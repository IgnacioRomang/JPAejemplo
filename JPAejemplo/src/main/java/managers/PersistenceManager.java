package managers;

public class PersistenceManager {
	public static PersistenceManager instance;
	
	private PersistenceManager(){
		super();
	}
	public PersistenceManager getIntenace() {
		if(instance==null) {
			instance = new PersistenceManager();
		}
		return instance;
	}
}
