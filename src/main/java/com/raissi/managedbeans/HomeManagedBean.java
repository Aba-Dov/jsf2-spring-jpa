package com.raissi.managedbeans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.raissi.domain.Resume;
import com.raissi.service.ResumeService;
import com.raissi.service.UserService;

@Component
@Scope("view")
public class HomeManagedBean extends BaseManagedBean{
	private static final long serialVersionUID = 5426154702541976181L;
	@Inject
	private @Named("loggedInUser") ILoggedInUser loggedInUser;
	@Inject
	private ResumeService resumeService;
	@Inject
	private UserService userService;
	
	private Resume resume;
	public HomeManagedBean(){}
	@PostConstruct
	public void init(){
		if(loggedInUser.getUser().getResume() != null){
			resume = loggedInUser.getUser().getResume();
		}else{
			resume = new Resume();
		}
	}

	private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void fileUploadListener(FileUploadEvent event){
    	file = event.getFile();
    	System.out.println("Uploaded file: "+file.getFileName());
    }

    public void upload() {
    	System.out.println("In upload method");
        if(file != null) {
    		try {
    			resume.setDocumentName(file.getFileName());
				resumeService.persistCvContent(file.getInputstream(), resume);
				loggedInUser.getUser().setResume(resume);
				FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (IOException e) {
				FacesMessage msg = new FacesMessage("File ", file.getFileName() + " couldn't be uploaded. Please contact admins");
	            FacesContext.getCurrentInstance().addMessage(null, msg);
				e.printStackTrace();
			}
        }
        userService.updateUser(loggedInUser.getUser()); //Save Resume (Eager association) and update user with ResumeId
        FacesMessage msg = new FacesMessage("Résumé updated with success!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String updateUserProfile(){
        //TODO handle when user is just updating profile, not newly registered
		if(loggedInUser.getUser().getRole().equals("NEW_REGISTERED")){
			loggedInUser.getUser().setRole("NOT_ACTIVATED");
			updateSecurityContext();
		}
		userService.updateUser(loggedInUser.getUser());
		return "pretty:awaiting-confirmation";
        
    }
    
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	@Override
	protected ILoggedInUser getLoggedInUser() {
		return this.loggedInUser;
	}
    
}
