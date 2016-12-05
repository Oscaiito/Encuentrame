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
@Table(name = "OBJETO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objeto.findAll", query = "SELECT o FROM Objeto o"),
    @NamedQuery(name = "Objeto.findByIdObjeto", query = "SELECT o FROM Objeto o WHERE o.idObjeto = :idObjeto"),
    @NamedQuery(name = "Objeto.findByFechaEncuentro", query = "SELECT o FROM Objeto o WHERE o.fechaEncuentro = :fechaEncuentro"),
    @NamedQuery(name = "Objeto.findByLugarE", query = "SELECT o FROM Objeto o WHERE o.lugarE = :lugarE"),
    @NamedQuery(name = "Objeto.findByDescripcion", query = "SELECT o FROM Objeto o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Objeto.findByImagen", query = "SELECT o FROM Objeto o WHERE o.imagen = :imagen"),
    @NamedQuery(name = "Objeto.findByCategoria", query = "SELECT o FROM Objeto o WHERE o.categoria = :categoria"),
    @NamedQuery(name = "Objeto.findByPregunta1", query = "SELECT o FROM Objeto o WHERE o.pregunta1 = :pregunta1"),
    @NamedQuery(name = "Objeto.findByPregunta2", query = "SELECT o FROM Objeto o WHERE o.pregunta2 = :pregunta2"),
    @NamedQuery(name = "Objeto.findByPregunta3", query = "SELECT o FROM Objeto o WHERE o.pregunta3 = :pregunta3"),
    @NamedQuery(name = "Objeto.findByPregunta4", query = "SELECT o FROM Objeto o WHERE o.pregunta4 = :pregunta4"),
    @NamedQuery(name = "Objeto.findByPregunta5", query = "SELECT o FROM Objeto o WHERE o.pregunta5 = :pregunta5"),
    @NamedQuery(name = "Objeto.findByEstado", query = "SELECT o FROM Objeto o WHERE o.estado = :estado"),
    @NamedQuery(name = "Objeto.findByFechaPublicacion", query = "SELECT o FROM Objeto o WHERE o.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Objeto.findByTitulo", query = "SELECT o FROM Objeto o WHERE o.titulo = :titulo")})
public class Objeto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_objeto")
    private Integer idObjeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_encuentro")
    @Temporal(TemporalType.DATE)
    private Date fechaEncuentro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lugar_e")
    private String lugarE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pregunta1")
    private String pregunta1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pregunta2")
    private String pregunta2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pregunta3")
    private String pregunta3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pregunta4")
    private String pregunta4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pregunta5")
    private String pregunta5;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "titulo")
    private String titulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjeto")
    private Collection<Respuestas> respuestasCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne(optional = false)
    private UsrComunidad usuario;

    public Objeto() {
    }

    public Objeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Objeto(Integer idObjeto, Date fechaEncuentro, String lugarE, String descripcion, String imagen, String categoria, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String estado, Date fechaPublicacion, String titulo) {
        this.idObjeto = idObjeto;
        this.fechaEncuentro = fechaEncuentro;
        this.lugarE = lugarE;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
        this.pregunta3 = pregunta3;
        this.pregunta4 = pregunta4;
        this.pregunta5 = pregunta5;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
        this.titulo = titulo;
    }

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Date getFechaEncuentro() {
        return fechaEncuentro;
    }

    public void setFechaEncuentro(Date fechaEncuentro) {
        this.fechaEncuentro = fechaEncuentro;
    }

    public String getLugarE() {
        return lugarE;
    }

    public void setLugarE(String lugarE) {
        this.lugarE = lugarE;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public String getPregunta4() {
        return pregunta4;
    }

    public void setPregunta4(String pregunta4) {
        this.pregunta4 = pregunta4;
    }

    public String getPregunta5() {
        return pregunta5;
    }

    public void setPregunta5(String pregunta5) {
        this.pregunta5 = pregunta5;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlTransient
    public Collection<Respuestas> getRespuestasCollection() {
        return respuestasCollection;
    }

    public void setRespuestasCollection(Collection<Respuestas> respuestasCollection) {
        this.respuestasCollection = respuestasCollection;
    }

    public UsrComunidad getUsuario() {
        return usuario;
    }

    public void setUsuario(UsrComunidad usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjeto != null ? idObjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objeto)) {
            return false;
        }
        Objeto other = (Objeto) object;
        if ((this.idObjeto == null && other.idObjeto != null) || (this.idObjeto != null && !this.idObjeto.equals(other.idObjeto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tecnosoft.encuentrame.model.Objeto[ idObjeto=" + idObjeto + " ]";
    }
    
}
