package old;

import java.util.LinkedList;

import old.List.Element;
import graph.Vertex;

public class BFList {
		public Vertex[] previous;
		public int[] distance;
		public List graph;
		int infinity = Integer.MAX_VALUE; //100000000;
//		LinkedList<Edge> edges;
		
		public BFList(List graph, int size){
			this.previous = new Vertex[size];
			this.distance = new int [size];
			this.graph = graph;
//			this.edges = this.getAllEdges();
		}
		public long go(Vertex source){
			long mstart = System.currentTimeMillis();
			int i=0;
			for(Vertex v : graph.vertices.keySet()){
				if(v.equals(source)){
					distance[i++] = 0;
				}
				else{
					distance[i] = this.infinity;
					previous[i++]=null;
				}
			}
//			for(int i=0; i<graph.vertices.length; i++){
//				if(graph.vertices[i]!=null){
//					if(graph.vertices[i].getVertex().equals(source)){
//						distance[i] = 0;
//					}
//					else{
//						distance[i] = this.infinity;
//						previous[i] = null;
//					}
//				}
//			}
			
			for (Vertex v : graph.vertices.keySet()){
//				LinkedList<Element> el = graph.vertices.values();
				for (LinkedList<Element> listasd : graph.vertices.values()){
//					if(distance[i1] == this.infinity) continue;
//					for(Element elem : graph.vertices.){
//						
//					}
					for(Element elem : listasd){
						if(distance[])
					}
				}
			}
//			int w = 0;
//			for(int j=0; j<graph.vertices.length; j++){
//				for(int i=0; i<graph.vertices.length; i++){
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
		
//		public LinkedList<EntryFile> getAllEdges(){
//			LinkedList<EntryFile> result = new LinkedList<EntryFile>();
//			for(int i=0; i<graph.vertices.length; i++){
//				if(graph.vertices[i]!=null){
//					for(Element elem : graph.vertices[i].getBeginList()){
//						result.add(new EntryFile(graph.vertices[i].getVertex().getName(), elem.getVertex().getName(), elem.getEdge().getWeight()));
//					}
//				}
//			}
//			return result;
//		}
	}
