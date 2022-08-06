package managers;

import java.util.ArrayList;
import java.util.List;

import callbacks.CallbackStock;
import dto.ShopDTO;
import dto.StockDTO;
import model.Product;
import model.Shop;
import model.Stock;

public class StockManager {
	private static StockManager instance;

	private StockManager() {
		super();
	}

	public static StockManager getIntenace() {
		if (instance == null) {
			instance = new StockManager();
		}
		return instance;
	}
	public void saveStock(StockDTO ndto, long product_id, long shop_id) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Product nProduct = ProductManager.getIntenace().getProductById(product_id);
				Shop nShop = ShopManager.getIntenace().getShopById(shop_id);
				Stock nstock = new Stock();
				nstock.setPrice(ndto.getPrice());
				nstock.setAmount(ndto.getAmount());
				nstock.setShop(nShop);
				nstock.setProduct(nProduct);
				PersistenceManager.getIntenace().saveStock(nstock);
				
			}};
		runnable.run();	
	}
	
	public void updateStock(StockDTO ndto, long product_id,long shop_id) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Product nProduct = ProductManager.getIntenace().getProductById(product_id);
				Stock nstock = PersistenceManager.getIntenace().getStockById(ndto.getId());
				Shop nShop = ShopManager.getIntenace().getShopById(shop_id);
				
				nstock.setPrice(ndto.getPrice());
				nstock.setAmount(ndto.getAmount());
				nstock.setProduct(nProduct);
				nstock.setShop(nShop);
				PersistenceManager.getIntenace().updateStock(nstock);
				
			}};
		runnable.run();	
	}

	public void getStockById(CallbackStock callback, Long id) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}};
		runnable.run();
	}
	public void getStockByShop(CallbackStock callback, ShopDTO shop) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				List<StockDTO> listDto = new ArrayList<StockDTO>();
				List<Stock> listStock = PersistenceManager.getIntenace().getStockByShop(shop.getId());
				for (Stock stock : listStock) {
					StockDTO nstock = new StockDTO();
					nstock.setAmount(stock.getAmount());
					nstock.setId(stock.getId());
					nstock.setPrice(stock.getPrice());
					// buscar
					nstock.setProduct(ProductManager.getIntenace().getDTO(stock.getProduct()));
					nstock.setShop(shop);
					listDto.add(nstock);
				}

				callback.getStockByShop(listDto);
			}
		};
		runnable.run();
	}
	public StockDTO getStockById(Long id) {
		StockDTO nStockDTO = new StockDTO();
		Stock stock = PersistenceManager.getIntenace().getStockById(id);
		nStockDTO.setId(stock.getId());
		nStockDTO.setAmount(stock.getAmount());
		nStockDTO.setPrice(stock.getPrice());
		nStockDTO.setShop(ShopManager.getIntenace().getDTO(stock.getShop()));
		nStockDTO.setProduct(ProductManager.getIntenace().getDTO(stock.getProduct()));
		return nStockDTO;
	}
}
