/**
 * @author jayaramakrishnanrajagopal
 * @date 04-Mar-2017
 */
package com.crackers.chain.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.crackers.common.CrackersLogger;
import com.crackers.dto.UserDto;
import com.crackers.exceptions.UnparseableDateTimeStringException;
import com.crackers.manager.db.UserManager;
import com.crackers.util.CryptoBinderUtil;

@Component
public class UserCredentialHandler extends Handler {
	
	private static Logger	logger	= Logger.getLogger(UserImageHandler.class);
	private String			image	= "userCredential";
	@Resource
	private UserManager		userService;

	@Override
	public UserDto handleRequest(Long idUser, UserDto userDto, String changedList) throws InvocationTargetException, UnparseableDateTimeStringException, IOException {
		if (changedList.equalsIgnoreCase(image) && userDto.getImageDto() != null) {
			CrackersLogger.info(logger, "Inside User Credential");
			return userService.updateUserCredential(CryptoBinderUtil.getDecryptId(userDto.getId()), userDto);
		}
		return super.handleRequest(idUser, userDto, changedList);
	}
}