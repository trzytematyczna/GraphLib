package old_fordfulkerson;

import java.util.LinkedList;

import algorithms.incidentEdgeVertex;
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
				ev.edge.setWeight(ev.edge.getWeight()-pathFlow);
				ev.setFlow(ev.flow+pathFlow);

			}
			maxFlow+=pathFlow;
			path = findPath(this.source, this.destination);
		}
		return maxFlow;
	}

	private int findMinWeight(LinkedList<incidentEdgeVertex> path) {
		int minWeight=Integer.MAX_VALUE;
		for(incidentEdgeVertex ev : path){
			if(minWeight> ev.edge.getWeight()){
				minWeight = ev.edge.getWeight();
			}
		}
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
//		marked[source.getName()]=true;
//		parent[source.getName()]=new incidentEdgeVertex(new Vertex(-1), source, new Edge(-1),-1);
		while(!stack.isEmpty()){
			v = stack.getLast();
			for(incidentEdgeVertex ev : incidentEdgesFF(v)){//this.graph.incidentEdges2(v)){
				int index = this.graph.hashVertices.get(ev.outvertex);
//				int index = ev.outvertex.getName();
				if(marked[index]==false){
					if(ev.edge.getWeight()>0){
						stack.add(ev);
						marked[index]=true;
						parent[index] = v;
						if(ev.outvertex.equals(destination)) {
							incidentEdgeVertex tempDest = ev;
							this.min= tempDest.edge.getWeight();
							while(!parent[this.graph.hashVertices.get(tempDest.invertex)].invertex.equals(new Vertex(-1))){
//							while(!parent[tempDest.invertex.getName()].invertex.equals(new Vertex(-1))){
								list.add(tempDest);
								tempDest = parent[this.graph.hashVertices.get(tempDest.invertex)];
//								tempDest = parent[tempDest.invertex.getName()];
								if(tempDest.edge.getWeight()< this.min){
									this.min=tempDest.edge.getWeight();
								}
							}
							list.add(tempDest);
							if(tempDest.edge.getWeight()< this.min){
								this.min=tempDest.edge.getWeight();
							}
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
//		for(Vertex i : graph.hashVertices.keySet()){
			marked[i] = false;
//			marked[i.getName()] = false;
		}
		return marked;
	}
	
	private LinkedList<incidentEdgeVertex> incidentEdgesFF(incidentEdgeVertex v){
		LinkedList<incidentEdgeVertex> list = new LinkedList<incidentEdgeVertex>();
		for(incidentEdgeVertex ev : this.ffedges){
			if(ev.invertex.equals(v.outvertex)){
				list.add(ev);
			}
		}
		return list;
		
	}
	
}
