package logApp.Format;

public class NegativeInventoryException extends Exception{
	
	public NegativeInventoryException()
	{
		super();
	}
	
	public NegativeInventoryException(String ItemCatalogId)
	{
		super(ItemCatalogId);
	}

}
