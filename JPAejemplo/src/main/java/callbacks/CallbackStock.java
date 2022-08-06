package callbacks;
import java.util.List;

import dto.StockDTO;
public interface CallbackStock {
	public void getStockByShop(List<StockDTO> listDTO);
}
