package managers;


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
	public ProductManager addNewProduct() {
		return null;
	}
}
