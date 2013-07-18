package com.raissi.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.raissi.domain.User;


@FacesConverter(value = "userConverter")
public class UserConverter implements Converter{
	

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(submittedValue);
                return new User(null, "candidate"+id, "candidate"+id, "Candidate"+id, "Candidate"+id);
                
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid category, number Format exception"));
            }
        }
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
            return null;
        } else {
            return String.valueOf(((User)value).getUserId());
        }
	}


}
