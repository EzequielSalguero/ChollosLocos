/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ContactoJpaController;
import DAO.ChollosJpaController;
import DAO.UsuarioJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Contacto;
import DTO.Chollos;
import DTO.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ezequiel
 */
public class beanAdministrador {
    
    private EntityManagerFactory emf;
    private UsuarioJpaController ctrUsu;
    private ChollosJpaController ctrChollo;
    private ContactoJpaController ctrCont;
    private String codUsu;
    private int codChollo;
    private String mensajeContacto;

    public beanAdministrador() {
        emf=Persistence.createEntityManagerFactory("ChollosLocosPU");
        ctrUsu=new UsuarioJpaController(emf);
        ctrChollo=new ChollosJpaController(emf);
        ctrCont=new ContactoJpaController(emf);
    }

    public String getMensajeContacto() {
        return mensajeContacto;
    }

    public void setMensajeContacto(String mensajeContacto) {
        this.mensajeContacto = mensajeContacto;
    }

    public int getCodChollo() {
        return codChollo;
    }

    public void setCodChollo(int codChollo) {
        this.codChollo = codChollo;
    }


    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }
    
    public List<Usuario> obtenerUsuarios(){
        return ctrUsu.findUsuarioEntities();
    }
    
    public List<Chollos> obtenerChollos(){
        return ctrChollo.findChollosEntities();
    }
    
    public void eliminarUsuario(){
        try {
            ctrUsu.destroy(codUsu);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(beanAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void elminarChollo(){

        try {
            ctrChollo.destroy(codChollo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    
    public List<Contacto> obtenerMensajes(){
        return ctrCont.findContactoEntities();
    }
    
    public void eliminarMensaje(){
        try {
            ctrCont.destroy(mensajeContacto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
