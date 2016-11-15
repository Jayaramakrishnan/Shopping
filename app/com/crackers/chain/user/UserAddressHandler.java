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
import com.crackers.dto.ContactDetailsDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserAddressHandler extends Handler
{

    private static Logger  logger  = Logger.getLogger(UserAddressHandler.class);
    protected final String ADDRESS = "address";
    @Resource
    private UserService    userService;

    @Override
    public UserDto handleRequest(Integer idUser, UserDto userDto, Integer idCurrentUser, String changedList) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, UnparseableDateTimeStringException, IOException
    {
        CMSLogger.info(logger, "Inside CONTACT_DETAILS");
        CMSLogger.info(logger, "Inside ContactDetailsHandler");
        if (changedList.equalsIgnoreCase(ADDRESS) && userDto.getContactDetailsDtos() != null)
        {
            Integer userId = CryptoBinderUtil.getDecryptId(userDto.getIdUser());
            CMSLogger.info(logger, "Inside UserContactDetailsHandler");
            List<ContactDetailsDto> dtosFromDB = userService.getContactDetailsList(userId);
            List<ContactDetailsDto> contactDetailsDtosGiven = userDto.getContactDetailsDtos();
            CMSLogger.info(logger, "LIST Form DB:::::::" + dtosFromDB);
            CMSLogger.info(logger, "LIST from User:::::" + contactDetailsDtosGiven);
            Iterator<ContactDetailsDto> contactDetailsIteratorFromDb = dtosFromDB.iterator();
            List<ContactDetailsDto> contactDetailsDtoChanged = new ArrayList<>();
            CMSLogger.info(logger, "Size of user ContactDetails list:" + userDto.getContactDetailsDtos().size());
            if (contactDetailsDtosGiven.isEmpty())
            {
                CMSLogger.info(logger, "Full empty the list of contactDetailss");
                while (contactDetailsIteratorFromDb.hasNext())
                {
                    ContactDetailsDto contactDetailsDto = contactDetailsIteratorFromDb.next();
                    contactDetailsDto.setIsDeleted((short) 1);
                    ContactDetailsDto dto = userService.updateContactDetailsList(userId, idCurrentUser, contactDetailsDto);
                    contactDetailsDtoChanged.add(dto);
                }
            }
            else
            {
                if (dtosFromDB != null && !(dtosFromDB.isEmpty()))
                {
                    while (contactDetailsIteratorFromDb.hasNext())
                    {
                        int i = 0;
                        CMSLogger.info(logger, "Inside ContactDetails iterator of DB ");
                        ContactDetailsDto contactDetailsDto = contactDetailsIteratorFromDb.next();
                        for (ContactDetailsDto exteEntityDto : contactDetailsDtosGiven)
                        {
                            CMSLogger.info(logger, "Inside ContactDetails iterator of user" + (contactDetailsDto.getIdContactDetails().equals(exteEntityDto.getIdContactDetails())));
                            if (contactDetailsDto.getIdContactDetails().equals(exteEntityDto.getIdContactDetails()))
                            {
                                CMSLogger.info(logger, "This is old user ContactDetails!!!!!!!!!!");
                                i++;
                                if (!(contactDetailsDto.getStreet().equalsIgnoreCase(exteEntityDto.getStreet())) || !(contactDetailsDto.getIsDeleted().equals(exteEntityDto.getIsDeleted())))
                                {
                                    CMSLogger.info(logger, "Update the contactDetails");
                                    contactDetailsDto = userService.updateContactDetailsList(userId, idCurrentUser, exteEntityDto);
                                    contactDetailsDtoChanged.add(contactDetailsDto);
                                }
                                else
                                {
                                    CMSLogger.info(logger, "No change in contactDetails");
                                    contactDetailsDtoChanged.add(exteEntityDto);
                                }
                            }
                        }
                        if (i == 0)
                        {
                            CMSLogger.info(logger, "Delete the given contactDetailss");
                            contactDetailsDto.setIsDeleted((short) 1);
                            ContactDetailsDto dto = userService.updateContactDetailsList(userId, idCurrentUser, contactDetailsDto);
                            contactDetailsDtoChanged.add(dto);
                        }
                    }
                }
                for (ContactDetailsDto exteEntityDto : contactDetailsDtosGiven)
                {
                    CMSLogger.info(logger, "***********Inside create contactDetails*************");
                    if (exteEntityDto != null && exteEntityDto.getIdContactDetails() == null && exteEntityDto.getStreet() != null)
                    {
                        CMSLogger.info(logger, "This is new user ContactDetails!!!!!!!!!!");
                        ContactDetailsDto contactDetailsDto = userService.createContactDetails(userId, idCurrentUser, exteEntityDto);
                        CMSLogger.info(logger, "userContactDetailsDto" + contactDetailsDto);
                        contactDetailsDtoChanged.add(contactDetailsDto);
                    }
                }
                userDto.setContactDetailsDtos(userService.getContactDetailsList(userId));
                return userDto;
            }
        }
        return super.handleRequest(idUser, userDto, idUser, changedList);
    }
}
