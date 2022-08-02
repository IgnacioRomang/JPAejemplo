package managers;

public class StockManager {
	private static StockManager instance;
	
	private StockManager(){
		super();
	}
	public static StockManager getIntenace() {
		if(instance==null) {
			instance = new StockManager();
		}
		return instance;
	}
}
