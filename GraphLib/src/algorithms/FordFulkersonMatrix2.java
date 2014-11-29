package algorithms;

import java.util.LinkedList;

import graph.Edge;
import graph.Vertex;
import representation.MatrixReprGraph;

public class FordFulkersonMatrix2 {

	Vertex source;
	Vertex destination;
	MatrixReprGraph graph;
	LinkedList<incidentEdgeVertex> ffedges;
	int min;
	
//	public FordFulkersonMatrix2(Vertex sour, Vertex dest, LinkedList<Edge> edg) {
//		this.source = sour;
//		this.destination = dest;
//		for(Edge e : edg){
//			this.edges.add(new incidentEdgeVertex(e.getWeight(),0, ));
//		}
//	}
	
	public FordFulkersonMatrix2(MatrixReprGraph graph,Vertex sour, Vertex dest) {
		this.graph = graph;
		this.source = sour;
		this.destination = dest;
		this.ffedges = new LinkedList<incidentEdgeVertex>();
		for(int i=0; i<graph.matrix.length; i++){
			for(int j=0; j<graph.matrix.length; j++){
//				if(this.graph.matrix[i]!=null){
					if(this.graph.matrix[i][j]!=null){
						this.ffedges.add(new incidentEdgeVertex(this.graph.getVertexFromValue(i),this.graph.getVertexFromValue(j), this.graph.matrix[i][j], 0));
				}
			}
		}
	}

	public int go(){
		int maxFlow=0;
//		LinkedList<Vertex> path =  findPath(this.source, this.destination);
		LinkedList<Vertex> path =  findPath(this.source, this.destination);
		int pathFlow=-1;
		while(!path.isEmpty()){
			pathFlow  = findMinWeight(path);

			for (int i=0; i<path.size()-1;i++){
				Vertex v1= path.get(i);
				Vertex v2 = path.get(i+1);
//				int from = this.graph.hashVertices.get(v1);
//				int to = this.graph.hashVertices.get(v2);
				
				incidentEdgeVertex ev = getEdge(v1,v2);
				ev.edge.setWeight(ev.edge.getWeight()-pathFlow);
				ev.setFlow(ev.flow+pathFlow);
				
//				this.graph.matrix[from][to].setWeight((this.graph.matrix[from][to].getWeight())+pathFlow);
//				
//				if(this.graph.matrix[to][from]!=null){
//						this.graph.matrix[to][from].setWeight((this.graph.matrix[to][from].getWeight())-pathFlow);
//				}
//				else{
//					this.graph.addEdge(new Edge(pathFlow),v2, v1);
////					this.graph.addVertex(path.get(++i));
////					this.graph.matrix[to][from]= new Edge((this.graph.matrix[to][from].getWeight())-pathFlow);					
//				}
				int p=0;
			}
			maxFlow+=pathFlow;
			path = findPath(this.source, this.destination);
		}
		return maxFlow;
	}

	private incidentEdgeVertex getEdge(Vertex v1, Vertex v2) {
		for(incidentEdgeVertex ev : this.ffedges){
			Vertex in = ev.invertex;
			Vertex out = ev.outvertex;
			if(in.equals(v1) && out.equals(v2)){
				return ev;
			}
		}
		return null;
	}
	private int findMinWeight(LinkedList<Vertex> path) {
		int minWeight=Integer.MAX_VALUE;

		for (int i=0; i<path.size()-1;){
			int from = this.graph.hashVertices.get(path.get(i));
			int to = this.graph.hashVertices.get(path.get(++i));
			
		
			if (this.graph.matrix[from][to].getWeight() < minWeight){
				minWeight = this.graph.matrix[from][to].getWeight();
			}
		}
		return minWeight;
	}
//	private int findMinWeight(LinkedList<Vertex> path) {
//		int minWeight=Integer.MAX_VALUE;
//
//		for (int i=0; i<path.size()-1;){
//			int from = this.graph.hashVertices.get(path.get(i));
//			int to = this.graph.hashVertices.get(path.get(++i));
//			
//		
//			if (this.graph.matrix[from][to].getWeight() < minWeight){
//				minWeight = this.graph.matrix[from][to].getWeight();
//			}
//		}
//		return minWeight;
//	}
	
	public LinkedList<Vertex> findPath(Vertex source, Vertex destination) {
		LinkedList<Vertex> stack = new LinkedList<Vertex>();
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		Vertex[] parent = new Vertex[this.graph.hashVertices.size()];
		boolean marked[] =  initMarked();
		Vertex v;
		
		stack.add(source);
		marked[this.graph.hashVertices.get(source)]=true;
		parent[this.graph.hashVertices.get(source)]=new Vertex(-1);
		int i=0;
		while(!stack.isEmpty()){
			v = stack.getLast();
			for(incidentEdgeVertex ev : incidentEdgesFF(v)){//this.graph.incidentEdges2(v)){
//				int index = this.graph.hashVertices.get(ev.invertex);
				int index = this.graph.hashVertices.get(ev.outvertex);
				if(marked[index]==false){
					if(ev.edge.getWeight()>0){
//						stack.add(ev.invertex);
						stack.add(ev.outvertex);
						marked[index]=true;
						parent[index] = v;
//						if(ev.invertex.equals(destination)) {
						if(ev.outvertex.equals(destination)) {
							Vertex tempDest = destination;
							int min= Integer.MAX_VALUE;
							while(!parent[this.graph.hashVertices.get(tempDest)].equals(new Vertex(-1))){
								list.add(tempDest);
								if(getEdge(parent[this.graph.hashVertices.get(tempDest)],tempDest).edge.getWeight()<min){
									min = getEdge(parent[this.graph.hashVertices.get(tempDest)],tempDest).edge.getWeight();
								}
								tempDest = parent[this.graph.hashVertices.get(tempDest)];
							}
							list.add(tempDest);
							break;
						}
					}
				}
			}
				stack.remove(v);
	}
	
	return reverse(list);
}

	private LinkedList<Vertex> reverse(LinkedList<Vertex> list){
		LinkedList<Vertex> nestlist = new LinkedList<Vertex>();
		int ji=list.size();
		for(int i=list.size()-1; i>=0; i--){
			nestlist.add(list.get(i));
		}
		return nestlist;
	}
	private boolean[] initMarked() {
		boolean marked[] = new boolean[graph.hashVertices.size()];
		for(int i : graph.hashVertices.values()){
			marked[i] = false;
		}
		return marked;
	}
	
	private LinkedList<incidentEdgeVertex> incidentEdgesFF(Vertex v){
		LinkedList<incidentEdgeVertex> list = new LinkedList<incidentEdgeVertex>();
		for(incidentEdgeVertex ev : this.ffedges){
			if(ev.invertex.equals(v)){
				list.add(ev);
			}
		}
		return list;
		
	}
	
}

//class FFEdge2{
//	int weight;
//	int flow;
//	int begin;
//	int end;
//	
//	public FFEdge2(int wei, int initFlow) {
//		this.weight = wei;
//		this.flow = initFlow;
//	}
//}