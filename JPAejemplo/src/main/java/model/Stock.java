package model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.OneToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "stock.getByShop", query = "SELECT s FROM Stock s WHERE s.shop = :shop")
public class Stock implements Serializable {
	@Override
	public String toString() {
		return "Stock [id=" + id + ", product=" + product + ", price=" + price + ", amount=" + amount + ", shop=" + shop
				+ "]";
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	
	@OneToOne
	private Product product;
	
	private double price;
	private int amount;
	@ManyToOne
	private Shop shop;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Stock(int id, Product product, Shop shop, double price, int amount) {
		super();
		this.id = id;
		this.shop = shop;
		this.product = product;
		this.price = price;
		this.amount = amount;
	}

	public Stock(Product product, double price, int amount) {
		super();
		this.product = product;
		this.price = price;
		this.amount = amount;
	}

	public Stock(Product product, Shop shop, double price, int amount) {
		super();
		this.shop = shop;
		this.product = product;
		this.price = price;
		this.amount = amount;
	}

	public Stock() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
