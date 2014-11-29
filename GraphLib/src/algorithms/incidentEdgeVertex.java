package algorithms;

import graph.Edge;
import graph.Vertex;

public class incidentEdgeVertex {
	Vertex invertex;
	Vertex outvertex;
	Edge edge;
	int flow;
	
	public incidentEdgeVertex(Vertex v, Vertex o,Edge e, int flow) {
		this.invertex=v;
		this.outvertex = o;
		this.edge=e;
		this.flow = flow;
	}
	
	//erase this
	public incidentEdgeVertex(Vertex v, Edge e) {
		this.invertex=v;
		this.edge=e;
		this.flow = flow;
	}
	public void setWeight(int x){
		this.edge.setWeight(x);
	}
	public void setFlow(int x){
		this.flow = x;
	}
//	public incidentEdgeVertex(Vertex v) {
//		this.invertex=v;
//	}
}
