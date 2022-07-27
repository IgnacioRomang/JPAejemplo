package model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock implements Serializable{
	@Id
	@GeneratedValue(strategy = AUTO )
	private long id;
	@OneToOne
	private Product product;
	private double price;
	private int amount;
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
	public Stock(int id,Product product, double price, int amount) {
		super();
		this.id = id;
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
