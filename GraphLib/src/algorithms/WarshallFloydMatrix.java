package algorithms;

import representation.MatrixReprGraph;
import graph.Vertex;

public class WarshallFloydMatrix {

	public int[][] distances;
	public Vertex[][] previous;
	public MatrixReprGraph graph;
	public int infinity = Integer.MAX_VALUE;
//	public int infinity = -1;
	
	public WarshallFloydMatrix(MatrixReprGraph graph, int size){
		this.distances = new int[size][size];
		this.previous = new Vertex[size][size];
		this.graph = graph;
	}
	public long go(){
		long start=System.currentTimeMillis();
//		System.out.println(start);
		for(Vertex v1 : graph.hashVertices.keySet()){
			int index1 = graph.hashVertices.get(v1);
			for(Vertex v2 : graph.hashVertices.keySet()){
				int index2 = graph.hashVertices.get(v2);
				if(index1 == index2){
					this.distances[index1][index2] = 0;
				}
				else if(graph.areNeigboursOneSided(v1, v2)){
//				else if(graph.matrix[index1][index2]!=null){
					this.distances[index1][index2] = graph.matrix[index1][index2].getWeight();
					this.previous[index1][index2] = v1;
				}
				else{
					this.distances[index1][index2] = this.infinity;
					this.previous[index1][index2] = null;
				}
			}
		}

		for(Vertex u : graph.hashVertices.keySet()){
			int indexU = graph.hashVertices.get(u);
			for(Vertex v1 : graph.hashVertices.keySet()){
				int index1 = graph.hashVertices.get(v1);
				if(this.distances[index1][indexU] == this.infinity) continue;
				for(Vertex v2 : graph.hashVertices.keySet()){
					int index2 = graph.hashVertices.get(v2);
					if(/*this.distances[index1][indexU] == this.infinity|| */this.distances[indexU][index2] == this.infinity) continue;
					if(this.distances[index1][index2]>this.distances[index1][indexU]+this.distances[indexU][index2]){
						this.distances[index1][index2] = this.distances[index1][indexU] + distances[indexU][index2];
						this.previous[index1][index2]=this.previous[indexU][index2];
					}
				}
			}
			
		}
		long end=System.currentTimeMillis() - start;
		return end;
	}
	
	public Vertex getPrevious(MatrixReprGraph graph, Vertex source, Vertex between) {
		if(!graph.hashVertices.containsKey(source) && !graph.hashVertices.containsKey(between)) return null;
		return this.previous[graph.hashVertices.get(source)][graph.hashVertices.get(between)];
	}

}
