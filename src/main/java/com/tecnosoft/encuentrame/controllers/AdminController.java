/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;


import com.tecnosoft.encuentrame.model.Lugar;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.ILugarRepository;
import com.tecnosoft.encuentrame.repository.IUsrComunidadRepository;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 *
 * @author Oscaiito
 */
@ManagedBean
@Controller("adminT")
@Scope ("session")
public class AdminController{
    private List<Usuario> lst;
    private String objetivo;
    private String placeName;
    private String gps;
    private Lugar place;
    private int tipo;
    
    @Autowired
    private IUsrComunidadRepository usrControll;
    
    @Autowired
    private ILugarRepository gpsControll;
    
    public class Usuario{
        public UsrComunidad usr;
        public String status;
        
        public Usuario(UsrComunidad us){
            this.usr = us;
            this.status = "";
        }

        public UsrComunidad getUsr() {
            return usr;
        }

        public void setUsr(UsrComunidad usr) {
            this.usr = usr;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        
        
    }
    
    @PostConstruct
    public void init(){
        
        //this.lst = this.usrControll.findAll();
    }
    public void search(){
        this.objetivo = this.objetivo.trim().toLowerCase();
        List<Usuario> temp = new ArrayList<>();
        boolean add = true;
        for(UsrComunidad u: this.usrControll.findAll()){
            if(!u.getEstado().equalsIgnoreCase("activo"))
                continue;
            
            switch(this.tipo){
                case 1:
                    add = u.getUsuario().contains(this.objetivo);
                    break;
                case 2:
                    add = u.getNombre().toLowerCase().contains(this.objetivo);
                    add |= u.getApMaterno().toLowerCase().contains(this.objetivo);
                    break;
                case 3:
                    add = u.getCorreo().toLowerCase().contains(this.objetivo);
                    break;
            }
            
            if(add){
                temp.add(new Usuario(u));
            }
        }
        this.lst = temp;
        
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void update(){
        List<Usuario> temp = new ArrayList<>();
        for(Usuario u : this.lst){
            if(Boolean.valueOf(u.status)){
                u.usr.setEstado("banned");
                try{
                    this.usrControll.save(u.usr);
                }catch(Exception e){
                    System.out.println("Cannot update ");
                }
            }else{
                temp.add(u);
            }
            
        }
        this.lst = temp;
    }
    
    public int addPlace(){
        this.place = gpsControll.findOne(this.placeName);
        if(this.place != null){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ya existe un lugar con nombre " + this.placeName));
            
            return 1;
        }
        if(!this.gps.startsWith("https://www.google.com.mx/maps/place")){
            return 1;
        }
        
        this.place = new Lugar();
        this.place.setNombreLugar(this.placeName);
        this.place.setUbicacionGmaps(gps);
        try {
            gpsControll.save(place);
        } catch (Exception ex) {
            System.out.println("Cannot create place" + ex.toString());
        }
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("\nSe ha agregado" + this.placeName + " a la lista de lugares"));
        
        return 0;
    }

    public List<Usuario> getLst() {
        return lst;
    }

    public void setLst(List<Usuario> lst) {
        this.lst = lst;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Lugar getPlace() {
        return place;
    }

    public void setPlace(Lugar place) {
        this.place = place;
    }
    
    
}
