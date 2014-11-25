package algorithms;

import graph.Edge;
import graph.Vertex;

public class incidentEdgeVertex {
	Vertex vertex;
	Edge edge;
	public incidentEdgeVertex(Vertex v, Edge e) {
		this.vertex=v;
		this.edge=e;
	}
	public incidentEdgeVertex(Vertex v) {
		this.vertex=v;
	}
}
