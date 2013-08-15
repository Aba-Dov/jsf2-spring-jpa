package com.raissi.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.raissi.dao.UserDao;
import com.raissi.domain.User;
import com.raissi.service.UserService;
import com.raissi.service.mail.MailService;

@Named("userService")
@Transactional
public class UserServiceImpl implements UserService, Serializable{
	private static final long serialVersionUID = -582344496993032759L;
	@Inject
	private UserDao userDao;
	
	@Inject //We will be using basicTextEncryptor for application being deployed on CloudBess since adding JCE is not simple
			//Use strongTextEncryptor instead if you can add JCE to your JDK
	private @Named("basicTextEncryptor")TextEncryptor textEncryptor;
	@Autowired
	private ServletContext servletContext;
	@Inject	
	private @Named("mailService")MailService mailService;
	
	@Inject
	private @Named("stringDigester")StandardStringDigester digester;

	@Override
	@Transactional(readOnly=true)
	public User loginUser(String login, String password) {
		User user = userDao.findUserByLoginOrEmail(login);
		if(user != null ){
			if(digester.matches(password, user.getPassword())){
				return user;
			}
		}
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveUser(User user){
		user.setPassword(digester.digest(user.getPassword()));
		userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findUserByLoginOrEmail(String loginOrEmail) {
		return userDao.findUserByLoginOrEmail(loginOrEmail);
	}


	@Override
	public List<User> getCandidates(int page, int pageSize) {
		return userDao.findUsers(page, pageSize);
	}
	
	@Override
	public String generateUserToken(String pageName, String tokenToBeEncrypted) throws UnsupportedEncodingException{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String domain = "http://"+request.getServerName()+":"+request.getServerPort();
		String context = servletContext.getContextPath();
		//As in the getContextPath() docs, The path starts with a "/" character but does not end with a "/" character 
		context = domain+context;
		String encryptedToken = URLEncoder.encode(textEncryptor.encrypt(tokenToBeEncrypted),"UTF-8");
		return context+"/"+pageName+"?token="+encryptedToken;
	}
	
	@Override
	public void updateUser(User user) {
		userDao.update(user);
		//Prepare to send confirm-register mail:
		try {
			String confirmUrl = generateUserToken("confirm", user.getLogin());
			//Send email:
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", user.getFirstName());
			model.put("confirmationLink", confirmUrl);
			mailService.sendMail("raissi.java@gmail.com", user.getEmail(), "Please confirm registration", model, "com/raissi/freemarker/confirm-register.ftl");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public User findUserByEncryptedLoginOrEmail(String encrypted){
		return userDao.findUserByLoginOrEmail(textEncryptor.decrypt(encrypted));
	}
	@Override
	public int countCandidates() {
		return userDao.countCandidates();
	}
	@Override
	public User validateUser(String token) {
		String username = textEncryptor.decrypt(token);
		System.out.println("Username: "+username);
		User user = userDao.findUserByLoginOrEmail(username);
		if(user != null && user.getRole().equals("NOT_ACTIVATED")){
			user.setRole("ROLE_USER");
			userDao.update(user);
		}
		return user;
	}
}
