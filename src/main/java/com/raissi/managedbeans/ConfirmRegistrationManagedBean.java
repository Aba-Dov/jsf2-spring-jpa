package com.raissi.managedbeans;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLQueryParameter;
import com.raissi.domain.User;
import com.raissi.service.UserService;

@Component
@Scope("request")
@URLMapping(id = "confirm-register", pattern = "/confirm", viewId = "/pages/confirm-registration.jsf")
public class ConfirmRegistrationManagedBean extends BaseManagedBean{
	private static final long serialVersionUID = 4540076687974386129L;

	@Inject
	private UserService userService;
	@Inject
	private @Named("loggedInUser") LoggedInUser loggedInUser;
	@URLQueryParameter("token")
	private String token;
	private String confirmMessage = "Please check the link we sent you via email";

	@URLAction
	public String confirmUserProfile(){
		//Token is supposed to be decoded
		System.out.println("Token: "+token);
		User user = userService.validateUser(token);
		if(user != null){
			loggedInUser.setUser(user);
			updateSecurityContext();
			confirmMessage = "Welcome, you have successfully validated your profile";
		}
		return null;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getConfirmMessage() {
		return confirmMessage;
	}
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
	@Override
	protected LoggedInUser getLoggedInUser() {
		return this.loggedInUser;
	}
	
}
