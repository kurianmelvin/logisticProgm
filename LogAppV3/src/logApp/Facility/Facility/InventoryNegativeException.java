package logApp.Facility.Facility;

public class InventoryNegativeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryNegativeException()
	{
		super();
	}
	
	public InventoryNegativeException(String ItemCatalogId)
	{
		super(ItemCatalogId);
	}

}
