package logApp.Format;

public class FacilityRecord implements Comparable<FacilityRecord> {

	private String name;
	private int numOfItemsAvailible;
	private int startDay;
	private int processingTime;
	private int TT;
	private int itemsProcessed;

	public FacilityRecord(String name, int numOfItemsAvailible, int startDay, int processingTime, int TT) {
		this.name = name.trim();
		this.numOfItemsAvailible = numOfItemsAvailible;
		this.startDay = startDay;
		this.processingTime = processingTime;
		this.TT = TT;
		itemsProcessed = 0;
	}

	public String getFacilityCity() {
		return name;
	}

	public boolean processItems(int quantity) throws NegativeInventoryException {
		if (quantity < 0 || (itemsProcessed + quantity) > numOfItemsAvailible) {
			throw new NegativeInventoryException();
		}
		itemsProcessed += quantity;
		return true;

	}

	public int getNumOfItemsProcessed() {
		return itemsProcessed;
	}

	public int getNumOfItemsAvailible() {
		return numOfItemsAvailible;
	}

	public int getProcessingTime() {
		return processingTime;
	}

	public int getPED() {
		return startDay + processingTime;
	}

	public int getTT() {
		return TT;
	}

	public int getAD() {
		return TT + processingTime + startDay;
	}

	public int compareTo(FacilityRecord other) {
		if (getAD() > other.getAD())
			return 1;
		if (getAD() < other.getAD())
			return -1;
		return 0;
	}

}
