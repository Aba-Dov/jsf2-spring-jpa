package com.raissi.managedbeans;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.raissi.domain.User;
import com.raissi.service.UserService;
import com.raissi.util.UserRole;

@Component
@Scope("view")
public class UserManagedBean extends BaseManagedBean{

	private static final long serialVersionUID = 152717293232892353L;
	private static final String ADMIN_HOME="pretty:admin-home";
	private static final String CANDIDATE_HOME="pretty:candidate-home";
	private static final String CANDIDATE_EDIT_PROFILE="pretty:candidate-edit-profile";
	
	public UserManagedBean(){
		System.out.println("Just for test, usermanagedbean nstantiated");
	}
	
	@Inject
	private UserService userService;
	@Inject
	private @Named("loggedInUser") LoggedInUser loggedInUser;
	
	private String userLogin;
	private String password;
	private User user;
	
	public String login(){
		if(userLogin != null && password != null){
			return someLoginMethod();
		}
		return "pretty:login";
	}
	
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public String registerNewUser(){
		user.setRole("NEW_REGISTERED");
		user.setFirstName(user.getLogin());
		user.setLastName(user.getLogin());
		user.setEmail("not_defined");
		userService.saveUser(user);
		System.out.println("User saved : "+user.getUserId());
		loggedInUser.setUser(user);
		updateSecurityContext();
		return CANDIDATE_EDIT_PROFILE;
	}
	
	public void showSignUp(ActionEvent event){
		System.out.println("SHOWSIGNUP called");
		user = new User();
	}
	public void cancelSignUp(ActionEvent event){
		user = null;
	}
	
	private String someLoginMethod(){
		User user = userService.loginUser(userLogin, password);
		if(user == null){
			return "pretty:login";
		}
		loggedInUser.setUser(user);
		updateSecurityContext();
		String home;
		if(user.hasRole(UserRole.ADMIN.toString())){
			home =  ADMIN_HOME;
		}else {
			home =  CANDIDATE_HOME;
		}
		
		return home;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	protected LoggedInUser getLoggedInUser() {
		return this.loggedInUser;
	}
	
}
