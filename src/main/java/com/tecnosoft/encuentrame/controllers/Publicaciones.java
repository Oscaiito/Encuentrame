/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.Objeto;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.IObjetoRepository;
import com.tecnosoft.encuentrame.repository.IRespuestasRepository;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
@Controller("muro")
@Scope("session")
public class Publicaciones {
    @Autowired
    private IObjetoRepository objObjetoRepository;
    @Autowired
    private IRespuestasRepository ansRespuestasRepository;
    @Autowired
    private IUsrComunidadRepository usrComunidadRepository;
    private UsrComunidad user = new UsrComunidad();
    private String objetivo;
    private String cat;
    private List<Item> cosas;
    private List<Objeto> misObjetos;
    
    
    
    
    @PostConstruct
    public void init(){
        
        this.user = TempUser.getCurrentUsr();
        /*
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usrComunidadRepository.findByUsuario(usr.getUsername());
        */
        if(user != null){
            System.out.println("Recuperado: " + user.getUsuario());
        }else{
            System.out.println("No user recovered");
        }
        
    }
    
    public class Item{
        public Objeto obj;
        public String isReclamable;
        
        public Item(Objeto o, String s){
            this.obj = o;
            this.isReclamable = s;
        }
        
        public Objeto getObj() {
            return obj;
        }
        
        public void setObj(Objeto obj) {
            this.obj = obj;
        }
        
        public String getIsReclamable() {
            return isReclamable;
        }
        
        public void setIsReclamable(String isReclamable) {
            this.isReclamable = isReclamable;
        }
        
        public void setObjeto(){
            EncuestaController.setObjeto(this.obj);
            
        }
        
        
    }
    
    
    public String search(){
        
        try{
            List<Item> nueva = new ArrayList<>();
            
            if(this.cat == null || this.cat.trim().isEmpty()){
                if(this.objetivo == null || this.objetivo.trim().isEmpty()){
                    for(Objeto o: this.objObjetoRepository.findAll()){
                        if(this.user != null){
                            nueva.add(new Item(o, this.user.getUsuario().equalsIgnoreCase(o.getUsuario().getUsuario()) ? "none" : ""));
                        }else{
                            nueva.add(new Item(o, "none"));
                        }
                    }
                }else{
                    for(Objeto o : this.objObjetoRepository.findAll()){
                        if(o.getTitulo().toLowerCase().contains(this.objetivo.trim().toLowerCase())){
                            if(this.user != null){
                                nueva.add(new Item(o, this.user.getUsuario().equalsIgnoreCase(o.getUsuario().getUsuario()) ? "none" : ""));
                            }else{
                                nueva.add(new Item(o, "none"));
                            }
                        }
                    }
                }
                this.cosas = nueva;
                return "";
            }
            
            if(this.objetivo == null || this.objetivo.trim().isEmpty()){
                for(Objeto o : this.objObjetoRepository.findAll()){
                    if(o.getCategoria().equalsIgnoreCase(this.cat)){
                        if(this.user != null){
                            nueva.add(new Item(o, this.user.getUsuario().equalsIgnoreCase(o.getUsuario().getUsuario()) ? "none" : ""));
                        }else{
                            nueva.add(new Item(o, "none"));
                        }
                    }
                }
                this.cosas = nueva;
                return "";
            }
            
            for(Objeto o : this.objObjetoRepository.findAll()){
                if(o.getCategoria().equalsIgnoreCase(this.cat) && o.getTitulo().toLowerCase().contains(this.objetivo.trim().toLowerCase())){
                    if(this.user != null){
                        nueva.add(new Item(o, this.user.getUsuario().equalsIgnoreCase(o.getUsuario().getUsuario()) ? "none" : ""));
                    }else{
                        nueva.add(new Item(o, "none"));
                    }
                    
                }
            }
            this.cosas = nueva;
            return "";
        }catch(Exception e){
            System.out.println("Error");
        }
        
        return "";
    }
    
    public void all(){
        this.cosas = new ArrayList<>();
        this.misObjetos = new ArrayList<>();
        
        if(this.user == null){
            for(Objeto o : this.objObjetoRepository.findAll()){
                if(o.getEstado().equalsIgnoreCase("activo")){
                    this.cosas.add(new Item(o, "none"));
                }
            }
        }else{
            for(Objeto o: this.objObjetoRepository.findAll()){
                if(o.getEstado().equalsIgnoreCase("activo")){
                    if(o.getUsuario().getUsuario().equalsIgnoreCase(this.user.getUsuario())){
                        this.cosas.add(new Item(o, "none"));
                        this.misObjetos.add(o);
                    }else{
                        this.cosas.add(new Item(o, ""));
                    }
                }
            }
        }
    }
    
    
    public List<Objeto> getMisObjetos() {
        return misObjetos;
    }
    
    public void setMisObjetos(List<Objeto> misObjetos) {
        this.misObjetos = misObjetos;
    }
    
    
    public String getObjetivo() {
        return objetivo;
    }
    
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    
    public String getCat() {
        return cat;
    }
    
    public void setCat(String cat) {
        this.cat = cat;
    }
    
    public List<Item> getCosas() {
        return cosas;
    }
    
    public void setCosas(List<Item> cosas) {
        this.cosas = cosas;
    }
    
    
}
