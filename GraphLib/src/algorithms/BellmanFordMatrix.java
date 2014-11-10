package algorithms;

import representation.MatrixReprGraph;
import graph.Edge;
import graph.Vertex;

public class BellmanFordMatrix {
	
	public Vertex[] previous;
	public int[] distance;
	public MatrixReprGraph graph;
	int infinity = 100000000;//Integer.MAX_VALUE;
	
	public BellmanFordMatrix(MatrixReprGraph graph, int size){
		this.previous = new Vertex[size];
		this.distance = new int [size];
		this.graph = graph;
	}
	public void go(Vertex source){
		for(Vertex vertex : graph.hashVertices.keySet()){
			if(vertex.equals(source)){
				distance[graph.hashVertices.get(vertex)] = 0;
			}
			else{
				distance[graph.hashVertices.get(vertex)] = this.infinity;
				previous[graph.hashVertices.get(vertex)] = null;
			}
		}
		int w = 0;
		for(Vertex v : graph.hashVertices.keySet()){
			for(Vertex v1 : graph.hashVertices.keySet()){
				for(Vertex v2 : graph.hashVertices.keySet()){
					Edge e = graph.getEdge(v1, v2);
					if(e!=null){
						w = e.getWeight();
						if(distance[graph.hashVertices.get(v1)] + w < distance[graph.hashVertices.get(v2)]){
							distance[graph.hashVertices.get(v2)] = distance[graph.hashVertices.get(v1)] + w;
							previous[graph.hashVertices.get(v2)] = v1;
						}
					}
				}
			}
		}
		//checking negative-weight cycles - off
//		for(Vertex v : graph.hashVertices.keySet()){
//			for(Vertex v1 : graph.hashVertices.keySet()){
//				for(Vertex v2 : graph.hashVertices.keySet()){
//					Edge e = graph.getEdge(v1, v2);
//					if(e!=null){
//						w = e.getWeight();
//						if(distance[graph.hashVertices.get(v1)] + w < distance[graph.hashVertices.get(v2)]){
//							System.out.println("Graph contains a negative-weight cycle");
//						}
//					}
//				}
//			}
//		}
	}
}
