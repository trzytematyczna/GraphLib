package algorithms_done;

import representation.ListReprGraph;
import graph.Vertex;

public class WarshallFloydList {

	public int[][] distances;
	public Vertex[][] previous;
	public ListReprGraph graph;
	public int infinity = Integer.MAX_VALUE;
	//		public int infinity = -1;
	
	public WarshallFloydList(ListReprGraph graph,int size){
		this.distances = new int[size][size];
		this.previous = new Vertex[size][size];
		this.graph = graph;
	}
	public long go(){
		long start=System.currentTimeMillis();
//		System.out.println(start);
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
				if(this.distances[i][u] == this.infinity) continue;
				for(int j = 0; j < graph.vertices.length; j++){
						if(this.distances[u][j] == this.infinity) continue;
						if(this.distances[i][j]>this.distances[i][u]+this.distances[u][j]){
							this.distances[i][j] = this.distances[i][u] + distances[u][j];
							this.previous[i][j]=this.previous[u][j];
						}
				}
			}
		}
			long end=System.currentTimeMillis() - start;
			return end;
	}
	public Vertex getPrevious(ListReprGraph graph, Vertex source, Vertex between) {
		if(graph.vertices[graph.getIndex(source)]==null && graph.vertices[graph.getIndex(between)]==null) return null;
		return this.previous[graph.getIndex(source)][graph.getIndex(between)];
	}
}
