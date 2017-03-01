package logApp.ItemCatalog;

public class ItemFactory {
	
	
	public static Item generateItem(String identifier,String id,int cost)
	{
		return new ItemImpl(id,cost);
	}

}
