package com.raissi.service;

import java.io.InputStream;

import com.raissi.domain.Resume;

public interface ResumeService {
	String ATTR_RESUME = "RESUME_ATTRIBUTE";
	
	public void persistCvContent(InputStream content, Resume resume);
	public InputStream getCvByUser(Long userId);
}
