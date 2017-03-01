package logApp.Facility.Connections;

import java.util.ArrayList;
import java.util.List;

public class DirectLinksManager {
	 private static volatile DirectLinksManager instance ;
	 private static ArrayList<DirectLinks> directLinks;
		private static ArrayList<Links> facilityNetwork;
		 private DirectLinksManager() {
			 if (instance != null) {
				 throw new IllegalStateException("Already initialized.");
			 }
		facilityNetwork = DirectLinksLoader.readNetwork();
			
		
		
		 }

		 public static DirectLinksManager getInstance() {

			 DirectLinksManager result = instance;
			 if (result == null) {
				 synchronized (DirectLinksManager.class) {
					 result = instance;
					 if (result == null) {
						 instance = result = new DirectLinksManager();
					 }
				 }
			 }
			 return result;
		 }
	
	private static Links returnNodeByName(String name) throws FacilityInformationErrorException
	{
		for(Links n:facilityNetwork)
		{
			
				return n;
		}
		
		throw new FacilityInformationErrorException(name);
	}
	
	public String getLinks(String facility) throws FacilityInformationErrorException
	{
		Links n = returnNodeByName(facility);
		ArrayList<DirectLinks> connections = n.getConnections();
		String info = "";
		for (DirectLinks c:connections)
		{
			info+=c.getDestination()+" ("+c.getDistance()+"),  ";
		}
		return info;
	}
	
	public double shortestPath(String origin, String destination) throws FacilityInformationErrorException
	{
		return PathDel.shortestPath(facilityNetwork, returnNodeByName(origin), returnNodeByName(destination));
	}
	
	public double shortestPathDays(String origin, String destination,int drivingHoursPerDay,int averageMPH) throws FacilityInformationErrorException
	{
		return (shortestPath(origin,destination)/(drivingHoursPerDay*averageMPH));
	}
	
	 public List<DirectLinks> getLinks() throws DirectLinksNotFoundException {
		 directLinks = new ArrayList<DirectLinks>();
		 for (DirectLinks dl: directLinks)
		 Graph.printStartEnd(directLinks);
		 return null;
		
		 }
	
	
	
	

}
