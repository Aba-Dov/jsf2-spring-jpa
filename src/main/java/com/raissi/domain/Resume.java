package com.raissi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cv")
public class Resume implements Serializable{
	private static final long serialVersionUID = -6450539497238528693L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "objective", nullable = false)
	private String description;
	@Column(name = "content_url", nullable = false)
	private String contentUrl;
	@Column(name = "document_name", nullable = false)
	private String documentName;
	
	public Resume() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	@Override
	public String toString() {
		return "Resume [title=" + title + ", contentUrl=" + contentUrl + "]";
	}

}
