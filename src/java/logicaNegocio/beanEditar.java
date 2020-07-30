/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.ChollosJpaController;
import DTO.Chollos;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class beanEditar {

    private EntityManagerFactory emf;
    private ChollosJpaController ctrChollo;
    
    public beanEditar() {
        emf = Persistence.createEntityManagerFactory("ChollosLocosPU");
        ctrChollo =new ChollosJpaController(emf);
    }
    
    public List<Chollos> obtenerChollosUsuario(String usu){
        return ctrChollo.obtenerChollosUsuario(usu);
    }
    
    public Chollos obtenerChollo(int cod){
        return ctrChollo.findChollos(cod);
    }
    
    public List<Chollos> obtenerTodosLosChollos(){
        return ctrChollo.findChollosEntities();
    }
    
}
