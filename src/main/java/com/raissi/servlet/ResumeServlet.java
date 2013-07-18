package com.raissi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raissi.service.ResumeService;

@WebServlet("/file/cv")
public class ResumeServlet  extends HttpServlet {
	private static final long serialVersionUID = -221600603615879137L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		if(userId != null && !userId.equals("")){
	        byte[] content = (byte[]) request.getSession().getAttribute(ResumeService.ATTR_RESUME+userId);
	        if(content != null){
		        response.setContentType("application/pdf");
		        response.setContentLength(content.length);
		        response.getOutputStream().write(content);
	        }
		}
    }

}