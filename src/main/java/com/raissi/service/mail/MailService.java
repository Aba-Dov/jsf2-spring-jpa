package com.raissi.service.mail;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

public class MailService {

	private JavaMailSenderImpl javaMailSender;
	private Configuration freemarkerConfiguration;
	
	/**
	 * Sample usage: SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("laabidi.raissi@gmail.com");
		message.setTo("laabidi.raissi@gmail.com");
		message.setSubject("Test");
		message.setText("Login with success");
		long before = System.currentTimeMillis();
		mailService.sendMail(message);
		System.out.println("Mail sent in: "+(System.currentTimeMillis()-before)+"ms");
	 * @param message
	 */
	@Async
	public void sendMail(final String from, final String to, final String subject, final Map<String, Object> model, final String template){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	            message.setFrom(from);
	    		message.setTo(to);
	    		message.setSubject(subject);
	    		//template sample: "com/raissi/freemarker/confirm-register.ftl"
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template,"UTF-8"), model);
	            message.setText(text, true);
	         }
	      };
		javaMailSender.send(preparator);
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public JavaMailSenderImpl getJavaMailSender() {
		return javaMailSender;
	}

	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}
	
	
}
