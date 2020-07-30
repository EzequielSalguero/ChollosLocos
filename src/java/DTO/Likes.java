/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ezequiel
 */
@Entity
@Table(name = "likes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l")
    , @NamedQuery(name = "Likes.findByIdUsuario", query = "SELECT l FROM Likes l WHERE l.likesPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "Likes.findByCodChollo", query = "SELECT l FROM Likes l WHERE l.likesPK.codChollo = :codChollo")})
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LikesPK likesPK;

    public Likes() {
    }

    public Likes(LikesPK likesPK) {
        this.likesPK = likesPK;
    }

    public Likes(String idUsuario, int codChollo) {
        this.likesPK = new LikesPK(idUsuario, codChollo);
    }

    public LikesPK getLikesPK() {
        return likesPK;
    }

    public void setLikesPK(LikesPK likesPK) {
        this.likesPK = likesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likesPK != null ? likesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.likesPK == null && other.likesPK != null) || (this.likesPK != null && !this.likesPK.equals(other.likesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Likes[ likesPK=" + likesPK + " ]";
    }
    
}
