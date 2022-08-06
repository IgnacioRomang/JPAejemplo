package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ProductDTO;
import dto.ShopDTO;
import dto.StockDTO;
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
		return em.createNamedQuery("product.getAll").getResultList();
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
	public long saveProduct(Product obj) {
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
	public Shop getShopById(long id) {
		EntityTransaction entityTransaction = em.getTransaction();
		Shop result = null;
		entityTransaction.begin();
		try {
			result = em.find(Shop.class, id);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		return result;
	}
	public List<Stock> getStockByShop(long shop_id){
		EntityTransaction entityTransaction = em.getTransaction();
		List<Stock> result = null;
		ShopDTO dto =null;
		entityTransaction.begin();
		try {
			Shop shop = em.find(Shop.class, shop_id);
			result = shop.getInventory();
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		
		return result;
	}
	public List<Product> getProductLike(String name){
		EntityTransaction entityTransaction = em.getTransaction();
		List<Product> result = null;
		String namelike;
		namelike= "%"+name+"%";
		entityTransaction.begin();
		try {
			result = em.createNamedQuery("product.getByName", Product.class)
					.setParameter("namelike", namelike)
					.getResultList();
			
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		
		return result;
	}
	public boolean existProduct(String name) {
		EntityTransaction entityTransaction = em.getTransaction();
		boolean result = false;
		List<Product> nproduct = null;
		entityTransaction.begin();
		try {
			nproduct = em.createNamedQuery("product.getByName", Product.class)
					.setParameter("namelike", name).getResultList();	
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		if(nproduct.size()> 0) {
			result = true;
		}
		return result;
		
	}
	public Stock getStockById(Long id) {
		EntityTransaction entityTransaction = em.getTransaction();
		Stock stock = null;
		entityTransaction.begin();
		try {
			stock = em.find(Stock.class, id);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		return stock;
	}
	public Product getProductById(long id) {
		EntityTransaction entityTransaction = em.getTransaction();
		Product product = null;
		entityTransaction.begin();
		try {
			product = em.find(Product.class, id);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
		return product;
	}
	public void saveStock(Stock nstock) {
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		try {
			em.persist(nstock);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
	}
	public void updateStock(Stock nstock) {
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		try {
			em.merge(nstock);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		}
	}
}
