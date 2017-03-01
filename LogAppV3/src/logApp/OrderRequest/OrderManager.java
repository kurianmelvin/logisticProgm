package logApp.OrderRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderManager {
	private static volatile OrderManager instance;

	private ArrayList<Order> orders;

	/**
	 * 
	 * private constructor to prevent client from instantiating.
	 */
	private OrderManager() {

		// to prevent instantiating by Reflection call
		if (instance != null) {
			throw new IllegalStateException("Already initialized.");
		}

		orders = OrderInformationLoader.read();

	}

	/**
	 * Public accessor.
	 *
	 * @return an instance of the class.
	 */
	public static OrderManager getInstance() {

		OrderManager result = instance;
		if (result == null) {
			synchronized (OrderManager.class) {
				result = instance;
				if (result == null) {
					instance = result = new OrderManager();
				}
			}
		}
		return result;
	}

	public ArrayList<String> getOrderIds() {
		ArrayList<String> info = new ArrayList<String>();
		for (Order o : orders) {
			info.add(o.getId());
		}
		return info;
	}

	public HashMap<String, Integer> getInfo(String orderId) throws OrderInformationInAccessibleException {
		Order data = findByString(orderId);
		ArrayList<ItemQuantity> items = data.getInfo();
		HashMap<String, Integer> info = new HashMap<String, Integer>();
		for (ItemQuantity i : items) {
			info.put(i.getId(), i.getQty());
		}
		return info;
	}

	public String getDestination(String orderId) throws OrderInformationInAccessibleException {
		Order d = findByString(orderId);
		return d.getDestination();
	}

	public int getDay(String orderId) throws OrderInformationInAccessibleException {
		Order data = findByString(orderId);
		return data.getDay();
	}

	private Order findByString(String data) throws OrderInformationInAccessibleException {
		for (Order o : orders) {
			if (o.getId().equalsIgnoreCase(data))
				return o;
		}

		throw new OrderInformationInAccessibleException();
	}

}
