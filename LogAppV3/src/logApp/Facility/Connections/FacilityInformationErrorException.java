package logApp.Facility.Connections;

public class FacilityInformationErrorException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4302694781702168495L;

	public FacilityInformationErrorException()
	{
		super();
	}
	
	public FacilityInformationErrorException(String facilityName)
	{
		super(facilityName);
	}


}
