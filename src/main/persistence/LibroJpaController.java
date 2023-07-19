package main.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import main.entities.Libro;
import main.persistence.exceptions.NonexistentEntityException;

public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LibroJpaController() {
        emf = PersistenceManager.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al crear la libro: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = libro.getIsbn();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getIsbn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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
    
    public Libro findLibro(long id) {
        EntityManager em = getEntityManager();
        try {
            Libro libro = em.find(Libro.class, id);
            if (libro == null) {
                System.out.println("No se encontró ningún libro con ese ISBN.");
            }
            return libro;
        } finally {
            em.close();
        }
    }

    public Libro findLibroByNombre(String nombre) {
    EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT a FROM Libro a WHERE a.titulo = :nombre", Libro.class);
            query.setParameter("nombre", nombre);
            try {
                return query.getSingleResult();
            } catch (NoResultException ex) {
                System.out.println("No se encontró ningún libro con ese título.");
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    public List<Libro> findLibroByAutor(String nombreAutor) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre = :nombreAutor", Libro.class);
            query.setParameter("nombreAutor", nombreAutor);
            List<Libro> libros = query.getResultList();
            if (libros.isEmpty()) {
                System.out.println("No se encontró ningún libro de ese autor.");
            }
            return libros;
        } finally {
        em.close();
        }
    }
    
    public List<Libro> findLibroByEditorial(String nombreEditorial) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l JOIN l.editorial a WHERE a.nombre = :nombreEditorial", Libro.class);
            query.setParameter("nombreEditorial", nombreEditorial);
            List<Libro> libros = query.getResultList();
            if (libros.isEmpty()) {
                System.out.println("No se encontró ningún libro de ese autor.");
            }
            return libros;
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
