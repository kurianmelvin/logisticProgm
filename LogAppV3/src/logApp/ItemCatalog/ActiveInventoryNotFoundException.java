package logApp.ItemCatalog;

public class ActiveInventoryNotFoundException extends Exception{
	
	public ActiveInventoryNotFoundException(){ super(); }
	public ActiveInventoryNotFoundException(String id){super(id);}
	 

}
