/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;


import com.tecnosoft.encuentrame.model.Objeto;
import com.tecnosoft.encuentrame.model.Respuestas;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IObjetoRepository;
import com.tecnosoft.encuentrame.repository.IRespuestasRepository;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Oscaiito
 * @version 1.0
 * @class EditorEncuesta es el modelo que verifica los campos de encuestas y respuestas temporalmente.
 */

@ManagedBean
@Controller("ansEditor")
@Scope("session")
public class EncuestaController {
    //Entidades para manejar las encuestas y respuetas.
    private Objeto obj = new Objeto();
    private Respuestas ans = new Respuestas();
    @Autowired
    private IObjetoRepository objControll;
    @Autowired
    private IRespuestasRepository rControll;
    @Autowired
    private IUsrComunidadRepository usr;
    
    private static Objeto objeto;
    private UsrComunidad usuario;
    
    @PostConstruct
    public void init(){
        usuario = new UsrComunidad();
        UserDetails usrd = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.usuario = usr.findOne(usrd.getUsername());
        
    }
    
    /**
     * Obtiene las preguntas a contestar para mostrarlas en la vista de contestar encuesta.
     */
    public void cargaEncuesta(){
        //this.cleanContent();
        try{
            this.obj = EncuestaController.getObjeto();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        
    }
    
    /**
     * Valida que los campos de respuesta no sean vacios, y manda a genera las respuestas.
     */
    public void nuevaRespuesta(){
        boolean ready = true;
        ready &= ans.getRespuesta1().length() < 50 &&
                ans.getRespuesta2().length() < 50 &&
                ans.getRespuesta3().length() < 50 &&
                ans.getRespuesta4().length() < 50 &&
                ans.getRespuesta5().length() < 50 &&
                ans.getRespuesta1().trim().length() > 1 &&
                ans.getRespuesta2().trim().length() > 1 &&
                ans.getRespuesta3().trim().length() > 1 &&
                ans.getRespuesta4().trim().length() > 1 &&
                ans.getRespuesta5().trim().length() > 1;
        if(ready){
            try{
                ans.setIdObjeto(obj);
                ans.setEstado("nueva");
                ans.setUsuario(this.usuario);//Este usuario debería ser persistente durante la sesión iniciada.
                rControll.save(ans);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("\nSe han enviado tus respuestas"));
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        
    }
    
    //Getters y Setters
    public Objeto getObj() {
        return obj;
    }
    
    public void setObj(Objeto obj) {
        this.obj = obj;
    }
    
    public Respuestas getAns() {
        return ans;
    }
    
    public void setAns(Respuestas ans) {
        this.ans = ans;
    }
    
    public static Objeto getObjeto() {
        return objeto;
    }
    
    public static void setObjeto(Objeto objeto) {
        EncuestaController.objeto = objeto;
    }
    
    
}
