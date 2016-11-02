package com.crackers.common;

import java.security.cert.CertificateException;
import java.util.Properties;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import play.Play;

@Component
public class TriggerMail
{

	private static final Logger	LOGGER	= LoggerFactory.getLogger(TriggerMail.class);

	/**
	 * Method for Triggering mail by fetching the configurations from application.conf file and also send the Failure Tracks
	 */
	public void sendMail(Exception exception)
	{
		try
		{
			Session session = null;
			String smtpIp = Play.application().configuration().getString("email.host");
			String smtpPort = Play.application().configuration().getString("email.port");
			Properties props = new Properties();
			props.put(CommonConstants.MAIL_TRANSPORT_PROTOCOL, "smtp");
			props.put(CommonConstants.MAIL_SMTP_HOST, smtpIp);
			props.put(CommonConstants.MAIL_SMTP_PORT, smtpPort);
			if (Boolean.parseBoolean(Play.application().configuration().getString("email.tls")))
			{
				props.put(CommonConstants.MAIL_SMTP_STARTTLS, "true");
				props.put(CommonConstants.MAIL_SMTP_AUTH, "true");
				props.put(CommonConstants.MAIL_SMTP_SSL, smtpIp);
				final String username = Play.application().configuration().getString("email.username");
				final String password = Play.application().configuration().getString("email.password");
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
			message.setFrom(new InternetAddress(Play.application().configuration().getString("email.default_from"), Play.application().configuration().getString("email.from_name")));
			message.setSubject(Play.application().configuration().getString("email.subject"));
			message.setContent(exception, "text/html; charset=utf-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Play.application().configuration().getString("email.default_to")));
			Transport.send(message);
		}
		catch (Exception e)
		{
			LOGGER.error("Exception while Sending Mail ", e);
		}
	}
}