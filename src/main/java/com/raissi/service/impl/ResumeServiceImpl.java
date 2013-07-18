package com.raissi.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.raissi.dao.ResumeDao;
import com.raissi.domain.Resume;
import com.raissi.service.ResumeService;

@Named("resumeService")
@Transactional
public class ResumeServiceImpl implements ResumeService, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ResumeDao resumeDao;
	@Value("${s3.accessKeyId}")
	private String amazonAccessKeyId;
	
	@Value("${s3.secretKey}")
	private String amazonSecretKey;
	
	@Value("${s3.bucketName}")
	private String bucketName;
	
	private S3Service s3Service;
	// To store data in S3 you must first create a bucket, a container for objects.
	private S3Bucket bucket;
	
	@PostConstruct
	public void init(){
		try {
			//amazon S3 storage credentials:
			AWSCredentials awsCredentials = 
			    new AWSCredentials(amazonAccessKeyId, amazonSecretKey);
			//To communicate with S3, create a class that implements an S3Service. 
			//We will use the REST/HTTP implementation based on HttpClient, 
			//as this is the most robust implementation provided with JetS3t.
			s3Service = new RestS3Service(awsCredentials);
			
			bucket = s3Service.getBucket(bucketName);
			if(bucket == null){
				bucket = s3Service.createBucket(bucketName);
			}
		} catch (S3ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void persistCvContent(InputStream content, Resume resume) {
		long currentTime = System.currentTimeMillis();
		String extension = resume.getDocumentName().substring(resume.getDocumentName().lastIndexOf("."));
		String fileName = currentTime+extension;
		
		try {
			byte[] contentArray = IOUtils.toByteArray(content);
			S3Object cvS3Object = new S3Object(fileName, contentArray);
			cvS3Object.setContentLength(contentArray.length);
			cvS3Object.setContentType("application/pdf");
			s3Service.putObject(bucket, cvS3Object);
			resume.setContentUrl(bucketName+"/"+fileName);
		} catch (S3ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InputStream getCvByUser(Long userId){
		Resume resume = resumeDao.getResumeByUser(userId);
		String fullS3Name = resume.getContentUrl();
		String bucketNameForResume = fullS3Name.substring(0, fullS3Name.indexOf("/"));
		String fileName = fullS3Name.substring(fullS3Name.indexOf("/")+1);
		try {
			System.out.println("Filename: "+fileName+", bucket: "+bucketNameForResume);
			S3Object cvS3Object = s3Service.getObject(bucketNameForResume, fileName);
			System.out.println("Got object: "+cvS3Object);
			if(cvS3Object != null){
				InputStream stream = cvS3Object.getDataInputStream();
				
				//return cvS3Object.getDataInputFile();
				return stream;
			}
		} catch (S3ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
