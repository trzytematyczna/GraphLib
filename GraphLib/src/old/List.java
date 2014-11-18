package old;

import java.util.HashMap;
import java.util.LinkedList;

import files.EntryFile;
import graph.Edge;
import graph.Vertex;

public class List {


		/**
		 * Element - structure that has info about Edge and corresponding vertex (ending/beggining depending on
		 * list [endings/beginnings])
		 * @author mra
		 *
		 */
		public class Element{
			/* (non-Javadoc)
			 * @see java.lang.Object#hashCode()
			 */
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getOuterType().hashCode();
				result = prime * result
						+ ((edge == null) ? 0 : edge.hashCode());
				result = prime * result + ((ver == null) ? 0 : ver.hashCode());
				return result;
			}

			/* (non-Javadoc)
			 * @see java.lang.Object#equals(java.lang.Object)
			 */
			@Override
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null) {
					return false;
				}
				if (!(obj instanceof Element)) {
					return false;
				}
				Element other = (Element) obj;
				if (!getOuterType().equals(other.getOuterType())) {
					return false;
				}
				if (edge == null) {
					if (other.edge != null) {
						return false;
					}
				} else if (!edge.equals(other.edge)) {
					return false;
				}
				if (ver == null) {
					if (other.ver != null) {
						return false;
					}
				} else if (!ver.equals(other.ver)) {
					return false;
				}
				return true;
			}
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

			private List getOuterType() {
				return List.this;
			}
		}
		
		
		public HashMap<Vertex, LinkedList<Element>> vertices;
		
			
//		public List(LinkedList<EntryFile> list) {
//			Vertex inVer;
//			Vertex outVer;
//			Edge newEdge;
//			
//			int size =preprocess(list);
//			this.vertices = new HashMap<Vertex, LinkedList<Element>>();
//			
//			for(int i=0; i<list.size();i++){
//				inVer = new Vertex(list.get(i).getInVertex());
//				outVer = new Vertex(list.get(i).getOutVertex());
//				newEdge = new Edge(list.get(i).getEdge());
//				
//				addVertex(inVer);
//				addVertex(outVer);
//				addEdge(newEdge, inVer, outVer);
//			}
//		}

		public List(LinkedList<EntryFile> list, int size) {
			Vertex inVer;
			Vertex outVer;
			Edge newEdge;
			
			this.vertices = new HashMap<Vertex, LinkedList<Element>>();
			
			int indexV=0;
			
			for(int i=0; i<list.size();i++){
				inVer = new Vertex(list.get(i).getInVertex());
				outVer = new Vertex(list.get(i).getOutVertex());
				newEdge = new Edge(list.get(i).getEdge());
//				addVertex(inVer);
//				addVertex(outVer);
				if(!this.vertices.containsKey(inVer)){
					this.vertices.put(inVer, new LinkedList<List.Element>());
				}
				if(!this.vertices.containsKey(outVer)){
					this.vertices.put(outVer, new LinkedList<List.Element>());
				}
				addEdge(newEdge, inVer, outVer);
			}
		}
		
		public boolean addVertex(Vertex vertex) {
			if(!this.vertices.containsKey(vertex)){
//				int space = freeSpace();
				
//				if(space==-1){
//					this.vertices = enlargeMatrix(this.vertices);
//					space = freeSpace();
//				}
//				this.vertices[space] = new listRep(vertex, new LinkedList<List.Element>(), 
//						new LinkedList<List.Element>());
				this.vertices.put(vertex, new LinkedList<List.Element>());
				return true;
			}
			return false;
		}

//		private listRep[] enlargeMatrix(listRep[] vertices2) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		private int freeSpace() {
//			for(int i =0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] == null ) return i;
//			}
//			return -1;
//		}
//		private boolean isVertexExists(Vertex vertex, listRep[] list) {
//			for(int i=0; i<list.length; i++){
//				if(list[i]!=null){
//					if(list[i].vertex.equals(vertex)) return true;
//				}
//			}
//			return false;
//		}
		public boolean deleteVertex(Vertex vertexToerase) {
//			if(isVertexExists(vertexToerase, this.vertices)){
			if(this.vertices.containsKey(vertexToerase)){
//				for(Vertex ver : this.vertices.keySet()){
//					
//				}
//				for(int i=0; i<this.vertices.length; i++){
//					if(this.vertices[i]!=null){
//						if(this.vertices[i].vertex.equals(vertexToerase)){
//							for(Element begEl : this.vertices[i].begin){
//								deleteEdge(vertexToerase, begEl.ver);
//							}
//							for(Element endEl : this.vertices[i].end){
//								deleteEdge(endEl.ver, vertexToerase);
//							}
							this.vertices.remove(vertexToerase);
//							this.vertices[i] = null;
							return true;
//						}
//					}
//				}
			}
			return false;
		}

		
		public boolean addEdge(Edge edge, Vertex inVer, Vertex outVer) {
//			if(!isVertexExists(inVer, this.vertices) || !isVertexExists(outVer, this.vertices)) return false;
			if(!this.vertices.containsKey(inVer) || !this.vertices.containsKey(outVer)) return false;
			this.vertices.get(inVer).add(new Element(edge, outVer));
//			Element elementBeg = new Element(edge, outVer);
//			Element elementEnd = new Element(edge, inVer);
//			for(int i =0; i< this.vertices.length; i++){
//				if(this.vertices[i]!=null){
//					if(this.vertices[i].vertex.equals(inVer)){
//						this.vertices[i].begin.add(elementBeg);
//					}
//					if(this.vertices[i].vertex.equals(outVer)){
//						this.vertices[i].end.add(elementEnd);
//					}
//				}
//			}
			return true;
		}

		
		public boolean deleteEdge(Vertex inVer, Vertex outVer) {
//			if(!isVertexExists(inVer, this.vertices) || !isVertexExists(outVer, this.vertices)) return false;
			if(!this.vertices.containsKey(inVer) || !this.vertices.containsKey(outVer)) return false;
			this.vertices.get(inVer).remove(new Element(new Edge(), outVer));
			for(Element elem : this.vertices.get(inVer)){
				if(elem.ver.equals(outVer)){
					this.vertices.get(inVer).remove(new Element(elem.edge, outVer));
				}
			}
//			for(int i =0; i< this.vertices.length; i++){
//				if(this.vertices[i]!=null){
//					if(this.vertices[i].vertex.equals(inVer)){
//						for(Element e: this.vertices[i].begin){
//							this.vertices[i].begin.remove(e);
//						}
//					}
//					if(this.vertices[i].vertex.equals(outVer)){
//						for(Element e: this.vertices[i].end){
//							this.vertices[i].end.remove(e);
//						}				
//					}
//				}
//			}
			return true;
		}

		
