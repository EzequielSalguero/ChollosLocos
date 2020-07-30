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
@Table(name = "contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c")
    , @NamedQuery(name = "Contacto.findByEmail", query = "SELECT c FROM Contacto c WHERE c.email = :email")
    , @NamedQuery(name = "Contacto.findByMensaje", query = "SELECT c FROM Contacto c WHERE c.mensaje = :mensaje")})
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Id
    @Basic(optional = false)
    @Column(name = "Mensaje")
    private String mensaje;

    public Contacto() {
    }

    public Contacto(String mensaje) {
        this.mensaje = mensaje;
    }

    public Contacto(String mensaje, String email) {
        this.mensaje = mensaje;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensaje != null ? mensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.mensaje == null && other.mensaje != null) || (this.mensaje != null && !this.mensaje.equals(other.mensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Contacto[ mensaje=" + mensaje + " ]";
    }
    
}
