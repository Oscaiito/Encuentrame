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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findByIdCita", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita"),
    @NamedQuery(name = "Cita.findByHorario", query = "SELECT c FROM Cita c WHERE c.horario = :horario"),
    @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita")
    private Integer idCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "nombre_lugar", referencedColumnName = "nombre_lugar")
    @ManyToOne(optional = false)
    private Lugar nombreLugar;
    @JoinColumn(name = "id_respuestas", referencedColumnName = "id_respuestas")
    @ManyToOne(optional = false)
    private Respuestas idRespuestas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCita")
    private Collection<UsrCalificacion> usrCalificacionCollection;

    public Cita() {
    }

    public Cita(Integer idCita) {
        this.idCita = idCita;
    }

    public Cita(Integer idCita, Date horario, String estado) {
        this.idCita = idCita;
        this.horario = horario;
        this.estado = estado;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lugar getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(Lugar nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public Respuestas getIdRespuestas() {
        return idRespuestas;
    }

    public void setIdRespuestas(Respuestas idRespuestas) {
        this.idRespuestas = idRespuestas;
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
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnosoft.encuentrame.model.Cita[ idCita=" + idCita + " ]";
    }
    
}
