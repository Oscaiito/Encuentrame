/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller("userModify")
@Scope("session")
public class ModificarUsrController {
    
    private String correo;
    private String password;
    private String passwordConfirm;
    
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;
    private UsrComunidad user = new UsrComunidad();
    
    @PostConstruct
    public void init() {
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usrComunidadRepository.findByUsuario(usr.getUsername());
    }
    
    public UsrComunidad getUser() {
        return user;
    }
    
    public void setUser(UsrComunidad user) {
        this.user = user;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    
    public void save() {
        
        try {   
            user.setPassword(password);
            usrComunidadRepository.save(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificaciones exitosas.", "Modificaciones exitosas."));
        } catch (Exception ex) {
            
        }
        
    }
    
    public String adminTools(){
        if(this.user.getRoll().equalsIgnoreCase("admin")){
            return "";
        }
        return "none";
    }
    
}
