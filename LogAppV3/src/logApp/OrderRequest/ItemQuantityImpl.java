package logApp.OrderRequest;

public class ItemQuantityImpl implements ItemQuantity {

	
	private String id;
	private int quantity;
	
	public ItemQuantityImpl(String id, int quantity)
	{
		this.id=id;
		this.quantity=quantity;
	}
	

	public String getId() {
		return id;
	}

	
	public int getQty() {
		return quantity;
	}

}
