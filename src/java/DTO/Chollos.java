/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ezequiel
 */
@Entity
@Table(name = "chollos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chollos.findAll", query = "SELECT c FROM Chollos c")
    , @NamedQuery(name = "Chollos.findByCodChollo", query = "SELECT c FROM Chollos c WHERE c.codChollo = :codChollo")
    , @NamedQuery(name = "Chollos.findByTitulo", query = "SELECT c FROM Chollos c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Chollos.findByPrecAnt", query = "SELECT c FROM Chollos c WHERE c.precAnt = :precAnt")
    , @NamedQuery(name = "Chollos.findByPrecAct", query = "SELECT c FROM Chollos c WHERE c.precAct = :precAct")
    , @NamedQuery(name = "Chollos.findByFoto", query = "SELECT c FROM Chollos c WHERE c.foto = :foto")
    , @NamedQuery(name = "Chollos.findByFechaChollo", query = "SELECT c FROM Chollos c WHERE c.fechaChollo = :fechaChollo")
    , @NamedQuery(name = "Chollos.findBySeccion", query = "SELECT c FROM Chollos c WHERE c.seccion = :seccion")
    , @NamedQuery(name = "Chollos.findByLikes", query = "SELECT c FROM Chollos c WHERE c.likes = :likes")
    , @NamedQuery(name = "Chollos.ordenadosFecha", query = "SELECT c FROM Chollos c ORDER BY c.fechaChollo DESC")
    , @NamedQuery(name = "Chollos.ordenadosFechaSeccion", query = "SELECT c FROM Chollos c WHERE c.seccion=:seccion ORDER BY c.fechaChollo DESC")
    , @NamedQuery(name = "Chollos.ordenadosPorLikes", query = "SELECT c FROM Chollos c ORDER BY c.likes DESC")
    , @NamedQuery(name = "Chollos.obtenerChollosUsuario", query = "SELECT c FROM Chollos c WHERE c.idUsuario.idUsuario=:idUsuario")})

public class Chollos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodChollo")
    private Integer codChollo;
    @Basic(optional = false)
    @Column(name = "Titulo")
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "Cuerpo")
    private String cuerpo;
    @Basic(optional = false)
    @Column(name = "PrecAnt")
    private double precAnt;
    @Basic(optional = false)
    @Column(name = "PrecAct")
    private double precAct;
    @Basic(optional = false)
    @Lob
    @Column(name = "Enlace")
    private String enlace;
    @Basic(optional = false)
    @Column(name = "Foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "FechaChollo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaChollo;
    @Basic(optional = false)
    @Column(name = "Seccion")
    private String seccion;
    @Basic(optional = false)
    @Column(name = "Likes")
    private int likes;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Chollos() {
    }

    public Chollos(Integer codChollo) {
        this.codChollo = codChollo;
    }

    public Chollos(Integer codChollo, String titulo, String cuerpo, double precAnt, double precAct, String enlace, String foto, Date fechaChollo, String seccion, int likes) {
        this.codChollo = codChollo;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.precAnt = precAnt;
        this.precAct = precAct;
        this.enlace = enlace;
        this.foto = foto;
        this.fechaChollo = fechaChollo;
        this.seccion = seccion;
        this.likes = likes;
    }

    public Integer getCodChollo() {
        return codChollo;
    }

    public void setCodChollo(Integer codChollo) {
        this.codChollo = codChollo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public double getPrecAnt() {
        return precAnt;
    }

    public void setPrecAnt(double precAnt) {
        this.precAnt = precAnt;
    }

    public double getPrecAct() {
        return precAct;
    }

    public void setPrecAct(double precAct) {
        this.precAct = precAct;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaChollo() {
        return fechaChollo;
    }

    public void setFechaChollo(Date fechaChollo) {
        this.fechaChollo = fechaChollo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codChollo != null ? codChollo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chollos)) {
            return false;
        }
        Chollos other = (Chollos) object;
        if ((this.codChollo == null && other.codChollo != null) || (this.codChollo != null && !this.codChollo.equals(other.codChollo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Chollos[ codChollo=" + codChollo + " ]";
    }
    
}
