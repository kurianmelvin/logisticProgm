package logApp.Facility.Facility;

public class ScheduleFactory {

	public static Schedule makeSchedule(String identifier, int itemsPerDay)
	{
		return new ScheduleImpl(itemsPerDay);
	}
}
