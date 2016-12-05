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
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author DELLINDIGO
 */
@ManagedBean
@Controller("registroController")
@Scope("session")
public class RegistroUsrController {
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;
    private UsrComunidad user = new UsrComunidad();
    private boolean skip;
    
    public UsrComunidad getUser(){
        return user;
    }
    
    public void setUser(UsrComunidad usr){
        this.user = usr;
    }
    
    public String obtenerAvatar(){
        if(this.user.getAvatar().isEmpty()){
            return "/resources/img/articles/avatar-no-set.png";
        }
        return this.user.getAvatar();
    }
    public void save(){
        user.setRoll("user");
        user.setPromCalificacion(0.0);
        user.setEstado("Activo");
        if (user.getAvatar().isEmpty()){
            user.setAvatar("avatar-no-set");
        }
        if(usrComunidadRepository.findByUsuario(user.getUsuario())==null){
            usrComunidadRepository.save(user);
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", this.user.getUsuario()));
        
    }
    
    public String prettyDate(){
        if(user == null)
            return "unknown";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(user.getFNacimiento());
    }
    
    public int isRegistred(){
        if(usrComunidadRepository.findByUsuario(user.getUsuario()) != null)
            return 1;
        return 0;
    }
    
    public boolean isSkip() {
        return skip;
    }
    
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
    public String onFlowProcess(FlowEvent event) {
        
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
        
    }
}