//		public LinkedList<Vertex> vertexNeighbours(Vertex vertex) {
//			LinkedList<Vertex> neighbours = new LinkedList<Vertex>();
//			if(isVertexExists(vertex, this.vertices)){
//				for(int i=0; i<this.vertices.length; i++){
//					if(this.vertices[i]!=null){
//						if(this.vertices[i].vertex.equals(vertex)){
//							for(Element begEl : this.vertices[i].begin){
//								neighbours.add(begEl.ver);
//							}
//							for(Element endEl : this.vertices[i].end){
//								neighbours.add(endEl.ver);
//							}
//							
//							this.vertices[i] = null;
//						}
//					}
//				}
//			}
//			return neighbours;
//		}
//
//		
//		public LinkedList<Edge> incidentEdges(Vertex vertex) {
//			LinkedList<Edge> incident = new LinkedList<Edge>();
//			if(isVertexExists(vertex, this.vertices)){
//				for(int i=0; i<this.vertices.length; i++){
//					if(this.vertices[i]!=null){
//						if(this.vertices[i].vertex.equals(vertex)){
//							for(Element begEl : this.vertices[i].begin){
//								incident.add(begEl.edge);
//							}
//							for(Element endEl : this.vertices[i].end){
//								incident.add(endEl.edge);
//							}
//							this.vertices[i] = null;
//						}
//					}
//				}
//			}
//			return incident;
//		}
//
//		
//		public int vertexCount() {
//			int count = 0;
//			for(int i = 0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] != null ) count++;
//			}
//			return count;
//		}
//
//		
//		public int edgeCount() {
//			int count = 0;
//			for(int i = 0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] != null ) {
//						count+=this.vertices[i].begin.size();
////						for(Element e : this.vertices[i].begin){
////							if(this.vertices[i].vertex.getName()<e.ver.getName())count+=e.edge.getWeight();
////						}
//				}
//			}
//			return count;
//		}
//
//		
//		public boolean areNeigbours(Vertex v1, Vertex v2) {
//			if(isVertexExists(v1, this.vertices) && isVertexExists(v2, this.vertices)){
//				for(int i =0; i< this.vertices.length; i++){
//					if(this.vertices[i]!=null){
//						if(this.vertices[i].vertex.equals(v1)){
//							for(Element e: this.vertices[i].begin){
//								if(e.ver.equals(v2)) return true;
//							}
//							for(Element e: this.vertices[i].end){
//								if(e.ver.equals(v2)) return true;
//							}
//						}
//					}
//				}
//			}
//			return false;
//		}
//		public boolean areNeigboursOneSided(Vertex v1, Vertex v2) {
//			if(isVertexExists(v1, this.vertices) && isVertexExists(v2, this.vertices)){
//				for(int i =0; i< this.vertices.length; i++){
//					if(this.vertices[i]!=null){
//						if(this.vertices[i].vertex.equals(v1)){
//							for(Element e: this.vertices[i].begin){
//								if(e.ver.equals(v2)) return true;
//							}
//						}
//					}
//				}
//			}
//			return false;
//		}
//		private int preprocess(LinkedList<EntryFile> list){
//			LinkedList<Integer> values = new LinkedList<Integer>();
//
//			for(int i=0; i<list.size();i++){
//				if(!values.contains(list.get(i).getInVertex())){
//					values.add(list.get(i).getInVertex());
//				}
//				if(!values.contains(list.get(i).getOutVertex())){
//					values.add(list.get(i).getOutVertex());
//				}
//			}
//			
//			return values.size();
//		}
//
//		public int weightCount() {
//			int count = 0;
//			for(int i = 0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] != null ) {
//						for(Element e : this.vertices[i].begin){
//							if(this.vertices[i].vertex.getName()<e.ver.getName())count+=e.edge.getWeight();
//						}
//				}
//			}
//			return count;
//		}
//		
//		public Edge getEdge(Vertex v1,Vertex v2){
//			for(int i = 0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] != null ) {
//					if(this.vertices[i].getVertex().equals(v1)){
//						for (Element e : this.vertices[i].getBeginList()){
//							if(e.ver.equals(v2)) return e.edge;
//						}
//					}
//				}
//			}
//			return null;
//			
//		}
//		
//		public int getIndex(Vertex vertex){
//			for(int i = 0 ; i<this.vertices.length;i++){
//				if(this.vertices[i] != null ) {
//					if(this.vertices[i].getVertex().equals(vertex)) return i;
//					}
//			}
//			return -1;
//		}
}

