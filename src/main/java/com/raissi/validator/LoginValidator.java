package com.raissi.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.raissi.service.UserService;

@Component("loginValidator")
@Scope("request")
public class LoginValidator implements Validator{
	
	@Inject
	private UserService userService;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		System.out.println("Validator called: "+value);
		if(userService.findUserByLoginOrEmail(value.toString()) != null){
			FacesMessage msg = 
				new FacesMessage("Login validation failed.", "Login is not available, please choose another one.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
	}

}
