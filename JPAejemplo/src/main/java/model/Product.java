package model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;
import static javax.persistence.LockModeType.READ;
import javax.persistence.Access;
import static javax.persistence.AccessType.FIELD;
import static javax.persistence.AccessType.PROPERTY;
import javax.persistence.QueryHint;
import static javax.persistence.LockModeType.WRITE;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "product.getAll", query = "SELECT p FROM Product p"), 
	@NamedQuery(name = "product.getById", query = "SELECT p FROM Product p WHERE p.id= :id", lockMode = READ),
	@NamedQuery(name = "product.getByName", query = "SELECT p FROM Product p WHERE p.name LIKE :namelike")
})
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	@Basic
	private long code;
	@Basic
	private String name;
	
	public List<Stock> getStockIn() {
		return stockIn;
	}

	public void setStockIn(List<Stock> stockIn) {
		this.stockIn = stockIn;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "product")
	private List<Stock> stockIn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product() {
		super();
		this.stockIn = new ArrayList<Stock>();
	}

	public Product(Long id, long code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Product(long code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", stockIn=" + "]";
	}
	
}