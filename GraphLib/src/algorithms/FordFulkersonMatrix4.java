package algorithms;

import java.util.LinkedList;

import graph.Edge;
import graph.Vertex;
import representation.MatrixReprGraph;

public class FordFulkersonMatrix4 {

	Vertex source;
	Vertex destination;
	MatrixReprGraph graph;
	LinkedList<incidentEdgeVertex> ffedges;
	int min;
	
	public FordFulkersonMatrix4(MatrixReprGraph graph,Vertex sour, Vertex dest) {
		this.graph = graph;
		this.source = sour;
		this.destination = dest;
		this.ffedges = new LinkedList<incidentEdgeVertex>();
		for(int i=0; i<graph.matrix.length; i++){
			for(int j=0; j<graph.matrix.length; j++){
					if(this.graph.matrix[i][j]!=null){
						this.ffedges.add(new incidentEdgeVertex(this.graph.getVertexFromValue(i),this.graph.getVertexFromValue(j), this.graph.matrix[i][j], 0));
				}
			}
		}
	}

	public int go(){
		int maxFlow=0;
		LinkedList<incidentEdgeVertex> path =  findPath(this.source, this.destination);
		int pathFlow=-1;
		while(!path.isEmpty()){
			pathFlow  = this.min;//findMinWeight(path);
			for(incidentEdgeVertex ev : path){
							
//					incidentEdgeVertex ev = getEdge(from,to);
				ev.edge.setWeight(ev.edge.getWeight()-pathFlow);
				ev.setFlow(ev.flow+pathFlow);

			}
			
			maxFlow+=pathFlow;
			path = findPath(this.source, this.destination);
		}
		return maxFlow;
	}

//		private incidentEdgeVertex getEdge(Vertex v1, Vertex v2) {
//			for(incidentEdgeVertex ev : this.ffedges){
//				if(ev.invertex.equals(v1) && ev.outvertex.equals(v2)){
//					return ev;
//				}
//			}
//			return null;
//		}
	private int findMinWeight(LinkedList<incidentEdgeVertex> path) {
		int minWeight=Integer.MAX_VALUE;
		for(incidentEdgeVertex ev : path){
			if(minWeight> ev.edge.getWeight()){
				minWeight = ev.edge.getWeight();
			}
		}
//			ListIterator<Vertex> iterator = path.listIterator();
//			int i=0;
//			while(i<path.size()-1){
//				int to = this.graph.hashVertices.get(iterator.next());
//				int from = this.graph.hashVertices.get(iterator.next());
//				i+=2;
//				if (this.graph.matrix[from][to].getWeight() < minWeight){
//					minWeight = this.graph.matrix[from][to].getWeight();
//				}
//			}
		
		return minWeight;
	}
	
	public LinkedList<incidentEdgeVertex> findPath(Vertex source, Vertex destination) {
		LinkedList<incidentEdgeVertex> stack = new LinkedList<incidentEdgeVertex>();
		LinkedList<incidentEdgeVertex> list = new LinkedList<incidentEdgeVertex>();
		incidentEdgeVertex[] parent = new incidentEdgeVertex[this.graph.hashVertices.size()];
		boolean marked[] =  initMarked();
		incidentEdgeVertex v;
		
		stack.add(new incidentEdgeVertex(new Vertex(-1), source, new Edge(-1), -1));
		marked[this.graph.hashVertices.get(source)]=true;
		parent[this.graph.hashVertices.get(source)]=new incidentEdgeVertex(new Vertex(-1), source, new Edge(-1),-1);
		while(!stack.isEmpty()){
			v = stack.getLast();
			for(incidentEdgeVertex ev : incidentEdgesFF(v)){//this.graph.incidentEdges2(v)){
				int index = this.graph.hashVertices.get(ev.outvertex);
				if(marked[index]==false){
					if(ev.edge.getWeight()>0){
						stack.add(ev);
						marked[index]=true;
						parent[index] = v;
						if(ev.outvertex.equals(destination)) {
//								incidentEdgeVertex tempDest = new incidentEdgeVertex(new Vertex(-1), destination, new Edge(-1), -1);
							incidentEdgeVertex tempDest = ev;
							this.min= Integer.MAX_VALUE;
//								while(!parent[this.graph.hashVertices.get(tempDest.outvertex)].invertex.equals(source)){
							while(!parent[this.graph.hashVertices.get(tempDest.invertex)].invertex.equals(new Vertex(-1))){
								list.add(tempDest);
								tempDest = parent[this.graph.hashVertices.get(tempDest.invertex)];
								if(tempDest.edge.getWeight()< this.min){
									this.min=tempDest.edge.getWeight();
								}
							}
							list.add(tempDest);
							break;
						}
					}
				}
			}
				stack.remove(v);
	}
	
		return list;
}

	private boolean[] initMarked() {
		boolean marked[] = new boolean[graph.hashVertices.size()];
		for(int i : graph.hashVertices.values()){
			marked[i] = false;
		}
		return marked;
	}
	
	private LinkedList<incidentEdgeVertex> incidentEdgesFF(incidentEdgeVertex v){
		LinkedList<incidentEdgeVertex> list = new LinkedList<incidentEdgeVertex>();
		for(incidentEdgeVertex ev : this.ffedges){
			if(ev.invertex.equals(v.outvertex)){
//				if(ev.outvertex.equals(v.outvertex)){
				list.add(ev);
			}
		}
		return list;
		
	}
	
}
