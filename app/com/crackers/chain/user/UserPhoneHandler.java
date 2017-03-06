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
import com.crackers.dto.PhoneNumberDto;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.services.UserService;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserPhoneHandler extends Handler {

	private static Logger	logger	= Logger.getLogger(UserPhoneHandler.class);
	protected final String	PHONE	= "phoneNumber";
	@Resource
	private UserManager		userManager;
	@Resource
	private UserService		userService;

	@Override
	public UserDto handleRequest(Long idUser, UserDto userDto, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException {
		CrackersLogger.info(logger, "Inside PHONE");
		if (changedList.equalsIgnoreCase(PHONE) && userDto.getPhoneNumberDtos() != null) {
			Long userId = CryptoBinderUtil.getDecryptId(userDto.getId());
			CrackersLogger.info(logger, "Inside UserPhoneNumberHandler" + userDto.getId());
			List<PhoneNumberDto> dtosFromDB = userService.getPhoneNumberList(userId);			
			List<PhoneNumberDto> externalEntityDtosGiven = userDto.getPhoneNumberDtos();
			Iterator<PhoneNumberDto> externalIteratorFromDb = dtosFromDB.iterator();
			List<PhoneNumberDto> externalEntityDtoChanged = new ArrayList<>();
			CrackersLogger.info(logger, "Size of user PhoneNumber list:" + userDto.getPhoneNumberDtos().size());
			if (externalEntityDtosGiven.isEmpty()) {
				CrackersLogger.info(logger, "Full empty the list of phoneNumbers");
				while (externalIteratorFromDb.hasNext()) {
					PhoneNumberDto phoneNumberDto = externalIteratorFromDb.next();
					phoneNumberDto.setIsDeleted((short) 1);
					PhoneNumberDto dto = userService.updateUserPhoneNumber(userId, phoneNumberDto);
					externalEntityDtoChanged.add(dto);
				}
			}
			else {
				if (dtosFromDB != null && !(dtosFromDB.isEmpty())) {
					while (externalIteratorFromDb.hasNext()) {
						int i = 0;
						CrackersLogger.info(logger, "Inside PhoneNumber iterator of DB ");
						PhoneNumberDto phoneNumberDto = externalIteratorFromDb.next();
						for (PhoneNumberDto exteEntityDto : externalEntityDtosGiven) {
							CrackersLogger.info(logger, "Inside PhoneNumber iterator of user" + (phoneNumberDto.getId().equals(exteEntityDto.getId())));
							if (phoneNumberDto.getId().equals(exteEntityDto.getId())) {
								CrackersLogger.info(logger, "This is old user PhoneNumber!!!!!!!!!!");
								i++;
								PhoneNumberDto externalEntityDto = new PhoneNumberDto();
								CrackersLogger.info(logger, "Update the phoneNumber");
								externalEntityDto = userService.updateUserPhoneNumber(userId, exteEntityDto);
								externalEntityDtoChanged.add(externalEntityDto);
							}
						}
						if (i == 0) {
							CrackersLogger.info(logger, "Delete the given phoneNumbers");
							phoneNumberDto.setIsDeleted((short) 1);
							PhoneNumberDto dto = userService.updateUserPhoneNumber(userId, phoneNumberDto);
							externalEntityDtoChanged.add(dto);
						}
					}
				}
				for (PhoneNumberDto exteEntityDto : externalEntityDtosGiven) {
					if (exteEntityDto != null && exteEntityDto.getId() == null && exteEntityDto.getPhoneNumber() != null) {
						CrackersLogger.info(logger, "This is new user PhoneNumber!!!!!!!!!!");
						PhoneNumberDto externalEntityDto = userService.createPhoneNumber(userId, exteEntityDto);
						externalEntityDtoChanged.add(externalEntityDto);
					}
				}
				userDto.setPhoneNumberDtos(userService.getPhoneNumberList(userId));
				return userDto;
			}
		}
		return super.handleRequest(idUser, userDto, changedList);
	}
}