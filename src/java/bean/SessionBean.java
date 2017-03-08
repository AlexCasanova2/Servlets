package bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import modelo.Pokemon;
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
    
    public List<Trainer> selectAllEntrenadores(){
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    }
    
    public Trainer getTrainerByName(String name){
        return emf.createEntityManager().find(Trainer.class, name);
    }
    
    public boolean existePokemon(Pokemon p){
        EntityManager em = emf.createEntityManager();
        Pokemon encontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return encontrado !=null;
    }
    
    public boolean insertarPokemon(Pokemon p){
        if(!existePokemon(p)){
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }else{
            return false;
        }
    }
}
