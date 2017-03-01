package logApp.Facility.Connections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Graph {
	public static final String seattle = "Seattle, WA";
	public static final String sanFrancisco = "San Francisco, CA";
	public static final String losAngeles = "Los Angeles, CA";
	public static final String phoenix = "Phoenix, AZ";
	public static final String denver = "Denver, CO";
	public static final String santaFe = "Santa Fe, NM";
	public static final String fargo = "Fargo, ND";
	public static final String austin = "Austin, TX";
	public static final String stLouis = "St. Louis, MO";
	public static final String chicago = "Chicago, IL";
	public static final String newOrleans = "New Orleans, LA";
	public static final String nashville = "Nashville, TN";
	public static final String detroit = "Detroit, MI";
	public static final String boston = "Boston, MA";
	public static final String newYork = "New York City, NY";
	public static final String norfolk = "Norfolk, VA";
	public static final String atlanta = "Atlanta, GA";
	public static final String miami = "Miami, FL";

	private static Map<String, Vertex> graph;
	private String start;

	/** Builds a graph from a set of paths */
	public Graph(List<DirectLinks> links) throws DirectLinksNotFoundException {
		graph = new HashMap<>(links.size());

		// one pass to find all vertices
		for (DirectLinks every : links) {
			if (!graph.containsKey(every.getSource()))
				graph.put(every.getSource(), new Vertex(every.getSource()));
			if (!graph.containsKey(every.getDestination()))
				graph.put(every.getDestination(), new Vertex(every.getDestination()));
		}

		// pass to set neighbouring vertices
		for (DirectLinks every : links) {
			graph.get(every.getSource()).neighbours.put(graph.get(every.getDestination()), every.getDistance());
		}
	}

	
	public void dijkstra(String startCity) throws DirectLinksNotFoundException {
		start = startCity;
		if (!graph.containsKey(startCity)) {
			System.err.printf("doesn't contain start vertex \"%s\"\n", startCity);
			return;
		}
		final Vertex source = graph.get(startCity);
		NavigableSet<Vertex> q = new TreeSet<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.previous = v == source ? source : null;
			v.dist = v == source ? 0 : Integer.MAX_VALUE;
			q.add(v);
		}
		dijkstra(q);
	}

	private void dijkstra(final NavigableSet<Vertex> q) throws DirectLinksNotFoundException {
		Vertex u, v;
		while (!q.isEmpty()) {
			u = q.pollFirst();
			if (u.dist == Integer.MAX_VALUE)
				break;

			for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
				v = a.getKey();
				final int alternateDist = u.dist + a.getValue();
				if (alternateDist < v.dist) {
					q.remove(v);
					v.dist = alternateDist;
					v.previous = u;
					q.add(v);
				}
			}
		}
	}

	public void printPath(String endCity) throws DirectLinksNotFoundException {
		if (!graph.containsKey(endCity)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endCity);
			return;
		}
		System.out.println(start + " to " + endCity + ": ");
		graph.get(endCity).printPath();
		System.out.format(" = " + graph.get(endCity).dist + " mi \n");
		System.out.format("\t\u2022  " + graph.get(endCity).dist + " mi /(8 hours per day * 50 mph) = " + "%,.2f days",
				(graph.get(endCity).dist / (8.0 * 50)));
		System.out.println();
	}

	/**
	 * Prints the path from the source to every vertex (output order is not
	 * guaranteed)
	 * 
	 * @param directLinks
	 */

	public void printAllPaths() {
		for (Vertex v : graph.values()) {
			v.printPath();
			// System.out.println();
		}
	}

	public String toString() {
		return String.format("%s", graph.get(start));
	}

	// public void shortest(){
	//
	//
	// }

	public static void printStartEnd(ArrayList<DirectLinks> facilityNetwork) throws DirectLinksNotFoundException {

		for (DirectLinks l : facilityNetwork) {

			{
				if (l.getSource() != null)

					return;
			}
		}

		Graph g = new Graph(facilityNetwork);

		System.out.println();
		g.dijkstra(santaFe);
		g.printPath(chicago);

		System.out.println();
		g.dijkstra(atlanta);
		g.printPath(stLouis);
		System.out.println();
		g.dijkstra(seattle);
		g.printPath(nashville);
		System.out.println();
		g.dijkstra(newYork);
		g.printPath(phoenix);
		System.out.println();
		g.dijkstra(fargo);
		g.printPath(austin);
		System.out.println();
		g.dijkstra(denver);
		g.printPath(miami);
		System.out.println();
		g.dijkstra(austin);
		g.printPath(norfolk);
		System.out.println();
		g.dijkstra(miami);
		g.printPath(seattle);
		System.out.println();
		g.dijkstra(losAngeles);
		g.printPath(chicago);
		System.out.println();
		g.dijkstra(detroit);
		g.printPath(nashville);
		System.out.println();
		g.dijkstra(miami);
		g.printPath(chicago);
		System.out.println();
		g.dijkstra(miami);
		g.printPath(denver);
		g.dijkstra(miami);
		g.printPath(nashville);

	}

}
