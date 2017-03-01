package logApp.Facility.Facility;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FacilityLoader {

	public static ArrayList<Facility> gatherInput() {
		ArrayList<Facility> info = new ArrayList<Facility>();

		try {

			File fileName = new File("Facility.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);

			NodeList nList = doc.getElementsByTagName("Facility");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = eElement.getAttribute("Name");
					int cost = Integer.parseInt(eElement.getAttribute("Cost"));
					int rate = Integer.parseInt(eElement.getAttribute("Rate"));
					Facility toAdd = FacilityFactory.generateFacility("", name, cost, rate);

					NodeList orderList = eElement.getElementsByTagName("Item");
					for (int subTemp = 0; subTemp < orderList.getLength(); subTemp++) {
						Node sNode = orderList.item(subTemp);

						if (sNode.getNodeType() == Node.ELEMENT_NODE) {

							Element sElement = (Element) sNode;
							String id = sElement.getElementsByTagName("Id").item(0).getTextContent();
							int quantity = Integer.parseInt(sElement.getElementsByTagName("quantity").item(0).getTextContent());
							toAdd.addInventory(id, quantity);

						}
					}

					info.add(toAdd);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
