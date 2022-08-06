package managers;

import java.util.ArrayList;
import java.util.List;

import callbacks.CallbackShop;
import dto.ProductDTO;
import dto.ShopDTO;
import dto.StockDTO;
import model.Product;
import model.Shop;
import model.Stock;

public class ShopManager {
	// TODO HACER QUE GESTIONE ARBOLES DE BUSQUEDAS
	private static ShopManager instance;

	private ShopManager() {
		super();
	}

	public static ShopManager getIntenace() {
		if (instance == null) {
			instance = new ShopManager();
		}
		return instance;
	}

	public void getAllShop(CallbackShop callback) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				List<Shop> listShop = PersistenceManager.getIntenace().getAllShop();
				ArrayList<ShopDTO> dtoShop = new ArrayList<ShopDTO>();
				for (Shop nshop : listShop) {
					ShopDTO nshopDto = new ShopDTO();
					nshopDto.setId(nshop.getId());
					nshopDto.setName(nshop.getName());
					dtoShop.add(nshopDto);
				}
				callback.startTable(dtoShop);
			}
		};
		runnable.run();

	}

	public void addNewShop(CallbackShop callback, ShopDTO shopDTO) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				PersistenceManager persistence = PersistenceManager.getIntenace();
				Shop nshop = new Shop();
				nshop.setName(shopDTO.getName());
				nshop.setInventory(new ArrayList<>());
				long id = persistence.saveShop(nshop);
				shopDTO.setId(id);
				callback.createShop(shopDTO);
			}
		};
		runnable.run();
	}

	public ShopDTO getDTO(Shop shop) {
		ShopDTO nShopDTO = new ShopDTO();
		nShopDTO.setId(shop.getId());
		nShopDTO.setName(shop.getName());
		return nShopDTO;
	}

	public Shop getShopById(long shop_id) {
		
		return PersistenceManager.getIntenace().getShopById(shop_id);
	}

}
