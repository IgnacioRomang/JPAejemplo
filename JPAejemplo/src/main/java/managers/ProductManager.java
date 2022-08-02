package managers;

import dto.ProductDTO;

public class ProductManager {
	public static ProductManager instance;
	
	
	private ProductManager(){
		super();
	}
	public ProductManager getIntenace() {
		if(instance==null) {
			instance = new ProductManager();
		}
		return instance;
	}
	public Long addNewProduct(ProductDTO productDTO) {
		return null;
	}
}
