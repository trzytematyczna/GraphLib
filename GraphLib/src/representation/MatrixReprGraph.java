package representation;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

import java.util.HashMap;
import java.util.LinkedList;

public class MatrixReprGraph implements Graph{
	
	public Edge[][] matrix;
	public HashMap<Vertex, Integer> hashVertices;
	
	public MatrixReprGraph(LinkedList<EntryFile> list) {
		Vertex inVer;
		Vertex outVer;
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
	
	public MatrixReprGraph(LinkedList<EntryFile> list, int size) {
		Vertex inVer;
		Vertex outVer;
		this.hashVertices= new HashMap<Vertex, Integer>();
		this.matrix = initMatrix(size);
		
		int indexV=0;
		
//		for(int i=0; i<list.size();i++){
//			inVer = new Vertex(list.get(i).getInVertex());
//			outVer = new Vertex(list.get(i).getOutVertex());
////			addVertex(inVer);
////			addVertex(outVer);
//			if(!this.hashVertices.containsKey(inVer)){
//				this.hashVertices.put(inVer, indexV++);
//			}
//			if(!this.hashVertices.containsKey(outVer)){
//				this.hashVertices.put(outVer, indexV++);
//			}
//		}
		
		for(EntryFile entry : list){
			inVer = new Vertex(entry.getInVertex());
			outVer = new Vertex(entry.getOutVertex());
//			addVertex(inVer);
//			addVertex(outVer);
			if(!this.hashVertices.containsKey(inVer)){
				this.hashVertices.put(inVer, indexV++);
			}
			if(!this.hashVertices.containsKey(outVer)){
				this.hashVertices.put(outVer, indexV++);
			}
		}
//		for(int i=0; i<list.size();i++){
		for(EntryFile entry : list){
//			this.matrix[this.hashVertices.get(list.get(i).getInVertex())][this.hashVertices.get(list.get(i).getOutVertex())] = new Edge (list.get(i).getEdge());
//			addEdge(new Edge (list.get(i).getEdge()), new Vertex(list.get(i).getInVertex()), new Vertex(list.get(i).getOutVertex())); 
			addEdge(new Edge (entry.getEdge()), new Vertex(entry.getInVertex()), new Vertex(entry.getOutVertex())); 
		}
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
	public Vertex getVertexFromName(int name){
		Vertex foundValue = null;
		for(Vertex v : this.hashVertices.keySet()){
			if(v.getName() == name){
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
		if(this.hashVertices.containsKey(vertex)) return false;
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
		int current_size = matrix2.length;
		int new_size = current_size+50;
		Edge [][] newMatrix = new Edge [new_size][new_size];
		
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
				
		if(!this.hashVertices.containsKey(vertex)) return false;
		
			putNullInAllRowAndCol(this.hashVertices.get(vertex));
			this.hashVertices.remove(vertex);
		
		return true;
	}

	@Override
	public boolean addEdge(Edge edge, Vertex v1, Vertex v2) {
		if(!this.hashVertices.containsKey(v1)|| !this.hashVertices.containsKey(v2)) return false;
		
		this.matrix[this.hashVertices.get(v1)][this.hashVertices.get(v2)] = edge;
		return true;
	}

	@Override
	public boolean deleteEdge(Vertex in, Vertex out) {
		if(!this.hashVertices.containsKey(in)|| !this.hashVertices.containsKey(out)) return false;
		this.matrix[this.hashVertices.get(in)][this.hashVertices.get(out)] = null;
		return false;
	}

	@Override
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
		
		if(this.hashVertices.containsKey(vertex)){
			int position = this.hashVertices.get(vertex);//getVertexValue(vertex.getName());
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
		if(this.hashVertices.containsKey(vertex)){
			int position = this.hashVertices.get(vertex);//getVertexValue(vertex.getName());
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
		if(this.hashVertices.containsKey(v1) && this.hashVertices.containsKey(v2)) {
		
			if(this.matrix[this.hashVertices.get(v1)][this.hashVertices.get(v2)]!=null ||
					this.matrix[this.hashVertices.get(v2)][this.hashVertices.get(v1)]!=null ){
				return true;
			}
		}
		return false;
	}
	
	public boolean areNeigboursOneSided(Vertex v1, Vertex v2) {
		if(this.hashVertices.containsKey(v1) && this.hashVertices.containsKey(v2)){
			if(this.matrix[this.hashVertices.get(v1)][this.hashVertices.get(v2)]!=null){
				return true;
			}
		}
		return false;
	}
	
	public Edge getEdge(Vertex v1, Vertex v2) {
		if(this.hashVertices.containsKey(v1) && this.hashVertices.containsKey(v2)) {
			int index1=this.hashVertices.get(v1);
			int index2=this.hashVertices.get(v2);
			if(this.matrix[index1][index2]!=null){
				return this.matrix[index1][index2];
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
