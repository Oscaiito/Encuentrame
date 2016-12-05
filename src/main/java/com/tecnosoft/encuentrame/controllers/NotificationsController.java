/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.tecnosoft.encuentrame.controllers;

import com.tecnosoft.encuentrame.model.Respuestas;
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
@Controller("notes")
@Scope("session")
public class NotificationsController{
    
    @Autowired
    private IObjetoRepository objControll;
    @Autowired
    private IRespuestasRepository ansControll;
    @Autowired
    private IUsrComunidadRepository usrRepository;
    
    private UsrComunidad usuario;
    private List<Notice> poll;
    private String notificaciones;
    
    
    
    @PostConstruct
    public void init(){
        usuario = new UsrComunidad();
        UserDetails usr = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.usuario = usrRepository.findOne(usr.getUsername());
    }
    
    public class Notice{
        private final IRespuestasRepository rControll;
        private final IObjetoRepository oControll;
        
        public Respuestas ans;
        public String title, qst1, qst2, qst3, qst4, qst5;
        public String ans1, ans2, ans3, ans4, ans5, estado, idAns;
        public List<Notice> mySelf = null;
        int span;
        
        public Notice(Respuestas ans, IRespuestasRepository rc, IObjetoRepository oc,int c){
            this.ans = ans;
            this.oControll = oc;
            this.rControll = rc;
            this.span = c;
            if(ans != null){
                this.title = ans.getIdObjeto().getTitulo();
                this.qst1 = ans.getIdObjeto().getPregunta1();
                this.qst2 = ans.getIdObjeto().getPregunta2();
                this.qst3 = ans.getIdObjeto().getPregunta3();
                this.qst4 = ans.getIdObjeto().getPregunta4();
                this.qst5 = ans.getIdObjeto().getPregunta5();
                this.ans1 = ans.getRespuesta1();
                this.ans2 = ans.getRespuesta2();
                this.ans3 = ans.getRespuesta3();
                this.ans4 = ans.getRespuesta4();
                this.ans5 = ans.getRespuesta5();
                this.estado = ans.getEstado();
                this.idAns = ans.getIdRespuestas().toString();
            }
        }
        
        public void validate(String estado){
            if(this.rControll == null){
                return;
            }
            try{
                this.ans.setEstado(estado);
                this.rControll.save(ans);
                if(estado.equalsIgnoreCase("aceptada")){
                    this.ans.getIdObjeto().setEstado("validado");
                    this.oControll.save(this.ans.getIdObjeto());
                    for(Notice n : this.mySelf){
                        if(n.ans.getIdObjeto().equals(this.ans.getIdObjeto()) && n.ans.getEstado().equalsIgnoreCase("nueva")){
                            n.ans.setEstado("rechazada");
                            this.rControll.save(n.ans);
                        }
                    }
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        
        public Respuestas getAns() {
            return ans;
        }
        
        public void setAns(Respuestas ans) {
            this.ans = ans;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getQst1() {
            return qst1;
        }
        
        public void setQst1(String qst1) {
            this.qst1 = qst1;
        }
        
        public String getQst2() {
            return qst2;
        }
        
        public void setQst2(String qst2) {
            this.qst2 = qst2;
        }
        
        public String getQst3() {
            return qst3;
        }
        
        public void setQst3(String qst3) {
            this.qst3 = qst3;
        }
        
        public String getQst4() {
            return qst4;
        }
        
        public void setQst4(String qst4) {
            this.qst4 = qst4;
        }
        
        public String getQst5() {
            return qst5;
        }
        
        public void setQst5(String qst5) {
            this.qst5 = qst5;
        }
        
        public String getAns1() {
            return ans1;
        }
        
        public void setAns1(String ans1) {
            this.ans1 = ans1;
        }
        
        public String getAns2() {
            return ans2;
        }
        
        public void setAns2(String ans2) {
            this.ans2 = ans2;
        }
        
        public String getAns3() {
            return ans3;
        }
        
        public void setAns3(String ans3) {
            this.ans3 = ans3;
        }
        
        public String getAns4() {
            return ans4;
        }
        
        public void setAns4(String ans4) {
            this.ans4 = ans4;
        }
        
        public String getAns5() {
            return ans5;
        }
        
        public void setAns5(String ans5) {
            this.ans5 = ans5;
        }
        
        public String getEstado() {
            return estado;
        }
        
        public void setEstado(String estado) {
            this.estado = estado;
        }
        
        public String getIdAns() {
            return idAns;
        }
        
        public void setIdAns(String idAns) {
            this.idAns = idAns;
        }
        
        public List<Notice> getMySelf() {
            return mySelf;
        }
        
        public void setMySelf(List<Notice> mySelf) {
            this.mySelf = mySelf;
        }
        
        public int getSpan() {
            return span;
        }
        
        public void setSpan(int span) {
            this.span = span;
        }
        
        
    }
    
    public List<Notice> getPoll() {
        this.poll = new ArrayList<>();
        Notice newN;
        int count = 0;
        for(Respuestas ans : ansControll.findAll()){
            if(ans.getEstado().equalsIgnoreCase("nueva")){
                if(ans.getIdObjeto().getUsuario().getUsuario().equalsIgnoreCase(this.usuario.getUsuario())){
                    newN = new Notice(ans, this.ansControll,this.objControll,count);
                    newN.mySelf= this.poll;
                    this.poll.add(newN);
                    count++;
                }
            }
        }
        return this.poll;
    }
    
    public String getNotificaciones() {
        if(this.getPoll().isEmpty())
            return "";
        return "[" + this.getPoll().size() + "]";
    }
    
}
