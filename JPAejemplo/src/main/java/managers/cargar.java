package managers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Product;
import model.Shop;
import model.Stock;

public class cargar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAejemplo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		String namelike = "%o%";
		List<Product> lis= null;
		et.begin();
		try {
			lis = em.createNamedQuery("shop.getAll").getResultList();
			et.commit();
		}catch (Exception e) {
			et.rollback();
		}
		System.out.println(lis.toString()); 
		
		/*
		long id = 101;
		Product lis= null;
		et.begin();
		try {
			lis = (Product) em.createNamedQuery("product.getById").setParameter("id", id).getSingleResult();
			et.commit();
		}catch (Exception e) {
			et.rollback();
		}
		System.out.println(lis.toString()); 
		*/
		/*
		Product nuevo = new Product(2348965, "Sopa");
		Product nuevo2 = new Product(4258863, "Fideos");
		Product nuevo3 = new Product(4178378, "Arroz");
		Product nuevo4 = new Product(1743737, "Melon");

		Shop shop1 = new Shop("Koko");
		Shop shop2 = new Shop("koko1");

		Stock stock1 = new Stock(nuevo, shop1, 63.21, 652);
		Stock stock2 = new Stock(nuevo, shop1, 2.5, 100);
		Stock stock3 = new Stock(nuevo2, shop1, 50.6, 625);
		Stock stock4 = new Stock(nuevo3, shop2, 81.2, 348);
		Stock stock5 = new Stock(nuevo4, shop2, 321.05, 987);

		List<Stock> inv1 = new ArrayList<>();
		inv1.add(stock1);
		inv1.add(stock2);
		inv1.add(stock3);
		List<Stock> inv2 = new ArrayList<>();
		inv2.add(stock4);
		inv2.add(stock5);

		shop1.setInventory(inv1);
		shop2.setInventory(inv2);
		System.out.println("<--------------------------------------------------------------->");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			em.persist(nuevo);
			em.persist(nuevo2);
			em.persist(nuevo3);
			em.persist(nuevo4);
			em.persist(stock1);
			em.persist(stock2);
			em.persist(stock3);
			em.persist(stock4);
			em.persist(stock5);
			em.persist(shop1);
			em.persist(shop2);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		em.close();
		emf.close();
		*/

	}

}
