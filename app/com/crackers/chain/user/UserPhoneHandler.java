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
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserPhoneHandler extends Handler
{

    private static Logger  logger = Logger.getLogger(UserPhoneHandler.class);
    protected final String PHONE  = "phoneNumber";
    @Resource
    private UserManager    userManager;
    @Resource
    private UserService    userService;

    @Override
    public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException
    {
        CMSLogger.info(logger, "Inside PHONE");
        if (changedList.equalsIgnoreCase(PHONE) && userDto.getPhoneNumberDtos() != null)
        {
            Integer userId = CryptoBinderUtil.getDecryptId(userDto.getIdUser());
            CMSLogger.info(logger, "Inside UserPhoneNumberHandler" + userDto.getIdUser());
            List<PhoneNumberDto> dtosFromDB = userService.getPhoneNumberList(userId);
            List<PhoneNumberDto> externalEntityDtosGiven = userDto.getPhoneNumberDtos();
            Iterator<PhoneNumberDto> externalIteratorFromDb = dtosFromDB.iterator();
            List<PhoneNumberDto> externalEntityDtoChanged = new ArrayList<>();
            CMSLogger.info(logger, "Size of user PhoneNumber list:" + userDto.getPhoneNumberDtos().size());
            if (externalEntityDtosGiven.isEmpty())
            {
                CMSLogger.info(logger, "Full empty the list of phoneNumbers");
                while (externalIteratorFromDb.hasNext())
                {
                    PhoneNumberDto phoneNumberDto = externalIteratorFromDb.next();
                    phoneNumberDto.setIsDeleted((short) 1);
                    PhoneNumberDto dto = userService.updatePhoneNumberList(userId, idCurrentUser, phoneNumberDto);
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
                        CMSLogger.info(logger, "Inside PhoneNumber iterator of DB ");
                        PhoneNumberDto phoneNumberDto = externalIteratorFromDb.next();
                        for (PhoneNumberDto exteEntityDto : externalEntityDtosGiven)
                        {
                            CMSLogger.info(logger, "Inside PhoneNumber iterator of user" + (phoneNumberDto.getIdPhoneNumber().equals(exteEntityDto.getIdPhoneNumber())));
                            if (phoneNumberDto.getIdPhoneNumber().equals(exteEntityDto.getIdPhoneNumber()))
                            {
                                CMSLogger.info(logger, "This is old user PhoneNumber!!!!!!!!!!");
                                i++;
                                PhoneNumberDto externalEntityDto = new PhoneNumberDto();
                                CMSLogger.info(logger, "Update the phoneNumber");
                                externalEntityDto = userService.updatePhoneNumberList(userId, idCurrentUser, exteEntityDto);
                                externalEntityDtoChanged.add(externalEntityDto);
                            }
                        }
                        if (i == 0)
                        {
                            CMSLogger.info(logger, "Delete the given phoneNumbers");
                            phoneNumberDto.setIsDeleted((short) 1);
                            PhoneNumberDto dto = userService.updatePhoneNumberList(userId, idCurrentUser, phoneNumberDto);
                            externalEntityDtoChanged.add(dto);
                        }
                    }
                }
                for (PhoneNumberDto exteEntityDto : externalEntityDtosGiven)
                {
                    if (exteEntityDto != null && exteEntityDto.getIdPhoneNumber() == null && exteEntityDto.getPhoneNumber() != null)
                    {
                        CMSLogger.info(logger, "This is new user PhoneNumber!!!!!!!!!!");
                        PhoneNumberDto externalEntityDto = userService.createPhoneNumber(userId, idCurrentUser, exteEntityDto);
                        externalEntityDtoChanged.add(externalEntityDto);
                    }
                }
                userDto.setPhoneNumberDtos(userService.getPhoneNumberList(userId));
                return userDto;
            }
        }
        return super.handleRequest(idUser, userDto, idUser, changedList);
    }
}