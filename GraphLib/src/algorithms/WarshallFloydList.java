package algorithms;

import representation.ListReprGraph;
import graph.Vertex;

public class WarshallFloydList {

	public int[][] distances;
	public Vertex[][] previous;
	public int infinity = Integer.MAX_VALUE;
	//		public int infinity = -1;
	
	public WarshallFloydList(int size){
		this.distances = new int[size][size];
		this.previous = new Vertex[size][size];
	}
	public void go(ListReprGraph graph){
		long start=System.currentTimeMillis();
		System.out.println(start);
		for(int i = 0; i < graph.vertices.length; i++){
			for(int j = 0; j < graph.vertices.length; j++){
				if(i == j) {
					this.distances[i][j] = 0;
				}
				else if(graph.areNeigboursOneSided(graph.vertices[i].getVertex(), graph.vertices[j].getVertex())){
					this.distances[i][j] = graph.getEdge(graph.vertices[i].getVertex(), graph.vertices[j].getVertex()).getWeight();
					this.previous[i][j] = graph.vertices[i].getVertex();					
				}
				else{
					this.distances[i][j] = this.infinity;
					this.previous[i][j] = null;
				}
			}
		}
	
		for(int u = 0; u < graph.vertices.length; u++){
			for(int i = 0; i < graph.vertices.length; i++){
				for(int j = 0; j < graph.vertices.length; j++){
						if(this.distances[i][u] == this.infinity|| this.distances[u][j] == this.infinity) continue;
						if(this.distances[i][j]>this.distances[i][u]+this.distances[u][j]){
							this.distances[i][j] = this.distances[i][u] + distances[u][j];
							this.previous[i][j]=this.previous[u][j];
						}
				}
			}
		}
			long end=System.currentTimeMillis() - start;
			System.out.println((double)end/(double)1000);
	}
}
