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
	
	public FordFulkersonMatrix(Vertex sour, Vertex dest, LinkedList<Edge> edg) {
		this.source = sour;
		this.destination = dest;
		for(Edge e : edg){
			this.edges.add(new FFEdge(e.getWeight(),0));
		}
	}
	
	public FordFulkersonMatrix(MatrixReprGraph graph) {
		this.graph = graph;
	}

	public void go(){
//		LinkedList<Vertex> path =  findPath(this.source, this.destination);
		Vertex[] path =  findPath(this.source, this.destination);
		int minWeight=-1;
		int i=0;
		while(path[i++]!=null){
//			minWeight = findMinWeight(path);
			for(FFEdge ffe : this.edges){
				if(ffe.weight == minWeight) continue;
				ffe.flow+= (ffe.weight - minWeight);
			}
			path = findPath(this.source, this.destination, minWeight);
		}
	}

	private int findMinWeight(LinkedList<Vertex> path) {
		// TODO Auto-generated method stub
		return 0;
	}
	public /*LinkedList<Vertex> */Vertex[] findPath(Vertex source, Vertex destination, int minWeight) {
	LinkedList<Vertex> stack = new LinkedList<Vertex>();
	LinkedList<Vertex> list = new LinkedList<Vertex>();
	Vertex[] parent = new Vertex[graph.hashVertices.size()];
	boolean marked[] =  initMarked();
//	boolean deadend=true;
//	boolean end=false;
	Vertex v;
	
	stack.add(source);
	marked[graph.hashVertices.get(source)]=true;
	parent[graph.hashVertices.get(source)]=new Vertex(-1);
	int i=0;
	while(!stack.isEmpty()){
		v = stack.getLast();
//		deadend=true;
		for(incidentEdgeVertex ev : graph.incidentEdges2(v)){
//			deadend = false;
			int index = graph.hashVertices.get(ev.vertex);
			if(marked[index]==false){
				stack.add(ev.vertex);
				marked[index]=true;
				parent[index] = v;
				if(ev.vertex.equals(destination)) {
//					int i;
					Vertex tempDest = destination;
					while(!parent[graph.hashVertices.get(tempDest)].equals(new Vertex(-1))){
						list.add(tempDest);
						tempDest = parent[graph.hashVertices.get(tempDest)];
//						if(minWeight<)
					}
					list.add(tempDest);
//					end=true;
					break;
				}
			}
		}
//		if(deadend==true || end == true){
			stack.remove(v);
//		}
	}
	return parent;
}

//	public LinkedList<Vertex> /*Vertex[]*/ findPath(Vertex source, Vertex destination) {
//		LinkedList<Vertex> stack = new LinkedList<Vertex>();
//		boolean marked[] =  initMarked();
//		Vertex[] list = new Vertex[graph.hashVertices.size()];
//		stack.add(source);
//		list[graph.hashVertices.get(source)]=source;
//		while(!stack.isEmpty()){
//			if(bfs(stack, list, stack.removeFirst(), destination, marked))
//				break;
//		}
//		return stack;
//	}
//	
//	public boolean bfs(LinkedList<Vertex> stack, Vertex[] list, Vertex source, Vertex dest, boolean[] marked){
//		if(source.equals(destination)) {
//			return true;
//		}
//		if(!marked[graph.hashVertices.get(source)]){
//			marked[graph.hashVertices.get(source)]=true;
//			for(incidentEdgeVertex ev : graph.incidentEdges2(source)){
//				list[graph.hashVertices.get(ev.vertex)] = ev.vertex;
//				stack.add(ev.vertex);
//			}			
//		}
//		return false;
//	}
	
	

//	public LinkedList<Vertex> /*Vertex[]*/ findPath(Vertex source, Vertex destination) {
//		LinkedList<Vertex> stack = new LinkedList<Vertex>();
//		LinkedList<Vertex> list = new LinkedList<Vertex>();
//		Vertex[] asd = new Vertex[graph.hashVertices.size()];
//		boolean marked[] =  initMarked();
//		Vertex v;
//		
//		stack.add(source);
//		marked[graph.hashVertices.get(source)]=true;
//		asd[graph.hashVertices.get(source)]=new Vertex(-1);
//		for(incidentEdgeVertex ev : graph.incidentEdges2(source)){
//			stack.add(ev.vertex);
//		}
//		while(!stack.isEmpty()){
//			v = stack.getLast();
//			//stack.remove(v);
//			for(incidentEdgeVertex ev : graph.incidentEdges2(v)){
//				int index = graph.hashVertices.get(ev.vertex);
//				if(marked[index]==false){
//					marked[index]=true;
//					stack.add(ev.vertex);
//					asd[index] = ev.vertex;
//					if(ev.vertex.equals(destination)) {
//						int i;
//						Vertex tempDest = destination;
//						while(!asd[graph.hashVertices.get(tempDest)].equals(new Vertex(-1))){
//							list.add(tempDest);
//							tempDest = asd[graph.hashVertices.get(tempDest)];
//						}
//						list.add(tempDest);
////						return stack;
//						return list;
////						return asd;
//					}
//				}
//			}
//		}
//		while(!stack.isEmpty()){
//			v = stack.getLast();
////			stack.removeFirst();
//			int index = graph.hashVertices.get(v);
//			if(!marked[index]){
//				marked[index]=true;
//				if(v.equals(destination)) {
//					int i;
//					return stack;
//				}
//				for(incidentEdgeVertex ev : graph.incidentEdges2(v)){
//					stack.add(ev.vertex);
//				}
//			}
//			else{
//				stack.removeLast();
//			}
//		}
//		return list;
//		return asd;
//		return stack;
//	}

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