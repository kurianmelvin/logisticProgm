package logApp.Facility.Connections;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class DirectLinksLoader {
	
	
	public static ArrayList<logApp.Facility.Connections.Links> readNetwork()
	{
		ArrayList<logApp.Facility.Connections.Links> info=new ArrayList<logApp.Facility.Connections.Links>();
		try
		{
		
		File fileName = new File("DirectLinks.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(fileName);
		
		NodeList nList = doc.getElementsByTagName("Facility");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					

					
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				String name = eElement.getAttribute("Name");
				NodeList orderList = eElement.getElementsByTagName("DirectLinks");
				ArrayList<DirectLinks> c = new ArrayList<DirectLinks>();
				
				for (int subTemp = 0; subTemp < orderList.getLength(); subTemp++)
				{
					Node sNode = orderList.item(subTemp);
					
					if (sNode.getNodeType() == Node.ELEMENT_NODE) {

						Element sElement = (Element) sNode;
						String t = sElement.getAttribute("ToCity");
						int distance = Integer.parseInt(sElement.getAttribute("Distance"));
						c.add(new DirectLinksImpl(name,t,distance));
				}
				}
				

				info.add(LinksFactory.generateNode("", name, c));

			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return info;
		
	}
	}


