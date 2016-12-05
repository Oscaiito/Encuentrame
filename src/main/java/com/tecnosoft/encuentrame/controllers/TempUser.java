/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import javax.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Oscaiito
 */
@ManagedBean
@Controller("current")
@Scope("session")
public class TempUser {
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;
    private static UsrComunidad user;    
    private String data;
    
    
    public static void setCurrentUsr(UsrComunidad usrLogged){
        user = usrLogged;
    }
    
    public static UsrComunidad getCurrentUsr(){
        return user;
    }
    
    
    
}
