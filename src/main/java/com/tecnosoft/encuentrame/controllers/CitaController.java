/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;


import com.tecnosoft.encuentrame.model.Cita;
import com.tecnosoft.encuentrame.model.Lugar;
import com.tecnosoft.encuentrame.model.Respuestas;
import com.tecnosoft.encuentrame.model.UsrComunidad;
import com.tecnosoft.encuentrame.repository.ICitaRepository;
import com.tecnosoft.encuentrame.repository.ILugarRepository;
import com.tecnosoft.encuentrame.repository.IObjetoRepository;
import com.tecnosoft.encuentrame.repository.IRespuestasRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Oscaiito
 */
@ManagedBean
@Controller("dater")
@Scope("session")
public class CitaController{
    @Autowired
    private IObjetoRepository objControll;
    @Autowired
    private IRespuestasRepository ansControll;
    @Autowired
    private ICitaRepository cControll;
    @Autowired
    private ILugarRepository plControll;
    
    private UsrComunidad usuario;
    private List<String> places;
    private List<Par> faltantes;
    private List<CitaPar> porAceptar;
    private List<CitaPar> entregas;
    private List<CitaPar> recibos;
    private String d1, d2, d3;
    private String pl1, pl2, pl3;
    
    
    @PostConstruct
    public void init(){
        this.places = new ArrayList<>();
        for(Lugar l : plControll.findAll()){
            this.places.add(l.getNombreLugar());
        }
      
        this.usuario = TempUser.getCurrentUsr();
        
    }
    
    public class Par{
        private Respuestas r;
        private int pos;
        
        public Par(Respuestas ans, int c){
            this.r = ans;
            this.pos = c;
            
        }
        
        public Respuestas getR() {
            return r;
        }
        
        public void setR(Respuestas r) {
            this.r = r;
        }
        
        public int getPos() {
            return pos;
        }
        
        public void setPos(int pos) {
            this.pos = pos;
        }
    }
    
    public class CitaPar{
        private final ICitaRepository cControll;
        private final IRespuestasRepository rControll;
        private Cita date;
        private int count;
        @SuppressWarnings("FieldMayBeFinal")
        private List<CitaPar> mySelf;
        private final String show;
        public CitaPar(ICitaRepository cc, IRespuestasRepository rc, Cita date, int c, List<CitaPar> yo, String value){
            this.cControll = cc;
            this.rControll = rc;
            this.date = date;
            this.count = c;
            this.mySelf = yo;
            this.show = value;
        }
        
