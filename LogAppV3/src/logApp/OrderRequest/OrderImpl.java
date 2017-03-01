package logApp.OrderRequest;

import java.util.ArrayList;

public class OrderImpl implements Order {
	
	
	private String id;
	private int day;
	private String destination;
	private ArrayList<ItemQuantity> loadInfo;
	
	public OrderImpl(String id, int day, String destination, ArrayList<ItemQuantity> loadInfo)
	{
		this.id=id;
		this.day=day;
		this.destination=destination;
		this.loadInfo=loadInfo;
	}


	public String getId() {
		return id;
	}

	public int getDay() {
		return day;
	}


	public String getDestination() {
		return destination;
	}


	public ArrayList<ItemQuantity> getInfo() {
		return loadInfo;
	}

}
