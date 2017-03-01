package logApp.ItemCatalog;

import java.util.ArrayList;

public class ItemManager {
	private static volatile ItemManager instance;

	private ArrayList<Item> catalogue;

	/**
	 * 
	 * private constructor to prevent client from instantiating.
	 */
	private ItemManager() {

		// to prevent instantiating by Reflection call
		if (instance != null) {
			throw new IllegalStateException("Already initialized.");
		}

		catalogue = ItemCatalogLoader.gatherInput();

	}

	/**
	 * Public accessor.
	 *
	 * @return an instance of the class.
	 */
	public static ItemManager getInstance() {

		ItemManager result = instance;
		if (result == null) {
			synchronized (ItemManager.class) {
				result = instance;
				if (result == null) {
					instance = result = new ItemManager();
				}
			}
		}
		return result;
	}

	public ArrayList<String> getItemList() {

		ArrayList<String> info = new ArrayList<String>();
		for (Item i : catalogue) {
			info.add(i.getId());
		}
		return info;
	}

	public boolean isItem(String id) {
		for (Item i : catalogue) {
			if (i.getId().equalsIgnoreCase(id))
				return true;
		}

		return false;

	}

	public int getCostPerDay(String id) throws ActiveInventoryNotFoundException {

		for (Item i : catalogue) {
			if (i.getId().equalsIgnoreCase(id))
				return i.getCostPerDay();
		}

		throw new ActiveInventoryNotFoundException(id);

	}


}
