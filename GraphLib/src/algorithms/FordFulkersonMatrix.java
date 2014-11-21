package algorithms;

import java.util.LinkedList;

import graph.Edge;
import graph.Vertex;

public class FordFulkersonMatrix {

	Vertex source;
	Vertex destination;
	LinkedList<FFEdge> edges;
	
	public FordFulkersonMatrix(Vertex sour, Vertex dest, LinkedList<Edge> edg) {
		this.source = sour;
		this.destination = dest;
		for(Edge e : edg){
			this.edges.add(new FFEdge(e.getWeight(),0));
		}
	}
	
	public void go(){
		LinkedList<Vertex> path =  findPath(this.source, this.destination);
		int minWeight;
		
		while(!path.isEmpty()){
			minWeight = findMinWeight(path);
			for(FFEdge ffe : this.edges){
				if(ffe.weight == minWeight) continue;
				ffe.flow+= (ffe.weight - minWeight);
			}
			path = findPath(this.source, this.destination);
		}
	}

	private int findMinWeight(LinkedList<Vertex> path) {
		// TODO Auto-generated method stub
		return 0;
	}

	private LinkedList<Vertex> findPath(Vertex source2, Vertex destination2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class FFEdge{
	int weight;
	int flow;
	int begin;
	int end;
	
	public FFEdge(int wei, int initFlow) {
		this.weight = wei;
		this.flow = initFlow;
	}
}