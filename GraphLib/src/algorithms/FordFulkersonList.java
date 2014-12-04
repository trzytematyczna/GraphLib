package algorithms;

import java.util.LinkedList;

import graph.Edge;
import graph.Vertex;
import representation.ListReprGraph;
import representation.ListReprGraph.Element;
import representation.ListReprGraph.listRep;

public class FordFulkersonList {

	Vertex source;
	Vertex destination;
	ListReprGraph graph;
	int size;
	LinkedList<incidentEdgeVertex> ffedges;
	int min;
	
	public FordFulkersonList (ListReprGraph graph,Vertex sour, Vertex dest) {
		this.graph = graph;
		this.source = sour;
		this.destination = dest;
		this.size = this.graph.vertices.length+1;
		this.ffedges = new LinkedList<incidentEdgeVertex>();
		for(listRep li: this.graph.vertices){
			if(li!=null){
				for(Element el : li.getBeginList()){
						this.ffedges.add(new incidentEdgeVertex(li.getVertex(), el.getVertex(), el.getEdge(), 0));
				}
			}
		}
		int i=0;
		i++;
	}

	public int go(){
		int maxFlow=0;
		LinkedList<incidentEdgeVertex> path =  findPath(this.source, this.destination);
		int pathFlow=-1;
		int count = 1;
		while(!path.isEmpty()){
			pathFlow  = findMinWeight(path);
			for(incidentEdgeVertex ev : path){
				ev.edge.setWeight(ev.edge.getWeight()-pathFlow);
				ev.setFlow(ev.flow+pathFlow);
				if(ev.edge.getWeight() <= 0){
					this.ffedges.remove(ev);
				}
			}
			maxFlow+=pathFlow;
			path = findPath(this.source, this.destination);
			count++;
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
		incidentEdgeVertex[] parent = new incidentEdgeVertex[this.size];
		boolean marked[] =  initMarked();
		incidentEdgeVertex v;
		
		stack.add(new incidentEdgeVertex(new Vertex(-1), source, new Edge(-1), -1));
		marked[source.getName()]=true;
		parent[source.getName()]=new incidentEdgeVertex(new Vertex(-1), source, new Edge(-1),-1);
		while(!stack.isEmpty()){
			v = stack.getLast();
			for(incidentEdgeVertex ev : incidentEdgesFF(v)){//this.graph.incidentEdges2(v)){
				int index = ev.outvertex.getName();
				if(marked[index]==false){
					if(ev.edge.getWeight()>0){
						stack.add(ev);
						marked[index]=true;
						parent[index] = v;
						if(ev.outvertex.equals(destination)) {
							incidentEdgeVertex tempDest = ev;
							this.min= Integer.MAX_VALUE;
//							while(!parent[this.graph.hashVertices.get(tempDest.invertex)].invertex.equals(new Vertex(-1))){
							while(!parent[tempDest.invertex.getName()].invertex.equals(new Vertex(-1))){
								list.add(tempDest);
								tempDest = parent[tempDest.invertex.getName()];
//								if(tempDest.edge.getWeight()< this.min){
//									this.min=tempDest.edge.getWeight();
//								}
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
		boolean marked[] = new boolean[this.size];
//		for(int i : graph.hashVertices.values()){
		for(listRep li: this.graph.vertices){
//			marked[i] = false;
			marked[li.getVertex().getName()] = false;
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
