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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ezequiel
 */
@Entity
@Table(name = "seccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s")
    , @NamedQuery(name = "Seccion.findByCodSeccion", query = "SELECT s FROM Seccion s WHERE s.codSeccion = :codSeccion")
    , @NamedQuery(name = "Seccion.findByNomSeccion", query = "SELECT s FROM Seccion s WHERE s.nomSeccion = :nomSeccion")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CodSeccion")
    private Integer codSeccion;
    @Basic(optional = false)
    @Column(name = "NomSeccion")
    private String nomSeccion;

    public Seccion() {
    }

    public Seccion(Integer codSeccion) {
        this.codSeccion = codSeccion;
    }

    public Seccion(Integer codSeccion, String nomSeccion) {
        this.codSeccion = codSeccion;
        this.nomSeccion = nomSeccion;
    }

    public Integer getCodSeccion() {
        return codSeccion;
    }

    public void setCodSeccion(Integer codSeccion) {
        this.codSeccion = codSeccion;
    }

    public String getNomSeccion() {
        return nomSeccion;
    }

    public void setNomSeccion(String nomSeccion) {
        this.nomSeccion = nomSeccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codSeccion != null ? codSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.codSeccion == null && other.codSeccion != null) || (this.codSeccion != null && !this.codSeccion.equals(other.codSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Seccion[ codSeccion=" + codSeccion + " ]";
    }
    
}
