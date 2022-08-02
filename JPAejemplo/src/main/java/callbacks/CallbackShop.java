package callbacks;

import java.util.List;

import dto.ShopDTO;

public interface CallbackShop {
	public void createShop(ShopDTO shop);
	public void startTable(List<ShopDTO> lista);
}
