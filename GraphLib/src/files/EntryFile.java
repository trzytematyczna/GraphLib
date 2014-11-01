package files;

public class EntryFile {

	int inVertex;
	int outVertex;
	int edge;
	
	public EntryFile(int in, int out, int edge) {
		this.inVertex = in;
		this.outVertex = out;
		this.edge = edge;
	}
	
	public int getInVertex(){
		return this.inVertex;
	}
	public int getOutVertex(){
		return this.outVertex;
	}
	public int getEdge(){
		return this.edge;
	}
}