        public void aceptarCita(){
            //Date today = new Date();
            try{
                this.date.setEstado("aceptada");               
                this.cControll.save(this.date);
                this.date.getIdRespuestas().setEstado("confecha");
                this.rControll.save(this.date.getIdRespuestas());
                for(CitaPar dte : this.mySelf){
                    if(dte.getDate().getIdRespuestas().getIdRespuestas().compareTo(this.date.getIdRespuestas().getIdRespuestas()) == 0){
                        if(dte.getDate().getEstado().equalsIgnoreCase("nueva")){
                            dte.getDate().setEstado("noaceptada");
                            this.cControll.save(dte.getDate());
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Error al aceptar: " + e.toString());
            }
            
        }
        
        public String info(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            return this.date.getIdRespuestas().getIdObjeto().getUsuario().getNombre() +
                    " te puede entregar \"" +
                    this.date.getIdRespuestas().getIdObjeto().getTitulo() +
                    "\" fecha: " +
                    dateFormat.format(this.date.getHorario()) +
                    " en: ";
        }
        
        public String deliverTo(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            return "Entregar \"" + this.date.getIdRespuestas().getIdObjeto().getTitulo() +
                    "\" a: " + this.date.getIdRespuestas().getUsuario().getNombre() +
                    "\n fecha: " + dateFormat.format(this.date.getHorario());
        }
        
        public String reciveFrom(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            return "Recibir \"" + this.date.getIdRespuestas().getIdObjeto().getTitulo() +
                    "\" de: " + this.date.getIdRespuestas().getIdObjeto().getUsuario().getNombre() +
                    "\n fecha: " + dateFormat.format(this.date.getHorario());
        }
        
        public String getShow() {
            return show;
        }
        
        public Cita getDate() {
            return date;
        }
        
        public void setDate(Cita date) {
            this.date = date;
        }
        public int getCount() {
            return count;
        }
        
        public void setCount(int count) {
            this.count = count;
        }
    }
    
    //on save change respuesta.estado = 'concita'
    //on save change objeto.estado = 'entregando'
    public void saveDate(Integer idRespuesta){
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(this.d1.isEmpty() ||
                this.d2.isEmpty() ||
                this.d3.isEmpty() ||
                this.pl1.isEmpty() ||
                this.pl2.isEmpty() ||
                this.pl3.isEmpty()
                ) return;
        
        this.d1 = this.d1.replace("T", " ");
        this.d2 = this.d2.replace("T", " ");
        this.d3 = this.d3.replace("T", " ");
        
        if(!this.inHours(d1) || !this.inHours(d2) || !this.inHours(d3))
            return;
        
        
        Date dt1, dt2, dt3;
        try{
            dt1 = formatter.parse(this.d1);
            dt2 = formatter.parse(this.d2);
            dt3 = formatter.parse(this.d3);
        }catch(Exception e){
            System.out.println("Error parseando fecha: " + e.toString());
            return;
        }
        Cita p1, p2, p3;
        p1 = new Cita();
        p2 = new Cita();
        p3 = new Cita();
        
        
        Respuestas ans = this.ansControll.findOne(idRespuesta);
        if(ans == null){
            System.out.println("Something went wrong with respuestas: " + idRespuesta);
            return;
        }
        ans.setEstado("concita");
        ans.getIdObjeto().setEstado("entregando");
        try {
            
            this.objControll.save(ans.getIdObjeto());
            this.ansControll.save(ans);
            
        } catch(Exception e){
            System.out.println("Something went wrong editing respuesta: "+ ans.getIdRespuestas() + "\n" + e.toString());
        }
        p1.setEstado("nueva");
        p1.setHorario(dt1);
        p1.setIdRespuestas(ans);
        p1.setNombreLugar(this.plControll.findOne(this.pl1));
        
        p2.setEstado("nueva");
        p2.setHorario(dt2);
        p2.setIdRespuestas(ans);
        p2.setNombreLugar(this.plControll.findOne(this.pl2));
        
        p3.setEstado("nueva");
        p3.setHorario(dt3);
        p3.setIdRespuestas(ans);
        p3.setNombreLugar(this.plControll.findOne(this.pl3));
        
        this.cControll.save(p1);
        this.cControll.save(p2);
        this.cControll.save(p3);
        
        this.getFaltantes();
    }
    
    private boolean inHours(String t){
        Integer h = Integer.valueOf(t.substring(11, 13));
        Integer m = Integer.valueOf(t.substring(14));
        if(h < 7 || h > 21){
            return false;
        }
        if(h == 20){
            if(m > 0){
                return false;
            }
        }
        return true;
    }
    
    public List<Par> getFaltantes() {
        this.faltantes = new ArrayList<>();
        int c = 0;
        for(Respuestas ans : this.ansControll.findAll()){
            if(ans.getEstado().equalsIgnoreCase("aceptada")){
                if(ans.getIdObjeto().getUsuario().getUsuario().equalsIgnoreCase(this.usuario.getUsuario())){
                    this.faltantes.add(new Par(ans, c));
                    c++;
                }
            }
        }
        return faltantes;
    }
    
    public List<CitaPar> getPorAceptar() {
        this.porAceptar = new ArrayList<>();
        int count = 0;
        for(Cita dte : this.cControll.findAll()){
            if(dte.getEstado().equalsIgnoreCase("nueva")){
                //if(dte.getIdRespuestas().getUsuario().getUsuario().equalsIgnoreCase(this.usuario.getUsuario())){
                if(dte.getIdRespuestas().getUsuario().equals(this.usuario)){
                    this.porAceptar.add(new CitaPar(this.cControll, this.ansControll, dte, count, this.porAceptar, ""));
                    count++;
                }
            }
        }
        
        return porAceptar;
    }
    
    public List<CitaPar> getEntregas() {
        this.entregas = new ArrayList<>();
        int count = 0;
        for(Cita dte : this.cControll.findAll()){
            if(dte.getEstado().equalsIgnoreCase("aceptada")){
                if(dte.getIdRespuestas().getIdObjeto().getUsuario().equals(this.usuario) && dte.getHorario().after(new Date())){
                    this.entregas.add(new CitaPar(this.cControll,this.ansControll,dte,count,this.entregas,""));
                    count++;
                }
            }
        }
        return entregas;
    }
    
    public List<CitaPar> getRecibos() {
        this.recibos = new ArrayList<>();
        int count = 0;
        for(Cita dte: this.cControll.findAll()){
            if(dte.getEstado().equalsIgnoreCase("aceptada")){
                if(dte.getIdRespuestas().getUsuario().equals(this.usuario)){
                    if(dte.getHorario().before(new Date())){
                        this.recibos.add(new CitaPar(this.cControll,this.ansControll,dte,count,this.entregas,""));
                        count++;
                    }else{
                        this.recibos.add(new CitaPar(this.cControll,this.ansControll,dte,count,this.entregas,"none"));
                        count++;
                    }
                }
            }
        }
        return recibos;
    }
    
    public String getD1() {
        return d1;
    }
    
    public void setD1(String d1) {
        this.d1 = d1;
    }
    
    public String getD2() {
        return d2;
    }
    
    public void setD2(String d2) {
        this.d2 = d2;
    }
    
    public String getD3() {
        return d3;
    }
    
    public void setD3(String d3) {
        this.d3 = d3;
    }
    
    public String getPl2() {
        return pl2;
    }
    
    public void setPl2(String pl2) {
        this.pl2 = pl2;
    }
    
    public String getPl3() {
        return pl3;
    }
    
    public void setPl3(String pl3) {
        this.pl3 = pl3;
    }
    
    
    public String getPl1() {
        return pl1;
    }
    
    public void setPl1(String pl1) {
        this.pl1 = pl1;
    }
    
    public void setEntregas(List<CitaPar> entregas) {
        this.entregas = entregas;
    }
    
    public void setRecibos(List<CitaPar> recibos) {
        this.recibos = recibos;
    }
    
    
    public void setPorAceptar(List<CitaPar> porAceptar) {
        this.porAceptar = porAceptar;
    }
    public List<String> getPlaces() {
        return places;
    }
    
    public void setPlaces(List<String> places) {
        this.places = places;
    }
    
}
