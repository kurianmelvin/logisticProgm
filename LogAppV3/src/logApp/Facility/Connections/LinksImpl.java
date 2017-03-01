package logApp.Facility.Connections;

import java.util.ArrayList;

public class LinksImpl implements Links {
	

	private String name;
	ArrayList<DirectLinks> connections;
	
	public LinksImpl(String name,ArrayList<DirectLinks> connections)
	{
		this.name=name.trim();
		this.connections=connections;
	}
	
	public String getFacilityCity()
	{
		return name.trim();
	}
	

	
	public ArrayList<DirectLinks> getConnections()
	{
		return connections;
	}

}
