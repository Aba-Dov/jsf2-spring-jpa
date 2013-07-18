package com.raissi.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.raissi.domain.User;
import com.raissi.model.UserLazyDataModel;
import com.raissi.service.ResumeService;
import com.raissi.service.UserService;

@Component
@Scope("view")
public class AdminHomeManagedBean  implements Serializable{
	private static final long serialVersionUID = 2889249602607213765L;
	
	@Inject
	private UserService userService;
	@Inject
	private ResumeService resumeService;
	
	private LazyDataModel<User> lazyUserDataModel;
	private User selectedUser;
	private String sitePath;
	
	@PostConstruct
	public void init(){
		lazyUserDataModel = new UserLazyDataModel(userService);
		sitePath = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
					+":"+FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
		System.out.println("Site path: "+sitePath);
	}
	
	public void generateUserCV(User user){
		setSelectedUser(user);
		if(user != null){
			InputStream file = resumeService.getCvByUser(user.getUserId());
			if(file != null){
				try {
					byte[] bytes = IOUtils.toByteArray(file);
					Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
					//We are using userId when storing cv content to be possible to display multiple files 
					session.put(ResumeService.ATTR_RESUME+user.getUserId(), bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void removeUserCVFromSession(CloseEvent event){
		if(selectedUser != null){
			Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.remove(ResumeService.ATTR_RESUME+selectedUser.getUserId());
		}
	}

	public LazyDataModel<User> getLazyUserDataModel() {
		return lazyUserDataModel;
	}

	public void setLazyUserDataModel(LazyDataModel<User> lazyUserDataModel) {
		this.lazyUserDataModel = lazyUserDataModel;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		System.out.println("-----------------Setting user");
		this.selectedUser = selectedUser;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}
	
	

}
