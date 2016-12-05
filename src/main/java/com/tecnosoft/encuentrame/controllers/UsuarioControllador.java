/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 *
 * @author DELLINDIGO
 */
@ManagedBean
@Controller("usuarioController")
@Scope("session")
public class UsuarioControllador {
    private UsrComunidad usuario;
    private List<UsrComunidad> usuarios;
    private String avatar;
    private String newAvatar;
    private String userName;
    private Integer calificacion;

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getAvatar() {
        return avatar;
    }
    @Autowired
    private IUsrComunidadRepository usrRepository;
    
    public String getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    private String currentUser;
    
    @PostConstruct
    public void init() {
        usuarios = usrRepository.findAll();
        usuario = new UsrComunidad();
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        currentUser = usr.getUsername();
        
        UsrComunidad t = this.usrRepository.findOne(currentUser);
        avatar = t.getAvatar();
        userName = t.getNombre();
        Double tmp = t.getPromCalificacion();
        calificacion = (Integer) tmp.intValue();
    }
    public UsrComunidad busquedaUsuario(String usr) {
        this.usuario = usrRepository.findByUsuario(usr);
        System.out.println(usuario.getNombre());
        return usuario;
    }
    
    public String obtenerAvatar(){
        this.init();
        if(avatar.equalsIgnoreCase("avatar-no-set")){
            return "/resources/img/articles/avatar-no-set.png";
        }
        return avatar;
    }
    
    public String getNewAvatar() {
        return newAvatar;
    }
    
    public void setNewAvatar(String newAvatar) {
        this.newAvatar = newAvatar;
    }
    
    public void upload(){
        if(this.newAvatar == null || this.newAvatar.isEmpty()){
            this.usuario.setAvatar("avatar-no-set");
        }else{
            this.usuario.setAvatar(newAvatar);
        }
        this.usrRepository.save(this.usuario);
    }
    
    public String getStar(double n){
       double v = this.usuario.getPromCalificacion();
       if(v >= n){
           return "fa fa-star";
       }
       if(v > n - 1.0){
           return "fa fa-star-half-o";
       } 
       return "fa fa-star-o";
    }
    
    public String meLlamo(){
        return userName;
    }
    
}
