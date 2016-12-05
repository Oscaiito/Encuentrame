/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.Objeto;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IObjetoRepository;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
/**
 *
 * @author Oscaiito
 */
@ManagedBean
@Controller("objEditor")
@Scope("session")
public class CreaObjetoController{
    @Autowired
    private IObjetoRepository objObjetoRepository;
    private Objeto objeto = new Objeto();
    
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;
    private UsrComunidad user = new UsrComunidad();
    
    @PostConstruct
    public void init() {
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usrComunidadRepository.findByUsuario(usr.getUsername());
        if(user != null){
            System.out.println("Recuperado: " + user.getUsuario());
        }
        
    }
    private boolean skip;
    
    public Objeto getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }
    
    public void save() {
        boolean ready = true;
        ready &= objeto.getPregunta1().length() > 2 &&
                objeto.getPregunta2().length() > 2 &&
                objeto.getPregunta3().length() > 2 &&
                objeto.getPregunta4().length() > 2 &&
                objeto.getPregunta5().length() > 2 &&
                objeto.getPregunta1().length() < 50 &&
                objeto.getPregunta2().length() < 50 &&
                objeto.getPregunta3().length() < 50 &&
                objeto.getPregunta4().length() < 50 &&
                objeto.getPregunta5().length() < 50;
        objeto.setFechaPublicacion(new Date());
        objeto.setEstado("activo");
        objeto.setUsuario(this.user);
        objeto.setEstado("activo");
        objeto.setImagen("no-image");
        if(ready)
            objObjetoRepository.save(objeto);
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
