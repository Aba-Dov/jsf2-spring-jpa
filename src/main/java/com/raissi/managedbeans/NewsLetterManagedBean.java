package com.raissi.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLQueryParameter;
import com.raissi.domain.User;
import com.raissi.service.UserService;
import com.raissi.service.newsletter.NewsLetterService;

@Component
@Scope("view")
@URLMapping(id = "unsbscribe-newsletter", pattern = "/unsbscribe-newsletter", viewId = "/pages/unsbscribe-newsletter.jsf")
public class NewsLetterManagedBean implements Serializable{
	private static final long serialVersionUID = 4954832777751112186L;
	
	@Inject
	private @Named("loggedInUser") ILoggedInUser loggedInUser;
	@Inject
	private NewsLetterService newsLetterService;
	@Inject
	private UserService userService;
	@URLQueryParameter("token")
	private String token;
	private User user;
	
	//Here we will send user newsletter, 
	//we are sure he is logged in to call this method
	//and we are sure he has confirmed his email, 
	//based on the rendered attribute of the button calling this method
	public void sendMeNewsLetter(ActionEvent event){
		newsLetterService.sendNewsLetter(loggedInUser.getUser());
		FacesMessage msg = new FacesMessage("We just sent you a nice newsletter, please check you inbox!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void unsubscribe(ActionEvent event){
		//Do DB deletion here
		FacesMessage msg = new FacesMessage("Ok, we are sorry to say it, but you have successfully unsubscribed!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Prepare the unsubscribe view: get user by email
	 * @return
	 */
	@URLAction
	public String prepareUnsubscribeView(){
		//Token is supposed to be decoded by Prettyfaces
		try{
			user = userService.findUserByEncryptedLoginOrEmail(token); 
		}catch(Exception e){
			e.printStackTrace();
			return "pretty:error-request";
		}
		if(user == null){
			System.out.println("User is null, redirecting to error page");
			return "pretty:error-request";
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
