/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import DAO.SeccionJpaController;
import DTO.Seccion;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ezequiel
 */
public class beanSeccion {
    
    private EntityManagerFactory emf;
    private SeccionJpaController ctrSec;
    private List<Seccion> listaSecciones;
    
    public beanSeccion(){
        emf = Persistence.createEntityManagerFactory("ChollosLocosPU");
        ctrSec = new SeccionJpaController(emf);
    }

    public List<Seccion> getListaSecciones() {
        listaSecciones = ctrSec.findSeccionEntities();
        return listaSecciones;
    }

    public void setListaSecciones(List<Seccion> listaSecciones) {
        this.listaSecciones = listaSecciones;
    }
    
    
    
}
