package graph;

public class Edge {

	int weight;
	
	public Edge(int weight){
		this.weight = weight;
	}
	
	public Edge() {
		// TODO Auto-generated constructor stub
	}

	public int getWeight(){
		return this.weight;
	}
	
	public void setWeight(int newWeight){
		this.weight = newWeight;
	}
}
