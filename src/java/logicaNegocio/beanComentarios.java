/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ComentarioJpaController;
import DTO.Comentario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ezequiel
 */
public class beanComentarios {

    private EntityManagerFactory emf;
    private ComentarioJpaController ctrComent;
    
    public beanComentarios() {
        emf=Persistence.createEntityManagerFactory("ChollosLocosPU");
        ctrComent=new ComentarioJpaController(emf);
    }
    
    public List obtenerComentarios(int cod){
        return ctrComent.obtenerComentariosChollo(cod);
    }
    
}
