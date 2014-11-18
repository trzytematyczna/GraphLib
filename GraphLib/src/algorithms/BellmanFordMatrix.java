package algorithms;

import java.util.ArrayList;

import representation.MatrixReprGraph;
import representation.ListReprGraph.Element;
import graph.Edge;
import graph.Vertex;

public class BellmanFordMatrix {
	
	public Vertex[] previous;
	public int[] distance;
	public MatrixReprGraph graph;
	int infinity = Integer.MAX_VALUE;//100000000;
	ArrayList<FullEdge> fullEdges;
	
	public BellmanFordMatrix(MatrixReprGraph graph, int size){
		this.previous = new Vertex[size];
		this.distance = new int [size];
		this.graph = graph;
		fullEdges = new ArrayList<FullEdge>();
	}
	public long go(Vertex source){
		long mstart = System.currentTimeMillis();
		for(Vertex vertex : graph.hashVertices.keySet()){
			if(vertex.equals(source)){
				distance[graph.hashVertices.get(vertex)] = 0;
			}
			else{
				distance[graph.hashVertices.get(vertex)] = this.infinity;
				previous[graph.hashVertices.get(vertex)] = null;
			}
		}
		
////		int len = graph.vertices.length;
//        for (int i = 0; i < graph.matrix.length; ++i) {
//            if(graph.vertices[i] != null) {
//                for (Element element : graph.vertices[i].getBeginList()) {
//                    FullEdge edge = new FullEdge();
//                    edge.begin = i;
//                    edge.end = graph.getIndex(element.getVertex());
//                    edge.weight = element.getEdge().getWeight();
//                    fullEdges.add(edge);
//                }
//            }
//        }
		int w = 0;
		int indexV1;
		int indexV2;
//		for(Vertex v : graph.hashVertices.keySet()){
//			for(Vertex v1 : graph.hashVertices.keySet()){
//				indexV1 = graph.hashVertices.get(v1);
//				if(distance[indexV1] == this.infinity) continue;
//				for(Vertex v2 : graph.hashVertices.keySet()){
//					indexV2 = graph.hashVertices.get(v2);
//					Edge e = graph.matrix[indexV1][indexV2];//graph.getEdge(v1, v2);
//					if(e!=null){
//						w = e.getWeight();
//						if(distance[indexV1] + w < distance[indexV2]){
//							distance[indexV2] = distance[indexV1] + w;
//							previous[indexV2] = v1;
//						}
//					}
//				}
//			}
//		}
		
		for(Vertex v : graph.hashVertices.keySet()){
			for(int i=0; i< graph.matrix.length; i++){
				if(distance[i] == this.infinity) continue;
				for(int j=0; j< graph.matrix.length; j++){
					Edge e = graph.matrix[i][j];//graph.getEdge(v1, v2);
					if(e!=null){
						w = e.getWeight();
						if(distance[i] + w < distance[j]){
							distance[j] = distance[i] + w;
							previous[j] = graph.getVertexFromValue(i);
						}
					}
				}
			}
		}
		
//        for(int j = 0; j < len; j++) {
//            for (FullEdge edge : fullEdges) {
//		        if(distance[edge.begin] == this.infinity) continue;
//		        if(distance[edge.begin] + edge.weight < distance[edge.end]){
//		            distance[edge.end] = distance[edge.begin] + edge.weight;
//		            previous[edge.end] = graph.vertices[edge.begin].getVertex();
//		      	}
//		     }
//        }
		return (System.currentTimeMillis() - mstart);
	}
}

