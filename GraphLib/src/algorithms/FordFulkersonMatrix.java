package algorithms;

import java.util.LinkedList;

import representation.MatrixReprGraph;
import algorithms_done.BellmanFordMatrix;
import graph.Edge;
import graph.Vertex;

public class FordFulkersonMatrix {

	Vertex source;
	Vertex destination;
	MatrixReprGraph graph;
	LinkedList<FFEdge> edges;
	int min;
	
	public FordFulkersonMatrix(Vertex sour, Vertex dest, LinkedList<Edge> edg) {
		this.source = sour;
		this.destination = dest;
		for(Edge e : edg){
			this.edges.add(new FFEdge(e.getWeight(),0));
		}
	}
	
	public FordFulkersonMatrix(MatrixReprGraph graph,Vertex sour, Vertex dest) {
		this.graph = graph;
		this.source = sour;
		this.destination = dest;
		this.edges = new LinkedList<FFEdge>();
		for(Edge e : this.graph.getAllEdges()){
			this.edges.add(new FFEdge(e.getWeight(),0));
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
				int from = this.graph.hashVertices.get(v1);
				int to = this.graph.hashVertices.get(v2);
				
				this.graph.matrix[from][to].setWeight((this.graph.matrix[from][to].getWeight())+pathFlow);
				
				if(this.graph.matrix[to][from]!=null){
						this.graph.matrix[to][from].setWeight((this.graph.matrix[to][from].getWeight())-pathFlow);
				}
				else{
					this.graph.addEdge(new Edge(pathFlow),v2, v1);
//					this.graph.addVertex(path.get(++i));
//					this.graph.matrix[to][from]= new Edge((this.graph.matrix[to][from].getWeight())-pathFlow);					
				}
				int p=0;
			}
			maxFlow+=pathFlow;
			path = findPath(this.source, this.destination);
		}
		return pathFlow;
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
		for(incidentEdgeVertex ev : this.graph.incidentEdges2(v)){
			int index = this.graph.hashVertices.get(ev.vertex);
			if(marked[index]==false){
				stack.add(ev.vertex);
				marked[index]=true;
				parent[index] = v;
				if(ev.vertex.equals(destination)) {
					Vertex tempDest = destination;
					while(!parent[this.graph.hashVertices.get(tempDest)].equals(new Vertex(-1))){
						list.add(tempDest);
						tempDest = parent[this.graph.hashVertices.get(tempDest)];
//						if(minWeight<)
					}
					list.add(tempDest);
					break;
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
	
}

class FFEdge{
	int weight;
	int flow;
	int begin;
	int end;
	
	public FFEdge(int wei, int initFlow) {
		this.weight = wei;
		this.flow = initFlow;
	}
	
}