package graph;

public class Vertex {

	int ver; 
	
	public Vertex(int i) {
		this.ver = i;
	}
	public Vertex() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEqual(Vertex vertex){
		if(this.ver == vertex.ver){
			return true;
		}
		return false;
	}
	
	public int getValue(){
		return this.ver;
	}
}
