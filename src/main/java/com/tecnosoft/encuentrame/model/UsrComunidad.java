/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELLINDIGO
 */
@Entity
@Table(name = "USR_COMUNIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsrComunidad.findAll", query = "SELECT u FROM UsrComunidad u"),
    @NamedQuery(name = "UsrComunidad.findByUsuario", query = "SELECT u FROM UsrComunidad u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UsrComunidad.findByCorreo", query = "SELECT u FROM UsrComunidad u WHERE u.correo = :correo"),
    @NamedQuery(name = "UsrComunidad.findByPassword", query = "SELECT u FROM UsrComunidad u WHERE u.password = :password"),
    @NamedQuery(name = "UsrComunidad.findByNombre", query = "SELECT u FROM UsrComunidad u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UsrComunidad.findByApPaterno", query = "SELECT u FROM UsrComunidad u WHERE u.apPaterno = :apPaterno"),
    @NamedQuery(name = "UsrComunidad.findByApMaterno", query = "SELECT u FROM UsrComunidad u WHERE u.apMaterno = :apMaterno"),
    @NamedQuery(name = "UsrComunidad.findBySexo", query = "SELECT u FROM UsrComunidad u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "UsrComunidad.findByRoll", query = "SELECT u FROM UsrComunidad u WHERE u.roll = :roll"),
    @NamedQuery(name = "UsrComunidad.findByFNacimiento", query = "SELECT u FROM UsrComunidad u WHERE u.fNacimiento = :fNacimiento"),
    @NamedQuery(name = "UsrComunidad.findByEscuela", query = "SELECT u FROM UsrComunidad u WHERE u.escuela = :escuela"),
    @NamedQuery(name = "UsrComunidad.findByAvatar", query = "SELECT u FROM UsrComunidad u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "UsrComunidad.findByPromCalificacion", query = "SELECT u FROM UsrComunidad u WHERE u.promCalificacion = :promCalificacion"),
    @NamedQuery(name = "UsrComunidad.findByEstado", query = "SELECT u FROM UsrComunidad u WHERE u.estado = :estado")})
public class UsrComunidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    @Size(max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 32)
    @Column(name = "ap_paterno")
    private String apPaterno;
    @Size(max = 32)
    @Column(name = "ap_materno")
    private String apMaterno;
    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "roll")
    private String roll;
    @Column(name = "f_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fNacimiento;
    @Size(max = 128)
    @Column(name = "escuela")
    private String escuela;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prom_calificacion")
    private double promCalificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Respuestas> respuestasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Twitter> twitterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Facebook> facebookCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Objeto> objetoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<UsrCalificacion> usrCalificacionCollection;

    public UsrComunidad() {
    }

    public UsrComunidad(String usuario) {
        this.usuario = usuario;
    }

    public UsrComunidad(String usuario, String correo, String password, String roll, double promCalificacion, String estado) {
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.roll = roll;
        this.promCalificacion = promCalificacion;
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public Date getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPromCalificacion() {
        return promCalificacion;
    }

    public void setPromCalificacion(double promCalificacion) {
        this.promCalificacion = promCalificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Respuestas> getRespuestasCollection() {
        return respuestasCollection;
    }

    public void setRespuestasCollection(Collection<Respuestas> respuestasCollection) {
        this.respuestasCollection = respuestasCollection;
    }

    @XmlTransient
    public Collection<Twitter> getTwitterCollection() {
        return twitterCollection;
    }

    public void setTwitterCollection(Collection<Twitter> twitterCollection) {
        this.twitterCollection = twitterCollection;
    }

    @XmlTransient
    public Collection<Facebook> getFacebookCollection() {
        return facebookCollection;
    }

    public void setFacebookCollection(Collection<Facebook> facebookCollection) {
        this.facebookCollection = facebookCollection;
    }

    @XmlTransient
    public Collection<Objeto> getObjetoCollection() {
        return objetoCollection;
    }

    public void setObjetoCollection(Collection<Objeto> objetoCollection) {
        this.objetoCollection = objetoCollection;
    }

    @XmlTransient
    public Collection<UsrCalificacion> getUsrCalificacionCollection() {
        return usrCalificacionCollection;
    }

    public void setUsrCalificacionCollection(Collection<UsrCalificacion> usrCalificacionCollection) {
        this.usrCalificacionCollection = usrCalificacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsrComunidad)) {
            return false;
        }
        UsrComunidad other = (UsrComunidad) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnosoft.encuentrame.model.UsrComunidad[ usuario=" + usuario + " ]";
    }
    
}
