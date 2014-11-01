package representation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

public class MatrixGraph implements Graph{

	int size = 100;
	
	public Integer[][] matrix;
	
	HashMap<Vertex, Integer> hashVertices;
	HashMap<Edge, Integer> hashEdges;
//	static int current_edge_id;
//	static int iver;
	
	
	public MatrixGraph() {
//		this.current_edge_id = -1;
//		this.iver = -1;
		this.matrix = new Integer[this.size][this.size];
		this.hashVertices= new HashMap<Vertex, Integer>();
		this.hashEdges= new HashMap<Edge, Integer>();
	}
	
//	public MatrixGraph(Vertex[] vertex, Edge[] edge) {
//		int index = 0;
//		this.hashVertices= new HashMap<Vertex, Integer>();
//		int size = vertex.length;
//		for(Vertex ver : vertex){
//			hashVertices.put(ver, index++);
//		}
//		index = 0;
//		this.hashEdges= new HashMap<Edge, Integer>();
//		for(Edge ed : edge){
//			hashEdges.put(ed, index++);
//		}
//		this.matrix = new Integer[this.size][this.size];
//	}
	
	@SuppressWarnings(value = { "sth_wrong" })
	public MatrixGraph(Integer [] [] list, int size) {
		int indexE = 0;
		Vertex inVer;
		Vertex outVer;
		Edge ed;
		
		this.size = size;
		this.hashVertices= new HashMap<Vertex, Integer>();
		this.hashEdges= new HashMap<Edge, Integer>();
		this.matrix = initMatrix(size);

		for(Integer [] entity: list){
			inVer = new Vertex(entity[0]);
			outVer = new Vertex(entity[1]);
			ed= new Edge(entity[2]);
			if(!isThere(inVer, hashVertices)){
				hashVertices.put(inVer, inVer.getValue());
			}
			if(!isThere(outVer, hashVertices)){
				hashVertices.put(outVer, outVer.getValue());
			}
			hashEdges.put(ed, indexE++);				
//			this.matrix[inVer.getValue()-1][outVer.getValue()-1] = this.hashEdges.get(ed);
			this.matrix[inVer.getValue()-1][outVer.getValue()-1] = this.hashEdges.get(ed);
		}
	}
	public MatrixGraph(LinkedList<EntryFile> list, int size) {
		int indexE = 0;
		Vertex inVer;
		Vertex outVer;
		Edge ed;
		
		this.size = maxList(list);
		this.hashVertices= new HashMap<Vertex, Integer>();
		this.hashEdges= new HashMap<Edge, Integer>();
		this.matrix = initMatrix(size);

		for(int i=0; i<list.size();i++){
			
			inVer = new Vertex(list.get(i).getInVertex());
			outVer = new Vertex(list.get(i).getOutVertex());
			ed= new Edge(list.get(i).getEdge());
			if(!isThere(inVer, hashVertices)){
				hashVertices.put(inVer, inVer.getValue());
			}
			if(!isThere(outVer, hashVertices)){
				hashVertices.put(outVer, outVer.getValue());
			}
			hashEdges.put(ed, indexE++);				
			this.matrix[inVer.getValue()-1][outVer.getValue()-1] = this.hashEdges.get(ed);
		}
	
	}
	private int maxList(LinkedList<EntryFile> list) {
		int max=0;
		for(int i=0; i<list.size(); i++){
				if(list.get(i).getInVertex() > max) max = list.get(i).getInVertex();
				if(list.get(i).getOutVertex() > max) max = list.get(i).getOutVertex();
			
		}
		return max;
	}
	public boolean isThere(Vertex vertex, HashMap<Vertex, Integer> haszmap){
		for(Vertex v : haszmap.keySet()){
			if(vertex.isEqual(v)){
				return true;
			}
		}
		return false;
	}
	
	private Integer[][] initMatrix(int size) {
		Integer[][] newMatrix = new Integer [size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				newMatrix[i][j] = -1;
			}
		}
		return newMatrix;
		
	}

	public boolean addVertex(Vertex vertex) {
		int index = vertex.getValue();
		if(!isThere(vertex, hashVertices)){
			if(this.size>index){
				hashVertices.put(vertex, index);
				
				for(int i=0; i<matrix.length; i++){
					matrix[i][index] = -1;
					matrix[index][i] = -1;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteVertex(Vertex vertex) {
		int position = hashVertices.get(vertex);
		if(hashVertices.containsKey(vertex)){
			for(int i=0; i<matrix.length; i++){
				matrix[i][position] = (Integer) null;
				matrix[position][i] = (Integer) null;
			}
			if(hashVertices.remove(vertex)!=null) return true;
			
		}
		return false;
	}

	public boolean addEdge(Edge edge, Vertex v1, Vertex v2) {
		int indexE = edgeCount();
		if(hashVertices.containsKey(v1)&&hashVertices.containsKey(v2)){
			int positionV1 = hashVertices.get(v1);
			int positionV2 = hashVertices.get(v2);
			hashEdges.put(edge, indexE);
			matrix[positionV1][positionV2] = indexE++;
			return true;			
		}
		return false;
	}

	public boolean deleteEdge(Edge edge) {

		if(hashEdges.containsKey(edge)){
			int position = hashEdges.get(edge);
			for(int i=0; i<matrix.length; i++){
				for(int j=0; j<matrix.length; j++){
					if(matrix[i][j] == position){
						matrix[i][j] = -1;
					}
				}
			}
			if(hashEdges.remove(edge)!=null) return true;			
		}
		return false;
	}

	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		
		LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
		if(isThere(vertex, hashVertices)){
			int position = vertex.getValue()-1;
			for(int i=0; i<matrix.length; i++){
				if( matrix[i][position] != -1 && matrix[i][position] != null){
					//hashmap has all values - int's according to names in vertices
					//whereas matrix has i and position as values -1 from the vertices' names
					int valueInHashmap = i+1;
					neighbours.add(getVertex(valueInHashmap));
				}
				if ( matrix[position][i] != -1 && matrix[position][i]!= null){
					int valueInHashmap = i+1;
					neighbours.add(getVertex(valueInHashmap));
				}
			}
		}
		return neighbours;
	}

	private Vertex getVertex(int i) {
		Vertex found = new Vertex();
		for(Vertex val : hashVertices.keySet()){
			if(hashVertices.get(val) == i ){
				found = val;	
			}
		}
		return found;
	}

	public LinkedList<Edge> incidentEdges(Vertex vertex) {
		LinkedList<Edge> incident = new LinkedList<Edge>();
		if(isThere(vertex, hashVertices)){
			int index = vertex.getValue()-1;
			for(int i=0; i<matrix.length; i++){
				if( matrix[i][index] != -1){
					incident.add(getEdge(matrix[i][index]));
				}
				if ( matrix[index][i] != -1){
					incident.add(getEdge(matrix[i][index]));
				}
			}
		}
		
		return incident;
	}

	private Edge getEdge(Integer i) {
		Edge found = new Edge();
		for(Edge val : hashEdges.keySet()){
			if(hashEdges.get(val) == i ){
				found = val;	
			}
		}
		return found;
	}

	public int vertexCount() {
		return hashVertices.size();
	}

	public int edgeCount() {
		return hashEdges.size();
	}

	public boolean areNeigbours(Vertex v1, Vertex v2) {
		if(matrix[hashVertices.get(v1)][hashVertices.get(v2)]!=-1){
			return true;
		}
		return false;
	}
	
	
}
