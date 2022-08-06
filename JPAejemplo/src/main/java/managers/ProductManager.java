package managers;

import java.util.ArrayList;
import java.util.List;

import callbacks.CallbackProduct;
import dto.ProductDTO;
import model.Product;

public class ProductManager {
	private static ProductManager instance;
	
	
	private ProductManager(){
		super();
	}
	public static ProductManager getIntenace() {
		if(instance==null) {
			instance = new ProductManager();
		}
		return instance;
	}
	public Long addNewProduct(ProductDTO productDTO) {
		return null;
	}
	public ProductDTO getDTO(Product product) {
		ProductDTO result = new ProductDTO();
		result.setName(product.getName());
		result.setId(product.getId());
		result.setCode(product.getCode());
		return result;
	} 
	public void getProductsLike(CallbackProduct callback,String name){
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				List<Product> list = PersistenceManager.getIntenace().getProductLike(name);
				List<ProductDTO> result = new ArrayList<>();
				for(Product selected : list) {
					result.add(ProductManager.getIntenace().getDTO(selected));
				}
				callback.findProduct(result);
			}
		};
		runnable.run();
	}
	public boolean exist(String name) {
		return PersistenceManager.getIntenace().existProduct(name);
	}
	public void saveProduct(ProductDTO dto) {
		Product nproduct = new Product();
		nproduct.setName(dto.getName());
		nproduct.setCode(dto.getCode());
		long id = PersistenceManager.getIntenace().saveProduct(nproduct);
		dto.setId(id);
	}
	public List<ProductDTO> getAll(){
		List<ProductDTO> listdto= new ArrayList<>();
		List<Product> list = PersistenceManager.getIntenace().getAll();
		ProductDTO nDTO=null;
		for(Product product: list) {
			nDTO = this.getDTO(product);
			listdto.add(nDTO);
		}
		return listdto;
	}
	public Product getProductById(long id) {
		return PersistenceManager.getIntenace().getProductById(id);
	}
}
