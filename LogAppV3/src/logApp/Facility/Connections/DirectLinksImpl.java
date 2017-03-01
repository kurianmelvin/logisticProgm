package logApp.Facility.Connections;

public class DirectLinksImpl implements DirectLinks{
	
	private String source;
	private String destination;
	private int distance;
	
	
	public DirectLinksImpl(String source,String destination, int distance)
	{
		this.source=source;
		this.destination=destination;
		this.distance=distance;
	}
	
	public String getSource()
	{
		return source;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public int getDistance()
	{
		return distance;
	}

}
