/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.controllers;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.annotation.ManagedBean;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
/**
 *
 * @author Karly
 */
@ManagedBean
@Controller("recoverPassword")
@Scope("session")
public class recoverPasswordController {
 
    private String correo;
    private String user;
    
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

   
    
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }
     
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Watch out for PrimeFaces."));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
    }
     
    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
    }    
    
    public void recover(){
        UsrComunidad userfound = usrComunidadRepository.findByUsuario(user);
        if (userfound == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se puedo encontrar al usuario"));
        }else{
            if (userfound.getCorreo().equals(correo)){
                
                JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         
                //Using gmail
                mailSender.setHost("smtp.gmail.com");
                mailSender.setPort(587);
                mailSender.setUsername("encuentrame2016app");
                mailSender.setPassword("findme2016");

                Properties javaMailProperties = new Properties();
                javaMailProperties.put("mail.smtp.starttls.enable", "true");
                javaMailProperties.put("mail.smtp.auth", "true");
                javaMailProperties.put("mail.transport.protocol", "smtp");
                javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

                mailSender.setJavaMailProperties(javaMailProperties);
                
                MimeMessagePreparator preparator = getMessagePreparator(userfound);
                try {
                    mailSender.send(preparator);
                    System.out.println("Message Send...Hurrey");
                } catch (MailException ex) {
                    System.err.println(ex.getMessage());
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Correo enviado."));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo es incorrecto"));
            }
            
        }
        
    }
    
    
    private MimeMessagePreparator getMessagePreparator(final UsrComunidad usercom) {
 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("sn4kes@gmail.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(usercom.getCorreo()));
                String message = usercom.getNombre()+"\n\n";
                message += "Haz pedido que se temande el password de tu cuenta en Ecuentrame \n\n";
                message += "Password: "+usercom.getPassword()+"\n";
                
                mimeMessage.setText(message);
                mimeMessage.setSubject("Encuentrame: Recuperacion de password");
            }

        };
        return preparator;
    }
}
