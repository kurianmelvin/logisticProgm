package logApp.Facility.Facility;

public class FacilityFactory {
	
	public static Facility generateFacility(String identifier, String name, int cost, int processingRate)
	{
		return new FacilityImpl(name,cost,processingRate);
	}

}
