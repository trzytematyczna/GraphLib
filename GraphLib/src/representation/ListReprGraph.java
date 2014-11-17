package representation;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

import java.util.LinkedList;


public class ListReprGraph implements Graph {

	/**
	 * Element - structure that has info about Edge and corresponding vertex (ending/beggining depending on
	 * list [endings/beginnings])
	 * @author mra
	 *
	 */
	public class Element{
		Edge edge;
		Vertex ver;
		
		public Element(Edge edge, Vertex ver) {
			this.edge = edge;
			this.ver= ver;
		}
		
		public Edge getEdge(){
			return this.edge;
		}
		public Vertex getVertex(){
			return this.ver;
		}
	}
	
	/**
	 * Structure needed for storing info about vertex and its two lists - beginnings -> when this node is a beginning of edge
	 * and endings - when it ends the edge
	 * @author mra
	 *
	 */
	public class listRep {
		Vertex vertex;
		LinkedList<Element> begin;
		LinkedList<Element> end;
		
		public listRep(Vertex v, LinkedList<Element> beg, LinkedList<Element> end) {
			this.vertex=v;
			this.begin = beg;
			this.end = end;
		}
		
		public Vertex getVertex(){
			return this.vertex;
		}
		public LinkedList<Element> getBeginList(){
			return this.begin;
		}
		public LinkedList<Element> getEndList(){
			return this.end;
		}
	}
	
	public listRep[] vertices;
	
	public ListReprGraph(LinkedList<EntryFile> list) {
		Vertex inVer;
		Vertex outVer;
		Edge newEdge;
		
		int size =preprocess(list);
		this.vertices = new listRep[size];
		
		for(int i=0; i<list.size();i++){
			inVer = new Vertex(list.get(i).getInVertex());
			outVer = new Vertex(list.get(i).getOutVertex());
			newEdge = new Edge(list.get(i).getEdge());
			
			addVertex(inVer);
			addVertex(outVer);
			addEdge(newEdge, inVer, outVer);
		}
	}

	public ListReprGraph(LinkedList<EntryFile> list, int size) {
		Vertex inVer;
		Vertex outVer;
		Edge newEdge;
		
		this.vertices = new listRep[size];
		
		int indexV=0;
		
		for(int i=0; i<list.size();i++){
			inVer = new Vertex(list.get(i).getInVertex());
			outVer = new Vertex(list.get(i).getOutVertex());
			newEdge = new Edge(list.get(i).getEdge());
//			addVertex(inVer);
//			addVertex(outVer);
			if(!isVertexExists(inVer, this.vertices)){
				this.vertices[indexV++] = new listRep(inVer, new LinkedList<ListReprGraph.Element>(), 
					new LinkedList<ListReprGraph.Element>());
			}			
			if(!isVertexExists(outVer, this.vertices)){
				this.vertices[indexV++] = new listRep(outVer, new LinkedList<ListReprGraph.Element>(), 
					new LinkedList<ListReprGraph.Element>());
			}
			addEdge(newEdge, inVer, outVer);
		}
	}
	
	@Override
	public boolean addVertex(Vertex vertex) {
		if(!isVertexExists(vertex, this.vertices)){
			int space = freeSpace();
			
			if(space==-1){
				this.vertices = enlargeMatrix(this.vertices);
				space = freeSpace();
			}
			this.vertices[space] = new listRep(vertex, new LinkedList<ListReprGraph.Element>(), 
					new LinkedList<ListReprGraph.Element>());
			return true;
		}
		return false;
	}

