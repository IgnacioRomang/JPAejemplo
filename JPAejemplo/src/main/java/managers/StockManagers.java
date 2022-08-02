package managers;

public class StockManagers {
	private static StockManagers instance;
	
	private StockManagers(){
		super();
	}
	public static StockManagers getIntenace() {
		if(instance==null) {
			instance = new StockManagers();
		}
		return instance;
	}
}
