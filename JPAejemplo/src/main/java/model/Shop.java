package model;

import static javax.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy = AUTO )
	private long id;
	@Basic
	private String name;
	@OneToMany(mappedBy = "")
	private List<Stock> inventory;
	private long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Shop(int id, String name ) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = new ArrayList<Stock>();
	}
	public Shop() {
		super();
	}
	public Shop(long id, String name, List<Stock> inventory) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = inventory;
	}
	
	public Shop(String name, List<Stock> inventory) {
		super();
		this.name = name;
		this.inventory = inventory;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Stock> getInventory() {
		return inventory;
	}
	public void setInventory(List<Stock> inventory) {
		this.inventory = inventory;
	}
	public void addItem(Stock item) {
		this.inventory.add(item);
	}
}
