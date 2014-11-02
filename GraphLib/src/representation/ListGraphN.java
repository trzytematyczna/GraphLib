package representation;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class ListGraphN implements Graph {

	/**
	 * Element - structure that has info about Edge and corresponding vertex (ending/beggining depending on
	 * list [endings/beginnings])
	 * @author mra
	 *
	 */
	private class Element{
		Edge edge;
		Vertex ver;
		
		public Element(Edge edge, Vertex ver) {
			this.edge = edge;
			this.ver= ver;
		}
	}
	
	/**
	 * Structure needed for storing info about vertex and its two lists - beginnings -> when this node is a beginning of edge
	 * and endings - when it ends the edge
	 * @author mra
	 *
	 */
	private class listRep {
		Vertex vertex;
		LinkedList<Element> begin;
		LinkedList<Element> end;
		
		public listRep(Vertex v, LinkedList<Element> beg, LinkedList<Element> end) {
			this.vertex=v;
			this.begin = beg;
			this.end = end;
		}
	}
	
	listRep[] vertices;
	
	public ListGraphN(LinkedList<EntryFile> list) {
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

	@Override
	public boolean addVertex(Vertex vertex) {
		if(!isVertexExists(vertex, this.vertices)){
			int space = freeSpace();
			
			if(space==-1){
				this.vertices = enlargeMatrix(this.vertices);
				space = freeSpace();
			}
			this.vertices[space] = new listRep(vertex, new LinkedList<ListGraphN.Element>(), 
					new LinkedList<ListGraphN.Element>());
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
				if(list[i].vertex.isEqual(vertex)) return true;
			}
		}
		return false;
	}
	@Override
	public boolean deleteVertex(Vertex vertex) {
		if(!isVertexExists(vertex, this.vertices)){
			for(int i=0; i<this.vertices.length; i++){
				if(this.vertices[i]!=null){
					if(this.vertices[i].vertex.isEqual(vertex)){
						this.vertices[i].vertex = null;
						this.vertices[i].begin = null;
						this.vertices[i].end = null;
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
				if(this.vertices[i].vertex.isEqual(inVer)){
					this.vertices[i].begin.add(elementBeg);
				}
				if(this.vertices[i].vertex.isEqual(outVer)){
					this.vertices[i].end.add(elementEnd);
				}
			}
		}
		return true;
	}

	@Override
	public boolean deleteEdge(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Edge> incidentEdges(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
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
				for(int j=0; j<this.vertices[i].begin.size(); j++){
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean areNeigbours(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
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


}
