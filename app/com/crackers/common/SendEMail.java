package com.crackers.common;

import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.controllers.Dashboard;
import com.crackers.dto.UserInfo;
import com.crackers.model.EmailTemplate;
import com.crackers.model.EmailTrack;
import com.crackers.model.User;
import com.crackers.repositories.ApplicationConfigRepository;
import com.crackers.repositories.EmailRepository;
import com.crackers.repositories.EmailTrackRepository;
import com.crackers.repositories.UserRepository;
import com.crackers.repositories.UserRoleRepository;
import com.google.common.base.Stopwatch;

@Component
public class SendEMail
{

	private static Logger				logger	= Logger.getLogger(SendEMail.class);
	@Resource
	private UserRoleRepository			userRoleRepository;
	@Resource
	private UserRepository				userRepository;
	@Resource
	private EmailRepository				emailRepository;
	@Resource
	private DateStringUtil				dateStringUtil;
	@Resource
	private ApplicationConfigRepository	applicationConfigRepository;
	@Resource
	private EmailTrackRepository		emailTrackRepository;

	public void sendMail(EmailTemplate emailTemplate, Integer resourceId, User user, List<UserInfo> userInfo)
	{
		Stopwatch stopwatch = Stopwatch.createStarted();
		if (emailTemplate == null)
		{
			return;
		}
		String isEmailOn = applicationConfigRepository.getConfigValueByKey(CommonConstants.IS_EMAIL_ON);
		if (isEmailOn == null)
		{
			CMSLogger.info(logger, "Config Value for Email ON or OFF is not set");
			return;
		}
		Boolean emailOn = Boolean.parseBoolean(isEmailOn);
		if (!emailOn)
		{
			CMSLogger.info(logger, "Email is Turned OFF");
			return;
		}
		for (UserInfo userInfos : userInfo)
		{
			CMSLogger.info(logger, "userInfos" + userInfo.size());
			if (userInfos != null)
			{
				EmailTemplate template = new EmailTemplate();
				template.setBody(emailTemplate.getBody());
				Map<String, String> map = new HashMap<>();
				if (Objects.nonNull(userInfos.getFullName()))
				{
					map.put("[[Name]]", userInfos.getFullName());
				}
				map.put(CommonConstants.POWERED_BY_XXX, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.POWERED_BY_XXX)));
				map.put(CommonConstants.SUPPORT_EMAIL_ID, EmailHelper.replaceNullInString(applicationConfigRepository.getConfigValueByKey(CommonConstants.SUPPORT_EMAIL_ID)));
				template.setBody(EmailHelper.getFilledEmailTemplate(map, emailTemplate.getBody()));
				try
				{
					Session session = null;
					Properties props = new Properties();
					props.put(CommonConstants.MAIL_TRANSPORT_PROTOCOL, "smtp");
					props.put(CommonConstants.MAIL_SMTP_HOST, getEmailSettings("smtp_ip"));
					props.put(CommonConstants.MAIL_SMTP_PORT, getEmailSettings("smtp_port"));
					if (Boolean.parseBoolean(Dashboard.clientConfigurationSettings.getIsTLSOn()))
					{
						props.put(CommonConstants.MAIL_SMTP_STARTTLS, "true");
						props.put(CommonConstants.MAIL_SMTP_AUTH, "true");
						props.put(CommonConstants.MAIL_SMTP_SSL, getEmailSettings("smtp_ip"));
						final String username = applicationConfigRepository.getConfigValueByKey(CommonConstants.EMAIL_ACCESS_USERNAME);// change accordingly
						final String password = applicationConfigRepository.getConfigValueByKey(CommonConstants.EMAIL_ACCESS_PASSWORD);// change accordingly
						TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

							public java.security.cert.X509Certificate[] getAcceptedIssuers()
							{
								return null;
							}

							@Override
							public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException
							{
							}

							@Override
							public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException
							{
							}
						} };
						// Install the all-trusting trust manager
						SSLContext sc = SSLContext.getInstance("SSL");
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
						HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
						// Create all-trusting host name verifier
						HostnameVerifier allHostsValid = new HostnameVerifier() {

							public boolean verify(String hostname, SSLSession session)
							{
								return true;
							}
						};
						// Install the all-trusting host verifier
						HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
						session = Session.getInstance(props, new javax.mail.Authenticator() {

							protected PasswordAuthentication getPasswordAuthentication()
							{
								return new PasswordAuthentication(username, password);
							}
						});
					}
					else
					{
						session = Session.getInstance(props);
					}
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(getEmailSettings("from_mail_id"), getEmailSettings("from_name")));
					message.setSubject(emailTemplate.getSubject());
					message.setContent(template.getBody(), "text/html; charset=utf-8");
					String emailSetting = getEmailSettings("user_test_mail");
					CMSLogger.info(logger, "user_test_mail " + emailSetting);
					if (emailSetting != null && emailSetting.equalsIgnoreCase("TEST"))
					{
						String testMail = getEmailSettings("test_mail_id");
						if (testMail != null)
						{
							CMSLogger.info(logger, "TestMail" + testMail);
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(testMail));
						}
					}
					else if (emailSetting != null)
					{
						CMSLogger.info(logger, "UserMail" + userInfos.getUserName());
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(userInfos.getUserName()));
					}
					Transport.send(message);
					putEmailTrack(emailTemplate, user, userInfos, resourceId, (short) 1);
				}
				catch (Exception e)
				{
					CMSLogger.error(logger, "Exception while sending Mail", e);
					putEmailTrack(emailTemplate, user, userInfos, resourceId, (short) 0);
				}
			}
		}
		CMSLogger.info(logger, "stopwatch " + stopwatch);
	}

	private void putEmailTrack(EmailTemplate emailTemplate, User user, UserInfo userInfos, Integer resourceId, Short isMailSend)
	{
		CMSLogger.info(logger, "Inside email track");
		EmailTrack emailTrack = new EmailTrack();
		emailTrack.setIdEmailTemplate(emailTemplate.getIdEmailTemplate());
		emailTrack.setCreatedBy(user.getIdUser());
		if (Objects.nonNull(userInfos.getIdUser()))
		{
			emailTrack.setIdRecipient(userInfos.getIdUser());
		}
		emailTrack.setIdGeneric(resourceId);
		emailTrack.setIsMailSend(isMailSend);
		emailTrack.setIsDeleted((short) CommonConstants.IS_DELETED);
		emailTrack.setCreatedOn(dateStringUtil.getCurrentTimestamp());
		emailTrack.setEmail(userInfos.getUserName());
		emailTrackRepository.saveAndFlush(emailTrack);
	}

	public String getEmailSettings(String key)
	{
		String value = applicationConfigRepository.getConfigValueByKey(key);
		CMSLogger.debug(logger, "Email key is: ".concat(key));
		if (value == null)
		{
			CMSLogger.error(logger, "Missing Email Configuration in DB while fetching the key: " + key, null);
			return null;
		}
		return value;
	}

	public Integer getEmailSettingsInteger(String key)
	{
		String value = applicationConfigRepository.getConfigValueByKey(key);
		CMSLogger.debug(logger, "Email key is: ".concat(key));
		if (value == null)
		{
			CMSLogger.error(logger, "Missing Email Configuration in DB while fetching the key: " + key, null);
			return null;
		}
		return Integer.parseInt(value);
	}
}