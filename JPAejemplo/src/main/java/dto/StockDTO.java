package dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import model.Product;
import model.Shop;

public class StockDTO {
	private long id;
	private ProductDTO product;
	
	private double price;
	private int amount;
	public StockDTO() {
		super();
	}
	public StockDTO(long id, ProductDTO product, double price, int amount, ShopDTO shop) {
		super();
		this.id = id;
		this.product = product;
		this.price = price;
		this.amount = amount;
		this.shop = shop;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
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
	public ShopDTO getShop() {
		return shop;
	}
	public void setShop(ShopDTO shop) {
		this.shop = shop;
	}
	private ShopDTO shop;
}
