/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller("mapaPrueba")
@Scope("session")
public class MapaPruebaController {

    private String latitud;
    private String longitud;
    
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    @PostConstruct
    public void init() {
        latitud = "19.324806735794954";
        longitud = "-99.17930647730827";
    }
    
    
    
}
