package algorithms;

import representation.Graph;
import representation.MGraph;
import representation.MGraph2;
import graph.Vertex;

public class WarshalFloyd {

	public int[][] distances;
	public Vertex[][] previous;
	public int infinity = Integer.MAX_VALUE;
//	public int infinity = -1;
	
	public WarshalFloyd(int size){
		this.distances = new int[size][size];
		this.previous = new Vertex[size][size];
	}
	public void go(MGraph2 graph){
		System.out.println(System.currentTimeMillis());
		for(Vertex v1 : graph.hashVertices.keySet()){
//			int index1= graph.getVertexValue(v1.getName());
			int index1 = graph.hashVertices.get(v1);
			for(Vertex v2 : graph.hashVertices.keySet()){
//				int index2= graph.getVertexValue(v2.getName());
				int index2 = graph.hashVertices.get(v2);
				if(index1 == index2){
					this.distances[index1][index2] = 0;
				}
//				else if(graph.areNeigboursOneSided(v1, v2)){
				else if(graph.matrix[index1][index2]!=null){
					this.distances[index1][index2] = graph.matrix[index1][index2].getWeight();
					this.previous[index1][index2] = v1;
				}
				else{
					this.distances[index1][index2] = this.infinity;
					this.previous[index1][index2] = null;
				}
			}
		}
		System.out.println(System.currentTimeMillis());
		for(Vertex u : graph.hashVertices.keySet()){
//			int indexU= graph.getVertexValue(u.getName());
			int indexU = graph.hashVertices.get(u);

			for(Vertex v1 : graph.hashVertices.keySet()){
//				int index1= graph.getVertexValue(v1.getName());
				int index1 = graph.hashVertices.get(v1);
				for(Vertex v2 : graph.hashVertices.keySet()){
//					int index2= graph.getVertexValue(v2.getName());
					int index2 = graph.hashVertices.get(v2);
					if(this.distances[index1][indexU] == this.infinity|| this.distances[indexU][index2] == this.infinity) continue;
					if(this.distances[index1][index2]>this.distances[index1][indexU]+this.distances[indexU][index2]){
						this.distances[index1][index2] = this.distances[index1][indexU] + distances[indexU][index2];
						this.previous[index1][index2]=this.previous[indexU][index2];
					}
				}
			}
			
		}
		System.out.println(System.currentTimeMillis());

	}
}
