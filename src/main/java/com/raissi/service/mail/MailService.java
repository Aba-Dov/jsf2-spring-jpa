package com.raissi.service.mail;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

public class MailService {

	private JavaMailSender javaMailSender;
	private Configuration freemarkerConfiguration;
	
	@Async
	public void sendMail(final String from, final String to, final String subject, final Map<String, Object> model, final String template){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setFrom(from, "Raissi JSF2 sample");
	            //message.setFrom(from);
	    		message.setTo(to);
	    		message.setSubject(subject);
	    		//template sample: "com/raissi/freemarker/confirm-register.ftl"
	    		//the model object, typically a Map that contains model names as keys and model objects as values
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template,"UTF-8"), model);
	            message.setText(text, true);
	         }
	      };
		javaMailSender.send(preparator);
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}
	
	
}
