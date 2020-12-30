package controllers;

import java.util.List;
import java.util.Properties;  

import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class Mailer {  
public static void send(String to, String subject, String msg){  
  
final String user="mailerwebappserver12345";//change accordingly  
final String pass="applicationmailer";  
  
//1st step) Get the session object    
Properties props = new Properties();  
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.auth", "true");  
props.put("mail.debug", "true");
props.put("mail.store.protocol", "pop3");
props.put("mail.transport.protocol", "smtp");
  
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user, pass);  
   }  
});  
//2nd step)compose message  
try {  
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
 message.setSubject(subject);  
 message.setText(msg);  
   
 //3rd step)send message  
 Transport.send(message);  
  
 System.out.println("Done");  
  
 } catch (MessagingException e) {  
    throw new RuntimeException(e);  
 }  
      
}  

public static void sendMany(List<String> to, String subject, String msg){  
	  
final String user="mailerwebappserver12345";//change accordingly  
final String pass="applicationmailer";  
  
//1st step) Get the session object    
Properties props = new Properties();  
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.auth", "true");  
props.put("mail.debug", "true");
props.put("mail.store.protocol", "pop3");
props.put("mail.transport.protocol", "smtp");
  
Session session = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user, pass);  
   }  
});  
//2nd step)compose message  
try {  
 MimeMessage message = new MimeMessage(session);  
 message.setFrom(new InternetAddress(user));  
 for(int i=0; i < to.size(); i++)
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to.get(i)));  
 message.setSubject(subject);  
 message.setText(msg);  
   
 //3rd step)send message  
 Transport.send(message);  
  
 System.out.println("Done");  
  
 } catch (MessagingException e) {  
    throw new RuntimeException(e);  
 }  
      
} 
} 