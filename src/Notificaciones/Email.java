package Notificaciones;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;


public class Email {

	final String username = "contacto@litech.cl";
	final String password = "C0nt4ct0.,";
	
	
	public Email(){
	
	}
	
	public boolean enviarEmail(String asunto, String mensaje, String destinatario){
	
		Properties propiedades = new Properties();
		propiedades.put("mail.smtp.host", "mail.litech.cl");
		propiedades.put("mail.transport.protocol", "smtp");
		propiedades.put("mail.smtp.auth", "true");
		propiedades.setProperty("mail.user", username);
		propiedades.setProperty("mail.password", password);
		
	Session mailSession = Session.getInstance(propiedades, new javax.mail.Authenticator(){
		
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication (username, password);
			
			}});
	
		MimeMessage msg = new MimeMessage(mailSession);
	
		try{
		msg.setSubject(asunto);
		msg.setFrom(new InternetAddress("contacto@litech.cl", "Sistema de monitoreo"));
		msg.addRecipients(Message.RecipientType.TO, new InternetAddress[]{ new InternetAddress(destinatario)});
		
		DataHandler dh = new DataHandler(mensaje, "text/plain");
		msg.setDataHandler(dh);
		
		javax.mail.Transport.send(msg);
		
		System.out.println("Mensaje enviado");
		return true;
		}
		catch(Exception e){
			
			System.out.println(e);
			return false;
		}
	
	}
	
}
