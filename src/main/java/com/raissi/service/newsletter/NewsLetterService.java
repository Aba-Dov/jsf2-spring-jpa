package com.raissi.service.newsletter;

import java.io.Serializable;

import com.raissi.domain.User;

public interface NewsLetterService extends Serializable{

	public void sendNewsLetter(User user);
}
