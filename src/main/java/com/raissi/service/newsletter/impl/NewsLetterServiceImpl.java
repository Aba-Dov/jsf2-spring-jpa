package com.raissi.service.newsletter.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raissi.domain.User;
import com.raissi.domain.newsletter.Article;
import com.raissi.service.UserService;
import com.raissi.service.mail.MailService;
import com.raissi.service.newsletter.NewsLetterService;

@Service("newsLetterService")
@Transactional
public class NewsLetterServiceImpl implements NewsLetterService{
	private static final long serialVersionUID = -6291547874161783407L;
	
	@Inject	
	private @Named("mailService")MailService mailService;
	@Inject
	private UserService userService;
	
	public void sendNewsLetter(User user){
		//Generate the Unsubscribe link, it will contain the user's email encrypted
		try {
			/*
			 * Will generate a complete url to the specified pageName and containing the tokenToBeEncrypted 
			 * as encrypted param, ex: http://mysite.com/confirm-registration?token=userNameEncrypted 
			 */
			String unsubscribeUrl = userService.generateUserToken("unsbscribe-newsletter", user.getEmail());
			//Get the site base url from the above url, and use it for images urls
			//It will be in the form: http://mysite.com/resources/freemarker
			String baseUrl = unsubscribeUrl.substring(0,unsubscribeUrl.indexOf("/unsbscribe")+1)+"resources/freemarker";
			List<Article> latestArticles = getLatestArticles();
			Map<String, String> headerTitles = getHottestNews();
			String newsSourceUrl = "http://www.richarddawkins.net/";
			String newsSourceName = "Richard Dawkins Foundation for Reason and Science";
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("unsubscribeUrl", unsubscribeUrl);
			model.put("latestArticles", latestArticles);
			model.put("headerTitles", headerTitles);
			model.put("newsSourceUrl", newsSourceUrl);
			model.put("newsSourceName", newsSourceName);
			model.put("baseUrl", baseUrl);
			mailService.sendMail("raissi.java@gmail.com", user.getEmail(),
					"Our Newsletter", model,
					"com/raissi/freemarker/newsletter.ftl");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String, String> getHottestNews(){
		Map<String, String> news = new HashMap<String, String>();
		//Just for testing purpose, we will generate a static list of news
		news.put("Richard Dawkins to headline unique Bristol event, Sat. 24th August","http://www.richarddawkins.net/news_articles/2013/7/19/richard-dawkins-to-headline-unique-bristol-event-sat-24th-august-2013");
		news.put("Parliament 'must pardon codebreaker Turing'","http://www.richarddawkins.net/news_articles/2013/7/19/parliament-must-pardon-codebreaker-turing");
		news.put("Curiosity team: Massive collision may have killed Red Planet","http://www.richarddawkins.net/news_articles/2013/7/19/curiosity-team-massive-collision-may-have-killed-red-planet");
		news.put("Tyrannosaurus rex hunted for live prey","http://www.richarddawkins.net/news_articles/2013/7/18/tyrannosaurus-rex-hunted-for-live-prey");
		return news;
	}
	
	public List<Article> getLatestArticles(){
		List<Article> latestArticles = new ArrayList<Article>();
		//Just for testing purpose, we will generate a static list of Articles
		//Noam Chomsky
		String chomskyDesc = "Avram Noam Chomsky (/ˈnoʊm ˈtʃɒmski/; born December 7, 1928) is an American linguist," +
				" philosopher, cognitive scientist, logician, political critic, and activist. " +
				"He is an Institute Professor and Professor (Emeritus) in the Department of Linguistics & Philosophy at MIT, " +
				"where he has worked for over 50 years. " +
				"In addition to his work in linguistics, he has written on war, politics, and mass media, " +
				"and is the author of over 100 books.[13] Between 1980 and 1992, " +
				"Chomsky was cited within the field of Arts and Humanities more often than any other living scholar, " +
				"and eighth overall within the Arts and Humanities Citation Index during the same period." +
				" He has been described as a prominent cultural figure, and was voted the \"world's top public intellectual\" " +
				"in a 2005 poll.[18]";
		String chomskyImg = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Chomsky.jpg/200px-Chomsky.jpg";
		Article noamChomsky = new Article("Noam Chomsky", chomskyImg, chomskyDesc);
		latestArticles.add(noamChomsky);
		//Richard Dawkins
		String dawkinsImg = "http://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Richard_Dawkins_Cooper_Union_Shankbone.jpg/250px-Richard_Dawkins_Cooper_Union_Shankbone.jpg";
		String dawkinsDesc = "Clinton Richard Dawkins, FRS, FRSL (born 26 March 1941) is an English ethologist," +
				" evolutionary biologist and author. He is an emeritus fellow of New College, Oxford," +
				" and was the University of Oxford's Professor for Public Understanding of Science from 1995 until 2008.";
		Article richardDawkins = new Article("Richard Dawkins", dawkinsImg, dawkinsDesc);
		latestArticles.add(richardDawkins);
		//Stephen Hawking
		String hawkingDesc = "Stephen William Hawking CH, CBE, FRS, FRSA (Listeni/ˈstiːvɛn hoʊkɪŋ/; stee-ven hoh-king; born 8 January 1942) " +
				"is an English theoretical physicist, cosmologist, author and Director of Research at the Centre for Theoretical Cosmology" +
				" within the University of Cambridge. Among his significant scientific works have been a collaboration with " +
				"Roger Penrose on gravitational singularities theorems in the framework of general relativity, " +
				"and the theoretical prediction that black holes emit radiation, often called Hawking radiation." +
				" Hawking was the first to set forth a cosmology explained by a union of the general theory of " +
				"relativity and quantum mechanics. He is a vocal supporter of the many-worlds interpretation of quantum mechanics.";
		Article stephenHawking = new Article("Stephen Hawking", null, hawkingDesc);
		latestArticles.add(stephenHawking);
		//Paul Nizan
		String nizanImg = "http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Nizanpaul.jpg/220px-Nizanpaul.jpg";
		String nizanDesc = "Paul-Yves Nizan (French: [nizɑ̃]; 7 February 1905 – 23 May 1940) was a French philosopher and writer. " +
				"He was born in Tours, Indre-et-Loire and studied in Paris where he befriended fellow student Jean-Paul Sartre at the Lycée Henri IV." +
				" He became a member of the French Communist Party, and much of his writing reflects his political beliefs, " +
				"although he resigned from the party upon hearing of the Molotov-Ribbentrop Pact in 1939. " +
				"He died in the Battle of Dunkirk, fighting against the German army in World War II.";
		Article paulNizan = new Article("Paul Nizan", nizanImg, nizanDesc);
		latestArticles.add(paulNizan);
		return latestArticles;
	}

}
