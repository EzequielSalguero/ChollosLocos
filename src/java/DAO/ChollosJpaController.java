/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Chollos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ezequiel
 */
public class ChollosJpaController implements Serializable {

    public ChollosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chollos chollos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = chollos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                chollos.setIdUsuario(idUsuario);
            }
            em.persist(chollos);
            if (idUsuario != null) {
                idUsuario.getChollosList().add(chollos);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chollos chollos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chollos persistentChollos = em.find(Chollos.class, chollos.getCodChollo());
            Usuario idUsuarioOld = persistentChollos.getIdUsuario();
            Usuario idUsuarioNew = chollos.getIdUsuario();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                chollos.setIdUsuario(idUsuarioNew);
            }
            chollos = em.merge(chollos);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getChollosList().remove(chollos);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getChollosList().add(chollos);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chollos.getCodChollo();
                if (findChollos(id) == null) {
                    throw new NonexistentEntityException("The chollos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chollos chollos;
            try {
                chollos = em.getReference(Chollos.class, id);
                chollos.getCodChollo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chollos with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = chollos.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getChollosList().remove(chollos);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(chollos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chollos> findChollosEntities() {
        return findChollosEntities(true, -1, -1);
    }

    public List<Chollos> findChollosEntities(int maxResults, int firstResult) {
        return findChollosEntities(false, maxResults, firstResult);
    }

    private List<Chollos> findChollosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chollos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Chollos findChollos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chollos.class, id);
        } finally {
            em.close();
        }
    }

    public int getChollosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chollos> rt = cq.from(Chollos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List mostrarChollosPorFecha(){
        EntityManager em = getEntityManager();
        TypedQuery<Chollos> query = em.createNamedQuery("Chollos.ordenadosFecha", Chollos.class);
        return query.getResultList();
    }
    
    public List mostrarChollosPorFechaSeccion(String seccion){
        EntityManager em = getEntityManager();
        TypedQuery<Chollos> query = em.createNamedQuery("Chollos.ordenadosFechaSeccion", Chollos.class);
        query.setParameter("seccion", seccion);
        return query.getResultList();
    }
    
    public List obtenerChollosDestacados(){
        EntityManager em = getEntityManager();
        TypedQuery<Chollos> query = em.createNamedQuery("Chollos.ordenadosPorLikes", Chollos.class);
        return query.getResultList();
    }
    
     public List obtenerChollosUsuario(String usu){
        EntityManager em = getEntityManager();
        TypedQuery<Chollos> query = em.createNamedQuery("Chollos.obtenerChollosUsuario", Chollos.class);
        query.setParameter("idUsuario", usu);
        return query.getResultList();
    }
    
}
