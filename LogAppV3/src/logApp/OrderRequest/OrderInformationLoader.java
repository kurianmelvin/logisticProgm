package logApp.OrderRequest;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrderInformationLoader {

	public static ArrayList<Order> read() {
		ArrayList<Order> info = new ArrayList<Order>();

		try {
			File fileName = new File("Orders.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileName);

			NodeList nList = doc.getElementsByTagName("Order");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String id = eElement.getAttribute("id");
					String destination = eElement.getAttribute("Destination");
					int day = Integer.parseInt(eElement.getAttribute("Day"));
					ArrayList<ItemQuantity> loadInfo = new ArrayList<ItemQuantity>();

					NodeList orderList = eElement.getElementsByTagName("Item");
					for (int subTemp = 0; subTemp < orderList.getLength(); subTemp++) {
						Node sNode = orderList.item(subTemp);

						if (sNode.getNodeType() == Node.ELEMENT_NODE) {

							Element sElement = (Element) sNode;
							String orderItemId = sElement.getElementsByTagName("Id").item(0).getTextContent();
							int orderItemQuantity = Integer
									.parseInt(sElement.getElementsByTagName("quantity").item(0).getTextContent());
							loadInfo.add(OrderItemFactory.makeItems(orderItemId, orderItemQuantity));

						}
					}
					info.add(OrderFactory.generateOrder(id, day, destination, loadInfo));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
