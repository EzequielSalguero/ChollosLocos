/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ezequiel
 */
@Embeddable
public class LikesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idUsuario")
    private String idUsuario;
    @Basic(optional = false)
    @Column(name = "codChollo")
    private int codChollo;

    public LikesPK() {
    }

    public LikesPK(String idUsuario, int codChollo) {
        this.idUsuario = idUsuario;
        this.codChollo = codChollo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodChollo() {
        return codChollo;
    }

    public void setCodChollo(int codChollo) {
        this.codChollo = codChollo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (int) codChollo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LikesPK)) {
            return false;
        }
        LikesPK other = (LikesPK) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if (this.codChollo != other.codChollo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.LikesPK[ idUsuario=" + idUsuario + ", codChollo=" + codChollo + " ]";
    }
    
}
