package graph;

public class Vertex {

	int name; 
	
	public Vertex(int i) {
		this.name = i;
	}
	public Vertex() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEqual(Vertex vertex){
		if(this.name == vertex.name){
			return true;
		}
		return false;
	}
	
	public int getName(){
		return this.name;
	}
}
