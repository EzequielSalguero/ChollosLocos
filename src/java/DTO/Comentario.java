/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ezequiel
 */
@Entity
@Table(name = "comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c")
    , @NamedQuery(name = "Comentario.findByCodComentario", query = "SELECT c FROM Comentario c WHERE c.codComentario = :codComentario")
    , @NamedQuery(name = "Comentario.findByCodChollo", query = "SELECT c FROM Comentario c WHERE c.codChollo = :codChollo")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodComentario")
    private Integer codComentario;
    @Basic(optional = false)
    @Column(name = "CodChollo")
    private int codChollo;
    @Basic(optional = false)
    @Lob
    @Column(name = "Comentario")
    private String comentario;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Comentario() {
    }

    public Comentario(Integer codComentario) {
        this.codComentario = codComentario;
    }

    public Comentario(Integer codComentario, int codChollo, String comentario) {
        this.codComentario = codComentario;
        this.codChollo = codChollo;
        this.comentario = comentario;
    }

    public Integer getCodComentario() {
        return codComentario;
    }

    public void setCodComentario(Integer codComentario) {
        this.codComentario = codComentario;
    }

    public int getCodChollo() {
        return codChollo;
    }

    public void setCodChollo(int codChollo) {
        this.codChollo = codChollo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        hash += (codComentario != null ? codComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.codComentario == null && other.codComentario != null) || (this.codComentario != null && !this.codComentario.equals(other.codComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Comentario[ codComentario=" + codComentario + " ]";
    }
    
}