	private listRep[] enlargeMatrix(listRep[] vertices2) {
		// TODO Auto-generated method stub
		return null;
	}
	private int freeSpace() {
		for(int i =0 ; i<this.vertices.length;i++){
			if(this.vertices[i] == null ) return i;
		}
		return -1;
	}
	private boolean isVertexExists(Vertex vertex, listRep[] list) {
		for(int i=0; i<list.length; i++){
			if(list[i]!=null){
				if(list[i].vertex.equals(vertex)) return true;
			}
		}
		return false;
	}
	@Override
	public boolean deleteVertex(Vertex vertexToerase) {
		if(isVertexExists(vertexToerase, this.vertices)){
			for(int i=0; i<this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.equals(vertexToerase)){
						for(Element begEl : this.vertices[i].begin){
							deleteEdge(vertexToerase, begEl.ver);
						}
						for(Element endEl : this.vertices[i].end){
							deleteEdge(endEl.ver, vertexToerase);
						}
						
						this.vertices[i] = null;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean addEdge(Edge edge, Vertex inVer, Vertex outVer) {
		if(!isVertexExists(inVer, this.vertices) || !isVertexExists(outVer, this.vertices)) return false;
		Element elementBeg = new Element(edge, outVer);
		Element elementEnd = new Element(edge, inVer);
		for(int i =0; i< this.vertices.length; i++){
			if(this.vertices[i]!=null){
				if(this.vertices[i].vertex.equals(inVer)){
					this.vertices[i].begin.add(elementBeg);
				}
				if(this.vertices[i].vertex.equals(outVer)){
					this.vertices[i].end.add(elementEnd);
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteEdge(Vertex inVer, Vertex outVer) {
		if(!isVertexExists(inVer, this.vertices) || !isVertexExists(outVer, this.vertices)) return false;
		for(int i =0; i< this.vertices.length; i++){
			if(this.vertices[i]!=null){
				if(this.vertices[i].vertex.equals(inVer)){
					for(Element e: this.vertices[i].begin){
						this.vertices[i].begin.remove(e);
					}
				}
				if(this.vertices[i].vertex.equals(outVer)){
					for(Element e: this.vertices[i].end){
						this.vertices[i].end.remove(e);
					}				
				}
			}
		}
		return true;
	}

	@Override
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
		if(isVertexExists(vertex, this.vertices)){
			for(int i=0; i<this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.equals(vertex)){
						for(Element begEl : this.vertices[i].begin){
							neighbours.add(begEl.ver);
						}
						for(Element endEl : this.vertices[i].end){
							neighbours.add(endEl.ver);
						}
						
						this.vertices[i] = null;
					}
				}
			}
		}
		return neighbours;
	}

	@Override
	public LinkedList<Edge> incidentEdges(Vertex vertex) {
		LinkedList<Edge> incident = new LinkedList<Edge>();
		if(isVertexExists(vertex, this.vertices)){
			for(int i=0; i<this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.equals(vertex)){
						for(Element begEl : this.vertices[i].begin){
							incident.add(begEl.edge);
						}
						for(Element endEl : this.vertices[i].end){
							incident.add(endEl.edge);
						}
						this.vertices[i] = null;
					}
				}
			}
		}
		return incident;
	}

	@Override
	public int vertexCount() {
		int count = 0;
		for(int i = 0 ; i<this.vertices.length;i++){
			if(this.vertices[i] != null ) count++;
		}
		return count;
	}

	@Override
	public int edgeCount() {
		int count = 0;
		for(int i = 0 ; i<this.vertices.length;i++){
			if(this.vertices[i] != null ) {
					count+=this.vertices[i].begin.size();
//					for(Element e : this.vertices[i].begin){
//						if(this.vertices[i].vertex.getName()<e.ver.getName())count+=e.edge.getWeight();
//					}
			}
		}
		return count;
	}

	@Override
	public boolean areNeigbours(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.vertices) && isVertexExists(v2, this.vertices)){
			for(int i =0; i< this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.equals(v1)){
						for(Element e: this.vertices[i].begin){
							if(e.ver.equals(v2)) return true;
						}
						for(Element e: this.vertices[i].end){
							if(e.ver.equals(v2)) return true;
						}
					}
				}
			}
		}
		return false;
	}
	public boolean areNeigboursOneSided(Vertex v1, Vertex v2) {
		if(isVertexExists(v1, this.vertices) && isVertexExists(v2, this.vertices)){
			for(int i =0; i< this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.equals(v1)){
						for(Element e: this.vertices[i].begin){
							if(e.ver.equals(v2)) return true;
						}
					}
				}
			}
		}
		return false;
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
		int count = 0;
		for(int i = 0 ; i<this.vertices.length;i++){
			if(this.vertices[i] != null ) {
					for(Element e : this.vertices[i].begin){
						if(this.vertices[i].vertex.getName()<e.ver.getName())count+=e.edge.getWeight();
					}
			}
		}
		return count;
	}
	
	public Edge getEdge(Vertex v1,Vertex v2){
		for(int i = 0 ; i<this.vertices.length;i++){
			if(this.vertices[i] != null ) {
				if(this.vertices[i].getVertex().equals(v1)){
					for (Element e : this.vertices[i].getBeginList()){
						if(e.ver.equals(v2)) return e.edge;
					}
				}
			}
		}
		return null;
		
	}
	
	public int getIndex(Vertex vertex){
		for(int i = 0 ; i<this.vertices.length;i++){
			if(this.vertices[i] != null ) {
				if(this.vertices[i].getVertex().equals(vertex)) return i;
				}
		}
		return -1;
	}
}
