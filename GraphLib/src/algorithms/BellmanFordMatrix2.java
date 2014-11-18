package algorithms;

import representation.MatrixReprGraph;
import graph.Edge;
import graph.Vertex;

public class BellmanFordMatrix2 {
	
	public Vertex[] previous;
	public int[] distance;
	public MatrixReprGraph graph;
	int infinity = Integer.MAX_VALUE;//100000000;
	
	public BellmanFordMatrix2(MatrixReprGraph graph, int size){
		this.previous = new Vertex[size];
		this.distance = new int [size];
		this.graph = graph;
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
		int w = 0;
		int indexV1;
		int indexV2;
		for(Vertex v : graph.hashVertices.keySet()){
			for(Vertex v1 : graph.hashVertices.keySet()){
				indexV1 = graph.hashVertices.get(v1);
				if(distance[indexV1] == this.infinity) continue;
				for(Vertex v2 : graph.hashVertices.keySet()){
					indexV2 = graph.hashVertices.get(v2);
					Edge e = graph.matrix[indexV1][indexV2];//graph.getEdge(v1, v2);
					if(e!=null){
						w = e.getWeight();
						if(distance[indexV1] + w < distance[graph.hashVertices.get(v2)]){
							distance[indexV2] = distance[indexV1] + w;
							previous[indexV2] = v1;
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
}

