/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnosoft.encuentrame.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELLINDIGO
 */
@Entity
@Table(name = "LUGAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lugar.findAll", query = "SELECT l FROM Lugar l"),
    @NamedQuery(name = "Lugar.findByNombreLugar", query = "SELECT l FROM Lugar l WHERE l.nombreLugar = :nombreLugar"),
    @NamedQuery(name = "Lugar.findByUbicacionGmaps", query = "SELECT l FROM Lugar l WHERE l.ubicacionGmaps = :ubicacionGmaps")})
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre_lugar")
    private String nombreLugar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ubicacion_gmaps")
    private String ubicacionGmaps;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreLugar")
    private Collection<Cita> citaCollection;

    public Lugar() {
    }

    public Lugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public Lugar(String nombreLugar, String ubicacionGmaps) {
        this.nombreLugar = nombreLugar;
        this.ubicacionGmaps = ubicacionGmaps;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getUbicacionGmaps() {
        return ubicacionGmaps;
    }

    public void setUbicacionGmaps(String ubicacionGmaps) {
        this.ubicacionGmaps = ubicacionGmaps;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreLugar != null ? nombreLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.nombreLugar == null && other.nombreLugar != null) || (this.nombreLugar != null && !this.nombreLugar.equals(other.nombreLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnosoft.encuentrame.model.Lugar[ nombreLugar=" + nombreLugar + " ]";
    }
    
}
