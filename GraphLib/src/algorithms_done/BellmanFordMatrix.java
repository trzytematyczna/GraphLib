package algorithms_done;

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
			int index = graph.hashVertices.get(vertex);
			if(vertex.equals(source)){
				distance[index] = 0;
			}
			else{
				distance[index] = this.infinity;
			}
			previous[index] = null;
		}
		int w = 0;
		int indexV1;
		int indexV2;
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
		return (System.currentTimeMillis() - mstart);
	}
	
	public long go_edges(Vertex source){
		long mstart = System.currentTimeMillis();
		for(Vertex vertex : graph.hashVertices.keySet()){
//			int index = graph.hashVertices.get(vertex);
			int index = vertex.getName();
			if(vertex.equals(source)){
				distance[index] = 0;
			}
			else{
				distance[index] = this.infinity;
			}
			previous[index] = null;
		}
//		int len = graph.matrix.length;
		Edge ee;
//        for (Vertex v1: graph.hashVertices.keySet()) {
//            for (Vertex v2: graph.hashVertices.keySet()) {
//                ee = graph.getEdge(v1, v2);
//                if(ee!=null) {        		
//	                FullEdge edge = new FullEdge();
//	                edge.begin = v1.getName();
//	                edge.end = v2.getName();//graph.getIndex(element.getVertex());
//                	edge.weight = ee.getWeight();//element.getEdge().getWeight();
//                    fullEdges.add(edge);
//                }
//            }
//        }
        
        for(int i = 0; i < graph.matrix.length; i++){
        	for(int j=0; j< graph.matrix.length; j++){
//        		if(i==j) continue;
        		if(graph.matrix[i][j]!=null){
        			FullEdge edge = new FullEdge();
	                edge.begin = graph.getVertexFromValue(i).getName();
	                edge.end = graph.getVertexFromValue(j).getName();//graph.getIndex(element.getVertex());
                	edge.weight = graph.matrix[i][j].getWeight();//element.getEdge().getWeight();
                    fullEdges.add(edge);
        		}
        	}
        }
        for(int j = 0; j < graph.matrix.length; j++) {
            for (FullEdge edge : fullEdges) {
		        if(distance[edge.begin] == this.infinity) continue;
		        if(distance[edge.begin] + edge.weight < distance[edge.end]){
		            distance[edge.end] = distance[edge.begin] + edge.weight;
		            previous[edge.end] = graph.getVertexFromName(edge.begin);//graph.vertices[edge.begin].getVertex();
		      	}
		     }
        }
        
        return (System.currentTimeMillis() - mstart);
	}
}

