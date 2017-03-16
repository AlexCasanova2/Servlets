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
    
    public List<Pokemon> selectAllPokemonLifeLvl(){
        return emf.createEntityManager().createQuery("select p from Pokemon p order by p.level desc, p.life desc").getResultList();
    }
    public List<Trainer> selectAllTrainersLvlLife(){
        return emf.createEntityManager().createQuery("select t from Trainer t order by t.points desc").getResultList();
    }
    public Trainer getTrainerByName(String name){
        return emf.createEntityManager().find(Trainer.class, name);
    }
    
    public List<Trainer> selectTrainerByPotions(){
        return emf.createEntityManager().createQuery("select t from Trainer t where t.potions > 1").getResultList();
    }
    
    public void updateTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer entrenador = em.find(Trainer.class, t.getName());
        entrenador.setPoints(t.getPoints());
        entrenador.setPotions(t.getPotions());
        em.persist(entrenador);
        em.close(); 
    }
    
    
    public boolean existePokemon(Pokemon p){
        EntityManager em = emf.createEntityManager();
        Pokemon encontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return encontrado !=null;
    }
    
    public boolean borrarPokemon(String nombre){
        EntityManager em = emf.createEntityManager();
        Pokemon borrar = em.find(Pokemon.class, nombre);
        boolean ok = false;
        if(borrar != null){
           em.remove(borrar);
           ok =true;
        }
        em.close();
        return ok;
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
