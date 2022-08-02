package dto;


import java.util.ArrayList;
import java.util.List;
import model.Stock;

public class ShopDTO {
	private long id;
	private String name;
	private List<StockDTO> inventory;
	public ShopDTO() {
		super();
		inventory = new ArrayList<StockDTO>();
	}
	public ShopDTO(long id, String name, List<StockDTO> inventory) {
		super();
		this.id = id;
		this.name = name;
		this.inventory = inventory;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<StockDTO> getInventory() {
		return inventory;
	}
	public void setInventory(List<StockDTO> inventory) {
		this.inventory = inventory;
	}
}
