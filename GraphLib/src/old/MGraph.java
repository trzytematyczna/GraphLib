package old;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;

import representation.Graph;

public class MGraph implements Graph{
	
	public Edge[][] matrix;
	
	public HashMap<Vertex, Integer> hashVertices;
	
	public MGraph(LinkedList<EntryFile> list) {
		Vertex inVer;
		Vertex outVer;
		int indexV = 0;
		this.hashVertices= new HashMap<Vertex, Integer>();

		int size = preprocess(list);
		this.matrix = initMatrix(size);

		for(int i=0; i<list.size();i++){
			inVer = new Vertex(list.get(i).getInVertex());
			outVer = new Vertex(list.get(i).getOutVertex());
			addVertex(inVer);
			addVertex(outVer);
		}
		
		for(int i=0; i<list.size();i++){
			addEdge(new Edge (list.get(i).getEdge()), new Vertex(list.get(i).getInVertex()), new Vertex(list.get(i).getOutVertex())); 
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
				newMatrix[i][j] = null;//new Edge(-1);
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
		}
		else{ //there is space to put new vertex
			this.hashVertices.put(vertex, space);
		}
		return true;
	}

	private Edge[][] enlargeMatrix(Edge[][] matrix2) {
		// TODO Auto-generated method stub
		int current_size = matrix2.length;
		int new_size = current_size+50;
		Edge [][] newMatrix = new Edge [new_size][new_size];
//		newMatrix = initMatrix(new_size);
		
		for(int i=0; i<current_size; i++){
			for(int j=0; j<current_size; j++){
				newMatrix[i][j] = matrix2[i][j];
			}
		}
		for(int i=current_size; i<new_size; i++){
			for(int j=0; j<new_size; j++){
				newMatrix[i][j] = null;
			}
		}
		for(int i=0; i<new_size; i++){
			for(int j=current_size; j<new_size; j++){
				newMatrix[i][j] = null;
			}
		}
		return newMatrix;
	}
	/**
	 * 
	 * @param position position of column and row to put there some new Edge with some value
	 * @param what value of edge (-1 - vertex exists with no edges
	 */
	private void putInAllRowAndCol(int position, int what) {
		for(int i=0; i<this.matrix.length; i++){
			this.matrix[position][i] = new Edge(what);
			this.matrix[i][position] = new Edge(what);
		}
		
	}
	
	private void putNullInAllRowAndCol(int position) {
		for(int i=0; i<this.matrix.length; i++){
			this.matrix[position][i] =null;
			this.matrix[i][position] = null;
		}
		
	}
	/**
	 * 
	 * @return free position or -1 if no free space
	 */
	private int freeSpace() {
		for(int i=0; i<this.matrix.length; i++){
			if(getVertexFromValue(i) == null) return i;
		}
		return -1;
	}
	
	@Override
	public boolean deleteVertex(Vertex vertex) {
				
		if(!isVertexExists(vertex, this.hashVertices)) return false;
		
			putNullInAllRowAndCol(getVertexValue(vertex.getName()));
			Vertex v =getVertexFromName(vertex.getName());
			this.hashVertices.remove(v);
		
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
		this.matrix[getVertexValue(in.getName())][getVertexValue(out.getName())] = null;//new Edge(-1);
		return false;
	}

	@Override
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
		
		if(isVertexExists(vertex, this.hashVertices)){
			int position = getVertexValue(vertex.getName());
			for(int i=0; i<this.matrix.length; i++){
				if(this.matrix[position][i] != null)  {
					neighbours.add(getVertexFromValue(i));
				}
				if(this.matrix[i][position] != null)  {
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
			for(int i=0; i<this.matrix.length; i++){
				if(this.matrix[position][i] != null)  {
					incident.add(this.matrix[position][i]);
				}
				if(this.matrix[i][position] != null){
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
		for (int i = 0; i < this.matrix.length; i++){
			for (int j = 0; j < this.matrix.length; j++){
				if(this.matrix[i][j]!=null){
						count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean areNeigbours(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.hashVertices) && isVertexExists(v2, this.hashVertices)){
			if(this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())]!= null ||
					this.matrix[getVertexValue(v2.getName())][getVertexValue(v1.getName())]!= null ){
				return true;
			}
		}
		return false;
	}
	
	public boolean areNeigboursOneSided(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.hashVertices) && isVertexExists(v2, this.hashVertices)){
			if(this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())]!= null ){
				return true;
			}
		}
		return false;
	}
	
	public Edge getEdge(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.hashVertices) && isVertexExists(v2, this.hashVertices)){
			if(this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())]!= null ||
					this.matrix[getVertexValue(v2.getName())][getVertexValue(v1.getName())]!= null ){
				return this.matrix[getVertexValue(v1.getName())][getVertexValue(v2.getName())];
			}
		}
		return null;
	}
	private int preprocess(LinkedList<EntryFile> list){
		LinkedList<Integer> values = new LinkedList<Integer>();

		for(int i=0; i<list.size();i++){
			if(!values.contains(list.get(i).getInVertex())){
				values.add(list.get(i).getInVertex());
			}
			if(!values.contains(list.get(i).getOutVertex())){
				values.add(list.get(i).getOutVertex());
			}
		}
		
		return values.size();
	}

	public int weightCount() {
		int count= 0;
		for (int i = 0; i < this.matrix.length; i++){
			for (int j = 0; j < this.matrix.length; j++){
				if(this.matrix[i][j]!=null){
					if(getVertexFromValue(i).getName()<getVertexFromValue(j).getName()) count+=this.matrix[i][j].getWeight(); 
				}
			}
		}
		return count;
	}
}
