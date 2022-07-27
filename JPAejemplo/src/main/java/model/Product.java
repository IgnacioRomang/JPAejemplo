package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = AUTO )
	private Long id;
	@Basic
	private long code;
	@Basic
	private String name;

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
}