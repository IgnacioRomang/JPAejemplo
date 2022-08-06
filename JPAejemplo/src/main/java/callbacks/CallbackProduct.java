package callbacks;

import java.util.List;

import dto.ProductDTO;

public interface CallbackProduct {
	public void findProduct(List<ProductDTO> result);
}
