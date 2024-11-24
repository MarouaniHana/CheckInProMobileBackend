package atelierSpringBoot_Ch;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.angus.mail.handlers.multipart_mixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    
    private JavaMailSender mailSender;

 
    
   public void sendHtmlEmail() throws MessagingException, IOException{
    	MimeMessage message =mailSender.createMimeMessage();
    	message.setFrom(new InternetAddress("anyame@freelance.mailtrap.link"));
    	message.setRecipients(MimeMessage.RecipientType.TO,"siwar.bengaji@gmail.com");
    	String htmlTemplate = readFile("C:/edu.iset.atelierSpringBoot.Email/src/main/resources/template.html");
    	String htmlContent = htmlTemplate.replace("${name}", "john Doe");
    	htmlContent =htmlContent.replace("${message}","salem alaykom :)");
    	
    	
    	message.setContent(htmlContent, "text/html; charset=utf-8");
    	mailSender.send(message);
    }
    
    public String readFile(String filePath) throws IOException {
    	Path path=Paths.get(filePath);
    	return Files.readString(path,StandardCharsets.UTF_8);
    	
    }
    // Injection de JavaMailSender via le constructeur
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Méthode pour envoyer un email avec ou sans pièce jointe
    public void sendEmailAttachment(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("Siwarbengagi042@gmail.com"); // Adresse email de l'expéditeur
        helper.setTo(to); // Adresse email du destinataire
        helper.setSubject(subject); // Sujet de l'email
        helper.setText(body); // Contenu de l'email

        // Optionnel : ajouter une pièce jointe
        // FileSystemResource file = new FileSystemResource(new File("C:\\Users\\21654\\Downloads\\download.png"));
        // helper.addAttachment("attachment.jpg", file);

        mailSender.send(message);
        System.out.println("Email envoyé avec succès !");
    }
    
    //public void sendEmailWithAttachment(String to, String subject, String body) throws MessagingException {
    	//MimeMessage message mailSender.createMimeMessage();
    	//MimeMessageHelper helper = new MimeMessageHelper (message, multipart: true);
    	//helper.setFrom("anyname@freelance.mailtrap.link");
    //	helper.setTo(to);
    	//helper.setSubject(subject);
    ////	helper.setText(body);
    	//FileSystemResource file = new FileSystemResource(new File(pathname: "/Users/ahenka/Downloads/attachment.jpg"));
    	//helper.addAttachment (attachmentFilename: "attachment.jpg", file);
    	//mailSender.send(message);
    
    
}