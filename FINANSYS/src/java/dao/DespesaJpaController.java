/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Despesa;

/**
 *
 * @author weverton
 */
public class DespesaJpaController implements Serializable {

    public DespesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Despesa despesa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            despesa.setDataF(despesa.getDat());
            em.persist(despesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Despesa despesa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            despesa = em.merge(despesa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = despesa.getId();
                if (findDespesa(id) == null) {
                    throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesa despesa;
            try {
                despesa = em.getReference(Despesa.class, id);
                despesa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The despesa with id " + id + " no longer exists.", enfe);
            }
            em.remove(despesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Despesa> findDespesaEntities() {
        return findDespesaEntities(true, -1, -1);
    }

    public List<Despesa> findDespesaEntities(int maxResults, int firstResult) {
        return findDespesaEntities(false, maxResults, firstResult);
    }

    private List<Despesa> findDespesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Despesa.class));
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

    public Despesa findDespesa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Despesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDespesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Despesa> rt = cq.from(Despesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Despesa> pesquisarPorDescricao(String descricao) {
        EntityManager em = getEntityManager();

        TypedQuery<Despesa> q;
     
        q = em.createQuery("select d from Despesa d where d.descricao like :descricao",Despesa.class);
        q.setParameter("descricao", "%" + descricao + "%");
        
        return q.getResultList();
    }
    
}
