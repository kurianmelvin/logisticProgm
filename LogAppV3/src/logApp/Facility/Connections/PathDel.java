package logApp.Facility.Connections;

import java.util.ArrayList;
import java.util.HashMap;

public class PathDel {
	
	private static ArrayList<Links> links;
	private static HashMap<Links,Integer> visPath;
	private static ArrayList<Links> notVisPath;
	
	public static int shortestPath(ArrayList<Links> facilityNetwork,Links origin, Links destination)
	{
		links=facilityNetwork;
		visPath = new HashMap<Links,Integer>();
		notVisPath = new ArrayList<Links>();
		for(Links n:facilityNetwork)
		{
			notVisPath.add(n);
			visPath.put(n, Integer.MAX_VALUE);
		}
		
		visPath.put(origin, 0);
		notVisPath.remove(origin);
		
		Links current = origin;
		go(current);
		while(notVisPath.contains(destination))
		{
			go(minVisit());
		}
		
		return visPath.get(destination);
		
		
	}
	
	
	private static void go(Links current)
	{
		for(DirectLinks c:current.getConnections())
		{
			int d = c.getDistance();
			Links data = returnNodeByName(c.getDestination());
			
			int newPathVisit = d+visPath.get(current);
			if(newPathVisit<visPath.get(data))
				visPath.put(data, newPathVisit);
			
			
		}
		
		notVisPath.remove(current);
	}
	
	
	private static Links returnNodeByName(String name)
	{
		for(Links n:links)
		{
			if(n.getFacilityCity().equalsIgnoreCase(name))
				return n;
		}
		
		return null;
	}
	
	private static Links minVisit()
	{
		int min = Integer.MAX_VALUE;
		Links info=null;
		for(Links n:notVisPath)
		{
			if(visPath.get(n)<min)
			{
				min=visPath.get(n);
				info=n;
			}
			
		}
		
		return info;
	}
	

}
