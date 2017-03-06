package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.dto.EmailDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserEmailHandler extends Handler
{

    private static Logger         logger = Logger.getLogger(UserEmailHandler.class);
    protected static final String EMAIL  = "Email";
    @Resource
    private UserManager           userManager;
    @Resource
    private UserService           userService;

    @Override
    public UserDto handleRequest(Long idUser, UserDto userDto, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException {
        CrackersLogger.info(logger, "Inside EMAIL");
        if (changedList.equalsIgnoreCase(EMAIL) && userDto.getEmailDtos() != null)
        {
            Long userId = CryptoBinderUtil.getDecryptId(userDto.getId());
            CrackersLogger.info(logger, "Inside UserEmailHandler");
            List<EmailDto> dtosFromDB = userService.getEmailList(userId);
            List<EmailDto> externalEmailDtosGiven = userDto.getEmailDtos();
            Iterator<EmailDto> externalIteratorFromDb = dtosFromDB.iterator();
            List<EmailDto> externalEntityDtoChanged = new ArrayList<>();
            CrackersLogger.info(logger, "Size of user Email list:" + userDto.getEmailDtos().size());
            if (externalEmailDtosGiven.isEmpty())
            {
                CrackersLogger.info(logger, "Full empty the list of emails");
                while (externalIteratorFromDb.hasNext())
                {
                    EmailDto emailDto = externalIteratorFromDb.next();
                    emailDto.setIsDeleted((short) 1);
                    EmailDto dto = userService.updateUserEmail(userId, emailDto);
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
                        CrackersLogger.info(logger, "Inside Email iterator of DB ");
                        EmailDto emailDto = externalIteratorFromDb.next();
                        for (EmailDto exteEmailDto : externalEmailDtosGiven)
                        {
                            CrackersLogger.info(logger, "Inside Email iterator of user" + (emailDto.getId().equals(exteEmailDto.getId())));
                            if (emailDto.getId().equals(exteEmailDto.getId()))
                            {
                                CrackersLogger.info(logger, "This is old user Email!!!!!!!!!!");
                                i++;
                                EmailDto externalEntityDto;
                                if (!(emailDto.getEmail().equalsIgnoreCase(exteEmailDto.getEmail())) || !(emailDto.getIsDeleted().equals(exteEmailDto.getIsDeleted())) || !(emailDto.getIsPrimary().equals(exteEmailDto.getIsPrimary())))
                                {
                                    CrackersLogger.info(logger, "Update the email");
                                    externalEntityDto = userService.updateUserEmail(userId, exteEmailDto);
                                    externalEntityDtoChanged.add(externalEntityDto);
                                }
                                else
                                {
                                    CrackersLogger.info(logger, "No change in email");
                                    externalEntityDtoChanged.add(exteEmailDto);
                                }
                            }
                        }
                        if (i == 0)
                        {
                            CrackersLogger.info(logger, "Delete the given emails");
                            emailDto.setIsDeleted((short) 1);
                            EmailDto dto = userService.updateUserEmail(userId, emailDto);
                            externalEntityDtoChanged.add(dto);
                        }
                    }
                }
                for (EmailDto exteEntityDto : externalEmailDtosGiven)
                {
                    CrackersLogger.info(logger, "***********Inside create email*************" + exteEntityDto.getEmail());
                    if (exteEntityDto != null && exteEntityDto.getId() == null && exteEntityDto.getEmail() != null)
                    {
                        CrackersLogger.info(logger, "This is new user Email!!!!!!!!!!");
                        EmailDto externalEntityDto = userService.createEmail(userId, exteEntityDto);
                        externalEntityDtoChanged.add(externalEntityDto);
                    }
                }
                userDto.setEmailDtos(userService.getEmailList(userId));
                return userDto;
            }
        }
        return super.handleRequest(idUser, userDto, changedList);
    }
}
