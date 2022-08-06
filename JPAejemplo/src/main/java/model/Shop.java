package model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.LockModeType.READ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@Cacheable
@NamedQuery(name = "shop.getAll", query = "SELECT p FROM Shop p")
public class Shop implements Serializable {

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	private String name;
	@OneToMany(mappedBy = "shop", fetch = LAZY)
	private List<Stock> inventory;

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", inventory=" + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Shop(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = new ArrayList<Stock>();
	}

	public Shop(String name) {
		super();
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
