package logApp.Facility.Connections;

import java.util.ArrayList;

public class LinksFactory {
	
	

	
	public static Links generateNode(String identifier, String name, ArrayList<DirectLinks> connections)
	{	
			return new LinksImpl(name,connections);
	}

}
