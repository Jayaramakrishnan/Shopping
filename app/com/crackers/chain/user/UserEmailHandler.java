package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CMSLogger;
import com.crackers.dto.EmailDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserEmailHandler extends Handler
{

	private static Logger	logger	= Logger.getLogger(UserEmailHandler.class);
	protected final String	EMAIL	= "Email";
	@Resource
	private UserService		userService;

	@Override
	public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnparseableDateTimeStringException, IOException
	{
		CMSLogger.info(logger, "Inside EMAIL");
		if (changedList.equalsIgnoreCase(EMAIL) && userDto.getEmailDtos() != null)
		{
			Integer userId = CryptoBinderUtil.getDecryptId(userDto.getIdUser());
			CMSLogger.info(logger, "Inside UserEmailHandler");
			List<EmailDto> dtosFromDB = userService.getEmailList(userId);
			List<EmailDto> externalEntityDtosGiven = userDto.getEmailDtos();
			CMSLogger.info(logger, "LIST Form DB:::::::" + dtosFromDB);
			CMSLogger.info(logger, "LIST from User:::::" + externalEntityDtosGiven);
			Iterator<EmailDto> externalIteratorFromDb = dtosFromDB.iterator();
			List<EmailDto> externalEntityDtoChanged = new ArrayList<>();
			CMSLogger.info(logger, "Size of user Email list:" + userDto.getEmailDtos().size());
			if (externalEntityDtosGiven.isEmpty())
			{
				CMSLogger.info(logger, "Full empty the list of emails");
				while (externalIteratorFromDb.hasNext())
				{
					EmailDto emailDto = externalIteratorFromDb.next();
					emailDto.setIsDeleted((short) 1);
					EmailDto dto = userService.updateEmailList(userId, idCurrentUser, emailDto);
					externalEntityDtoChanged.add(dto);
				}
			}
			else
			{
				if (dtosFromDB != null && !(dtosFromDB.isEmpty()))
				{
					while (externalIteratorFromDb.hasNext())
					{
						int i = 0;
						CMSLogger.info(logger, "Inside Email iterator of DB ");
						EmailDto emailDto = externalIteratorFromDb.next();
						for (EmailDto exteEntityDto : externalEntityDtosGiven)
						{
							CMSLogger.info(logger, "Inside Email iterator of user" + (emailDto.getIdEmail().equals(exteEntityDto.getIdEmail())));
							if (emailDto.getIdEmail().equals(exteEntityDto.getIdEmail()))
							{
								CMSLogger.info(logger, "This is old user Email!!!!!!!!!!");
								i++;
								EmailDto externalEntityDto = new EmailDto();
								if (!(emailDto.getEmail().equalsIgnoreCase(exteEntityDto.getEmail())) || !(emailDto.getIsDeleted().equals(exteEntityDto.getIsDeleted())) || !(emailDto.getIsPrimary().equals(exteEntityDto.getIsPrimary())))
								{
									CMSLogger.info(logger, "Update the email");
									externalEntityDto = userService.updateEmailList(userId, idCurrentUser, exteEntityDto);
									externalEntityDtoChanged.add(externalEntityDto);
								}
								else
								{
									CMSLogger.info(logger, "No change in email");
									externalEntityDtoChanged.add(exteEntityDto);
								}
							}
						}
						if (i == 0)
						{
							CMSLogger.info(logger, "Delete the given emails");
							emailDto.setIsDeleted((short) 1);
							EmailDto dto = userService.updateEmailList(userId, idCurrentUser, emailDto);
							externalEntityDtoChanged.add(dto);
						}
					}
				}
				for (EmailDto exteEntityDto : externalEntityDtosGiven)
				{
					CMSLogger.info(logger, "***********Inside create email*************" + exteEntityDto.getEmail());
					CMSLogger.debug(logger, "EMail");
					CMSLogger.debug(logger, "EMail");
					if (exteEntityDto != null && exteEntityDto.getIdEmail() == null && exteEntityDto.getEmail() != null)
					{
						CMSLogger.info(logger, "This is new user Email!!!!!!!!!!");
						EmailDto externalEntityDto = userService.createEmail(userId, idCurrentUser, exteEntityDto);
						CMSLogger.info(logger, "userEmailDto" + externalEntityDto);
						externalEntityDtoChanged.add(externalEntityDto);
					}
				}
				userDto.setEmailDtos(userService.getEmailList(userId));
				return userDto;
			}
		}
		return super.handleRequest(idUser, userDto, idUser, changedList);
	}
}
