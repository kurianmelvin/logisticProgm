package logApp.OrderRequest;

public class OrderItemFactory {

	
	
	public static ItemQuantity makeItems(String id, int quantity)
	{
		return new ItemQuantityImpl(id,quantity);
	}
}
