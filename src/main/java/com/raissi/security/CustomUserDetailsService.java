package com.raissi.security;

import javax.inject.Inject;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raissi.domain.User;
import com.raissi.managedbeans.ILoggedInUser;
import com.raissi.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Inject
	private UserService userService;
	@Inject
	private ILoggedInUser loggedInUser;
	
	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		System.out.println("Trying to fetch user with login: "+login);
		final User user = userService.findUserByLoginOrEmail(login);
		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}
		UserDetails details = new CustomUserDetails(user);
		Authentication authentication =  new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/*
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		loggedInUser = (LoggedInUser)request.getSession().getAttribute("loggedInUser");
		if(loggedInUser == null){
			loggedInUser = new LoggedInUser();
			request.getSession().setAttribute("loggedInUser", loggedInUser);
		}
		*/
		if(loggedInUser.getUser() == null){
			loggedInUser.setUser(user);
		}
		return details;
	}

}
