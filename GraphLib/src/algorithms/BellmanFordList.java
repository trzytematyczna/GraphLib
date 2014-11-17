package algorithms;

import java.util.LinkedList;

import graph.Edge;
import graph.Vertex;
import representation.ListReprGraph;
import representation.ListReprGraph.Element;

public class BellmanFordList {
	public Vertex[] previous;
	public int[] distance;
	public ListReprGraph graph;
	int infinity = Integer.MAX_VALUE; //100000000;
//	LinkedList<Edge> edges;
	
	public BellmanFordList(ListReprGraph graph, int size){
		this.previous = new Vertex[size];
		this.distance = new int [size];
		this.graph = graph;
//		this.edges = this.getAllEdges();
	}
	public long go(Vertex source){
		long mstart = System.currentTimeMillis();
		for(int i=0; i<graph.vertices.length; i++){
			if(graph.vertices[i]!=null){
				if(graph.vertices[i].getVertex().equals(source)){
					distance[i] = 0;
				}
				else{
					distance[i] = this.infinity;
					previous[i] = null;
				}
			}
		}
		int w = 0;
		for(int j=0; j<graph.vertices.length; j++){
			for(int i=0; i<graph.vertices.length; i++){
				if(graph.vertices[i]!=null){
					if(distance[i] == this.infinity) continue;
					for(Element elem : graph.vertices[i].getBeginList()){
						w = elem.getEdge().getWeight();
						if(distance[i] + w < distance[graph.getIndex(elem.getVertex())]){
							distance[graph.getIndex(elem.getVertex())] = distance[i] + w;
							previous[graph.getIndex(elem.getVertex())] = graph.vertices[i].getVertex();
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
		return (System.currentTimeMillis() - mstart);
	}
	
	public LinkedList<Edge> getAllEdges(){
		LinkedList<Edge> result = new LinkedList<Edge>();
		for(int i=0; i<graph.vertices.length; i++){
			if(graph.vertices[i]!=null){
				for(Element elem : graph.vertices[i].getBeginList()){
					result.add(elem.getEdge());
				}
			}
		}
		return result;
	}
}
