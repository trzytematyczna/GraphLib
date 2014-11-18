package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import files.EntryFile;
import graph.Vertex;
import representation.ListReprGraph;
import representation.ListReprGraph.Element;

public class BellmanFordList {
		public Vertex[] previous;
		public int[] distance;
		public ListReprGraph graph;
		int infinity = Integer.MAX_VALUE; //100000000;
		ArrayList<FullEdge> fullEdges;
//		LinkedList<Edge> edges;
		
		public BellmanFordList(ListReprGraph graph, int size){
			this.previous = new Vertex[size];
			this.distance = new int [size];
			this.graph = graph;
//			this.edges = this.getAllEdges();
	        fullEdges = new ArrayList<FullEdge>();
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
					}
					previous[i] = null;
				}
			}
		int len = graph.vertices.length;
        for (int i = 0; i < len; ++i) {
            if(graph.vertices[i] != null) {
                for (Element element : graph.vertices[i].getBeginList()) {
                    FullEdge edge = new FullEdge();
                    edge.begin = i;
                    edge.end = graph.getIndex(element.getVertex());
                    edge.weight = element.getEdge().getWeight();
                    fullEdges.add(edge);
                }
            }
        }
 
        for(int j = 0; j < len; j++) {
            for (FullEdge edge : fullEdges) {
		        if(distance[edge.begin] == this.infinity) continue;
		        if(distance[edge.begin] + edge.weight < distance[edge.end]){
		            distance[edge.end] = distance[edge.begin] + edge.weight;
		            previous[edge.end] = graph.vertices[edge.begin].getVertex();
		      	}
		     }
        }
	
    return (System.currentTimeMillis() - mstart);
	}	
		
	public long go_no_edges(Vertex source){
		long mstart = System.currentTimeMillis();
		for(int i=0; i<graph.vertices.length; i++){
			if(graph.vertices[i]!=null){
				if(graph.vertices[i].getVertex().equals(source)){
					distance[i] = 0;
				}
				else{
					distance[i] = this.infinity;
				}
				previous[i] = null;
			}
		}
		int w = 0;
		int index ;
		for(int j=0; j<graph.vertices.length; j++){
			for(int i=0; i<graph.vertices.length; i++){
				if(distance[i] == this.infinity) continue;
				if(graph.vertices[i]!=null){
					for(Element elem : graph.vertices[i].getBeginList()){
						w = elem.getEdge().getWeight();
						index = graph.getIndex(elem.getVertex());
						if(distance[i] + w < distance[index]){
							distance[index] = distance[i] + w;
							previous[index] = graph.vertices[i].getVertex();
						}
					}
				}
			}
		}
//			for(int u=0; u<graph.vertices.length; u++){
//				for(int i=0; i<graph.vertices.length; i++){
//					if(distance[i] == this.infinity) continue;
//					for(int j=0; j<graph.vertices.length; j++){
//						if(graph.vertices[i]!=null){
//							Vertex v1 = graph.vertices[i].getVertex();
//							Vertex v2 =graph.vertices[j].getVertex();
//							Edge edge = graph.getEdge(v1, v2);
//							if(edge!=null){
//								int ww =  edge.getWeight();
//								if(distance[i] + ww < distance[j]){
//									distance[j] = distance[i] + ww;
//									previous[j] = v1;
//								}
//							}
//							
//						}
//					}
//					if(graph.vertices[i]!=null){
//						if(distance[i] == this.infinity) continue;
//						for(Element elem : graph.vertices[i].getBeginList()){
//							w = elem.getEdge().getWeight();
//							if(distance[i] + w < distance[graph.getIndex(elem.getVertex())]){
//								distance[graph.getIndex(elem.getVertex())] = distance[i] + w;
//								previous[graph.getIndex(elem.getVertex())] = graph.vertices[i].getVertex();
//							}
//						}
//					}
//				}
//			}
		//checking negative-weight cycles - off
//			for(Vertex v : graph.hashVertices.keySet()){
//				for(Vertex v1 : graph.hashVertices.keySet()){
//					for(Vertex v2 : graph.hashVertices.keySet()){
//						Edge e = graph.getEdge(v1, v2);
//						if(e!=null){
//							w = e.getWeight();
//							if(distance[graph.hashVertices.get(v1)] + w < distance[graph.hashVertices.get(v2)]){
//								System.out.println("Graph contains a negative-weight cycle");
//							}
//						}
//					}
//				}
//			}
		return (System.currentTimeMillis() - mstart);
	}

		
}

class FullEdge {
    int begin;
    int end;
    int weight;
}
