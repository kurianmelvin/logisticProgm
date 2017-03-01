package logApp.Facility.Facility;

public interface Schedule {
	
	public int processRatePerDayCounter(int startingDay,int quantity);
	public boolean orderTime(int startingDay, int quantity);
	public void print();

}
