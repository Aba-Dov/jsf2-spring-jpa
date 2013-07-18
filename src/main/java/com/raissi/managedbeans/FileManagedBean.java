package com.raissi.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.raissi.service.ResumeService;

@Component
@Scope("session")
public class FileManagedBean implements Serializable{
	
	private static final long serialVersionUID = -6579218026280608456L;

	@Inject
	private ResumeService resumeService;
	
	private Long userId;

	
	public void displayFile(){
    	System.out.println("Here file ");
    	FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		InputStream file = resumeService.getCvByUser(userId); 
		if(file != null){
			response.setHeader("Content-disposition",
					"inline;filename=cv.pdf");
			//response.setContentLength((int)file.getTotalSpace());
			
			try {
				byte[] bytes = IOUtils.toByteArray(file);
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/pdf");
			context.responseComplete();
		}else{
			System.out.println("File null");
		}
    }


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
