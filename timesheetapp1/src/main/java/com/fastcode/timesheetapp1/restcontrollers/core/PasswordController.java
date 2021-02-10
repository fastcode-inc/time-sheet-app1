package com.fastcode.timesheetapp1.restcontrollers.core;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fastcode.timesheetapp1.domain.core.authorization.tokenverification.TokenverificationEntity;
import com.fastcode.timesheetapp1.application.core.authorization.tokenverification.ITokenVerificationAppService;
import com.fastcode.timesheetapp1.application.core.authorization.users.IUsersAppService;
import com.fastcode.timesheetapp1.application.core.authorization.users.dto.*;
import com.fastcode.timesheetapp1.addons.email.application.mail.IEmailService;
import com.fastcode.timesheetapp1.commons.logging.LoggingHelper;
import com.fastcode.timesheetapp1.domain.core.authorization.users.IUsersRepository;
import com.fastcode.timesheetapp1.domain.core.authorization.users.UsersEntity;
import com.fastcode.timesheetapp1.security.JWTAppService;

@RestController
@RequestMapping("/password")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PasswordController {

	@Autowired
	private ITokenVerificationAppService _tokenAppService;

	@Autowired
	@Qualifier("usersAppService")
	private IUsersAppService _usersAppService;

	@Autowired
	@Qualifier("usersRepository")
	private IUsersRepository _usersRepository;

	@Autowired
	private IEmailService _emailService;

	@Autowired
	private PasswordEncoder pEncoder;
	
	@Autowired
	private JWTAppService _jwtAppService;

	@Autowired
	private LoggingHelper logHelper;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<HashMap<String,String>> processForgotPassword(@RequestBody ForgotPasswordInput passwordInput, HttpServletRequest request) throws InvalidInputException {
		
		if(passwordInput.getEmail() == null || !validate(passwordInput.getEmail()))
		{
			logHelper.getLogger().error("Email is not valid");
			throw new InvalidInputException("Email is not valid");
		}

		FindUsersByUsernameOutput foundUsers = _usersAppService.findByEmailaddress(passwordInput.getEmail());
	    Optional.ofNullable(foundUsers).orElseThrow(() -> new EntityNotFoundException(String.format("There does not exist a users with a email=%s", passwordInput.getEmail())));

		String appUrl = request.getScheme() + "://" + request.getServerName();
		System.out.println("App url " + appUrl);
		
		TokenverificationEntity	token = _tokenAppService.generateToken("password", foundUsers.getId());
		
        
        String subject = "Password Reset Request";
		String emailText = "To reset your password, click the link below:\n" + passwordInput.getClientUrl()
		+ "/reset-password?token=" + token.getToken();
		_emailService.sendEmail(_emailService.buildEmail(passwordInput.getEmail(), subject, emailText));
		
		String msg = "A password reset link has been sent to " + passwordInput.getEmail();
		HashMap resultMap = new HashMap<String,String>();
		resultMap.put("message", msg);
		return new ResponseEntity(resultMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<HashMap<String,String>> setNewPassword(@RequestBody ResetPasswordInput input) {
	
	    TokenverificationEntity tokenEntity = _tokenAppService.findByTokenAndType(input.getToken(), "password");
		Optional.ofNullable(tokenEntity).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid password reset link.")));
		
		FindUsersWithAllFieldsByIdOutput output = _usersAppService.findWithAllFieldsById(tokenEntity.getUsersId());
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid password reset link.")));
		
		if(new Date().after(tokenEntity.getExpirationTime()))
		{
			logHelper.getLogger().error("Token has expired, please request a new password reset");
			throw new EntityNotFoundException(
					String.format("Token has expired, please request a new password reset"));
		}

		output.setPassword(pEncoder.encode(input.getPassword()));
		_tokenAppService.deleteToken(tokenEntity);
		_usersAppService.updateUsersData(output);
		_jwtAppService.deleteAllUserTokens(output.getUsername()); 
	
		String msg = "Password reset successfully !";
		HashMap resultMap = new HashMap<String,String>();
		resultMap.put("message", msg);
		return new ResponseEntity(resultMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<HashMap<String,String>> changePassword(@RequestBody UpdatePasswordInput input) throws InvalidInputException {
		UsersEntity loggedInUser =  _usersAppService.getUsers();
		if(!pEncoder.matches(input.getOldPassword(), loggedInUser.getPassword()))
		{
			logHelper.getLogger().error("Invalid Old password");
			throw new InvalidInputException(
					String.format("Invalid Old password"));
		}
		if(pEncoder.matches(input.getNewPassword(), loggedInUser.getPassword()))
		{
			logHelper.getLogger().error("You cannot set prevoius password again");
			throw new InvalidInputException(
					String.format("You cannot set prevoius password again"));
		}

		loggedInUser.setPassword(pEncoder.encode(input.getNewPassword()));
		_usersRepository.save(loggedInUser);
		_jwtAppService.deleteAllUserTokens(loggedInUser.getUsername()); 
        String msg = "Password updated successfully !";
		HashMap resultMap = new HashMap<String,String>();
		resultMap.put("message", msg);
		return new ResponseEntity(resultMap, HttpStatus.OK);
	}


}
