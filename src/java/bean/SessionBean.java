package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import modelo.Trainer;

@Stateless
public class SessionBean {
    
    @PersistenceUnit
      EntityManagerFactory emf;
    
    public boolean existeEntrenador(Trainer t){
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, t.getName());
        em.close();
        return encontrado != null;
    }
    
    
    public boolean insertarEntrenador(Trainer t){
        if(!existeEntrenador(t)){
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }else{
            return false;
        }
    }
}
