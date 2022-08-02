package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ProductDTO;
import model.Product;
import model.Shop;
import model.Stock;

public class PersistenceManager {
	private static PersistenceManager instance;
	public EntityManagerFactory emf;
	private EntityManager em;
	//TODO PASAR TODO A Runnables
	private PersistenceManager(){
		super();
		emf = Persistence.createEntityManagerFactory("JPAejemplo");
		em = emf.createEntityManager();
	}
	public static PersistenceManager getIntenace() {
		if(instance==null) {
			instance = new PersistenceManager();
		}
		return instance;
	}
	
	public List<Product> getAll() {
		return em.createNativeQuery("product.getAll").getResultList();
	}
	public long saveShop(Shop obj) {
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		try {
			em.persist(obj);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		return obj.getId();
	}
	public List<Shop> getAllShop() {
		EntityTransaction entityTransaction = em.getTransaction();
		List<Shop> result = null;
		entityTransaction.begin();
		try {
			result = em.createNamedQuery("shop.getAll").getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		return result;
	}
}
