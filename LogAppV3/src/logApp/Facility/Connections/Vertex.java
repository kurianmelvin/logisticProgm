package logApp.Facility.Connections;

import java.util.HashMap;
import java.util.Map;

/** One vertex of the graph, complete with mappings to neighbouring vertices */
public class Vertex implements Comparable<Vertex> {
	public final String name;
	public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();

	public Vertex(String name) {
		this.name = name;
	}

	void printPath() {
		if (this == this.previous) {
			// System.out.println( this.name+" to ");
			 System.out.printf("\t\u2022  %s ", this.name);
		} else if (this.previous == null) {
			System.out.printf("%s(unreached)", this.name);
		} else {
			this.previous.printPath();
			System.out.printf(" -> %s ", this.name);
//			System.out.println(" -> " + this.name + " (" + this.dist + ")");
		}	 
	}
	public int compareTo(Vertex other) {
		if (dist == other.dist)
			return name.compareTo(other.name);
		return Integer.compare(dist, other.dist);
	}
}