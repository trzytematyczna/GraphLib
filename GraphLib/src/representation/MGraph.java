package representation;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;

public class MGraph implements Graph{
	
	public Edge[][] matrix;
	int size;
	
	HashMap<Vertex, Integer> hashVertices;
	
	public MGraph(LinkedList<EntryFile> list) {
		int indexV = 0;
		Vertex inVer;
		Vertex outVer;
		
		this.hashVertices= new HashMap<Vertex, Integer>();

		for(int i=0; i<list.size();i++){
			inVer = new Vertex(list.get(i).getInVertex());
			outVer = new Vertex(list.get(i).getOutVertex());
			if(!isVertexExists(inVer, this.hashVertices)){
				this.hashVertices.put(inVer, indexV++);
			}
			if(!isVertexExists(outVer, this.hashVertices)){
				this.hashVertices.put(outVer, indexV++);
			}
		}
		
		this.size = this.hashVertices.size();
		this.matrix = initMatrix(this.size);
		for(int i=0; i<list.size();i++){
			this.matrix[getVertexValue(list.get(i).getInVertex())][getVertexValue(list.get(i).getOutVertex())] = new Edge (list.get(i).getEdge());
		}
	}
	public boolean isVertexExists(Vertex vertex, HashMap<Vertex, Integer> haszmap){
		for(Vertex v : haszmap.keySet()){
			if(vertex.isEqual(v)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param name -integer form vertex.getName()
	 * @return 
	 */
	public Integer getVertexValue(int name){
		int foundValue = -1;
		for(Vertex v : this.hashVertices.keySet()){
			if(v.getName() == name){
				foundValue = this.hashVertices.get(v);
			}
		}
		return foundValue;
	}
	
	public Vertex getVertexFromName(int name){
		Vertex foundValue = null;
		for(Vertex v : this.hashVertices.keySet()){
			if(v.getName() == name){
				foundValue = v;
			}
		}
		return foundValue;
	}
	public Vertex getVertexFromValue(int value){
		Vertex foundValue = null;
		for(Vertex v : this.hashVertices.keySet()){
			if(this.hashVertices.get(v) == value){
				foundValue = v;
			}
		}
		return foundValue;
	}
	private Edge[][] initMatrix(int size) {
		Edge[][] newMatrix = new Edge[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				newMatrix[i][j] = new Edge(-1);
			}
		}
		return newMatrix;
	}
	
	
	public boolean addVertex(Vertex vertex) {
		if(isVertexExists(vertex, this.hashVertices)) return false;
		
		int space = freeSpace();
		if( space == -1){
			this.matrix = enlargeMatrix(this.matrix);
			int position = this.hashVertices.size();
			this.hashVertices.put(vertex, position);
			putInAllRowAndCol(position, -1);
		}
		else{ //there is space to put new vertex
			this.hashVertices.put(vertex, space);
			putInAllRowAndCol(space, -1);	
		}
		return true;
	}

	private Edge[][] enlargeMatrix(Edge[][] matrix2) {
		// TODO Auto-generated method stub
		//create new matrix
		//init new matrix with edge(-1)
		//copy old matrix to new matrix
		//return new matrix
		return null;
	}
	/**
	 * 
	 * @param position position of column and row to put there some new Edge with some value
	 * @param what value of edge (-1 - vertex exists with no edges
	 */
	private void putInAllRowAndCol(int position, int what) {
		for(int i=0; i<this.size; i++){
			this.matrix[position][i] = new Edge(what);
			this.matrix[i][position] = new Edge(what);
		}
		
	}
	
	private void putNullInAllRowAndCol(int position) {
		for(int i=0; i<this.size; i++){
			this.matrix[position][i] =null;
			this.matrix[i][position] = null;
		}
		
	}
	/**
	 * 
	 * @return free position or -1 if no free space
	 */
	private int freeSpace() {
		for(int i=0; i<this.size; i++){
//			for(int j=0; j<this.size; j++){
					if(this.matrix[i][0] == null) return i;
//			}
		}
		return -1;
	}
	
	@Override
	public boolean deleteVertex(Vertex vertex) {
				
		if(!isVertexExists(vertex, this.hashVertices)) return false;
		
			putNullInAllRowAndCol(getVertexValue(vertex.getName()));
			this.hashVertices.remove(getVertexFromName(vertex.getName()));
		
		return true;
	}

	@Override
	public boolean addEdge(Edge edge, Vertex v1, Vertex v2) {
		if(!isVertexExists(v1, this.hashVertices) || !isVertexExists(v2, this.hashVertices)) return false;
		
		this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())] = edge;
		return true;
	}

	@Override
	public boolean deleteEdge(Vertex in, Vertex out) {
		if(!isVertexExists(in, this.hashVertices) || !isVertexExists(out, this.hashVertices)) return false;
		this.matrix[getVertexValue(in.getName())][getVertexValue(out.getName())] = new Edge(-1);
		return false;
	}

	@Override
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
		
		if(isVertexExists(vertex, this.hashVertices)){
			int position = getVertexValue(vertex.getName());
			for(int i=0; i<this.size; i++){
				if(this.matrix[position][i].getWeight() != -1 && this.matrix[position][i] != null)  {
					neighbours.add(getVertexFromValue(i));
				}
				if(this.matrix[i][position].getWeight() != -1 && this.matrix[i][position] != null){
					neighbours.add(getVertexFromValue(i));					
				}
			}
			
		}
		return neighbours;
	}

	@Override
	public LinkedList<Edge> incidentEdges(Vertex vertex) {
		LinkedList<Edge> incident = new LinkedList<Edge>();
		if(isVertexExists(vertex, this.hashVertices)){
			int position = getVertexValue(vertex.getName());
			for(int i=0; i<this.size; i++){
				if(this.matrix[position][i].getWeight() != -1 && this.matrix[position][i] != null)  {
					incident.add(this.matrix[position][i]);
				}
				if(this.matrix[i][position].getWeight() != -1 && this.matrix[i][position] != null){
					incident.add(this.matrix[i][position]);
				}
			}
		}
		return incident;
	}

	@Override
	public int vertexCount() {
		
		return this.hashVertices.size();
	}

	@Override
	public int edgeCount() {
		int count= 0;
		for (int i = 0; i < this.size; i++){
			for (int j = 0; j < this.size; j++){
				if (this.matrix[i][j].getWeight() != -1 && this.matrix[i][j] != null ) count++;
			}
		}
		return count;
	}

	@Override
	public boolean areNeigbours(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.hashVertices) && isVertexExists(v2, this.hashVertices)){
			if(this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())].getWeight()!=-1 &&
					this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())]!= null){
				return true;
			}
		}
		return false;
	}

}
