/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Chollos;
import java.util.ArrayList;
import java.util.List;
import DTO.Comentario;
import DTO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ezequiel
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getChollosList() == null) {
            usuario.setChollosList(new ArrayList<Chollos>());
        }
        if (usuario.getComentarioList() == null) {
            usuario.setComentarioList(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Chollos> attachedChollosList = new ArrayList<Chollos>();
            for (Chollos chollosListChollosToAttach : usuario.getChollosList()) {
                chollosListChollosToAttach = em.getReference(chollosListChollosToAttach.getClass(), chollosListChollosToAttach.getCodChollo());
                attachedChollosList.add(chollosListChollosToAttach);
            }
            usuario.setChollosList(attachedChollosList);
            List<Comentario> attachedComentarioList = new ArrayList<Comentario>();
            for (Comentario comentarioListComentarioToAttach : usuario.getComentarioList()) {
                comentarioListComentarioToAttach = em.getReference(comentarioListComentarioToAttach.getClass(), comentarioListComentarioToAttach.getCodComentario());
                attachedComentarioList.add(comentarioListComentarioToAttach);
            }
            usuario.setComentarioList(attachedComentarioList);
            em.persist(usuario);
            for (Chollos chollosListChollos : usuario.getChollosList()) {
                Usuario oldIdUsuarioOfChollosListChollos = chollosListChollos.getIdUsuario();
                chollosListChollos.setIdUsuario(usuario);
                chollosListChollos = em.merge(chollosListChollos);
                if (oldIdUsuarioOfChollosListChollos != null) {
                    oldIdUsuarioOfChollosListChollos.getChollosList().remove(chollosListChollos);
                    oldIdUsuarioOfChollosListChollos = em.merge(oldIdUsuarioOfChollosListChollos);
                }
            }
            for (Comentario comentarioListComentario : usuario.getComentarioList()) {
                Usuario oldIdUsuarioOfComentarioListComentario = comentarioListComentario.getIdUsuario();
                comentarioListComentario.setIdUsuario(usuario);
                comentarioListComentario = em.merge(comentarioListComentario);
                if (oldIdUsuarioOfComentarioListComentario != null) {
                    oldIdUsuarioOfComentarioListComentario.getComentarioList().remove(comentarioListComentario);
                    oldIdUsuarioOfComentarioListComentario = em.merge(oldIdUsuarioOfComentarioListComentario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<Chollos> chollosListOld = persistentUsuario.getChollosList();
            List<Chollos> chollosListNew = usuario.getChollosList();
            List<Comentario> comentarioListOld = persistentUsuario.getComentarioList();
            List<Comentario> comentarioListNew = usuario.getComentarioList();
            List<String> illegalOrphanMessages = null;
            for (Chollos chollosListOldChollos : chollosListOld) {
                if (!chollosListNew.contains(chollosListOldChollos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chollos " + chollosListOldChollos + " since its idUsuario field is not nullable.");
                }
            }
            for (Comentario comentarioListOldComentario : comentarioListOld) {
                if (!comentarioListNew.contains(comentarioListOldComentario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comentario " + comentarioListOldComentario + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Chollos> attachedChollosListNew = new ArrayList<Chollos>();
            for (Chollos chollosListNewChollosToAttach : chollosListNew) {
                chollosListNewChollosToAttach = em.getReference(chollosListNewChollosToAttach.getClass(), chollosListNewChollosToAttach.getCodChollo());
                attachedChollosListNew.add(chollosListNewChollosToAttach);
            }
            chollosListNew = attachedChollosListNew;
            usuario.setChollosList(chollosListNew);
            List<Comentario> attachedComentarioListNew = new ArrayList<Comentario>();
            for (Comentario comentarioListNewComentarioToAttach : comentarioListNew) {
                comentarioListNewComentarioToAttach = em.getReference(comentarioListNewComentarioToAttach.getClass(), comentarioListNewComentarioToAttach.getCodComentario());
                attachedComentarioListNew.add(comentarioListNewComentarioToAttach);
            }
            comentarioListNew = attachedComentarioListNew;
            usuario.setComentarioList(comentarioListNew);
            usuario = em.merge(usuario);
            for (Chollos chollosListNewChollos : chollosListNew) {
                if (!chollosListOld.contains(chollosListNewChollos)) {
                    Usuario oldIdUsuarioOfChollosListNewChollos = chollosListNewChollos.getIdUsuario();
                    chollosListNewChollos.setIdUsuario(usuario);
                    chollosListNewChollos = em.merge(chollosListNewChollos);
                    if (oldIdUsuarioOfChollosListNewChollos != null && !oldIdUsuarioOfChollosListNewChollos.equals(usuario)) {
                        oldIdUsuarioOfChollosListNewChollos.getChollosList().remove(chollosListNewChollos);
                        oldIdUsuarioOfChollosListNewChollos = em.merge(oldIdUsuarioOfChollosListNewChollos);
                    }
                }
            }
            for (Comentario comentarioListNewComentario : comentarioListNew) {
                if (!comentarioListOld.contains(comentarioListNewComentario)) {
                    Usuario oldIdUsuarioOfComentarioListNewComentario = comentarioListNewComentario.getIdUsuario();
                    comentarioListNewComentario.setIdUsuario(usuario);
                    comentarioListNewComentario = em.merge(comentarioListNewComentario);
                    if (oldIdUsuarioOfComentarioListNewComentario != null && !oldIdUsuarioOfComentarioListNewComentario.equals(usuario)) {
                        oldIdUsuarioOfComentarioListNewComentario.getComentarioList().remove(comentarioListNewComentario);
                        oldIdUsuarioOfComentarioListNewComentario = em.merge(oldIdUsuarioOfComentarioListNewComentario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Chollos> chollosListOrphanCheck = usuario.getChollosList();
            for (Chollos chollosListOrphanCheckChollos : chollosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Chollos " + chollosListOrphanCheckChollos + " in its chollosList field has a non-nullable idUsuario field.");
            }
            List<Comentario> comentarioListOrphanCheck = usuario.getComentarioList();
            for (Comentario comentarioListOrphanCheckComentario : comentarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Comentario " + comentarioListOrphanCheckComentario + " in its comentarioList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
