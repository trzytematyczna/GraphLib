package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import files.FileGraph;
import graph.Edge;
import graph.Vertex;
import representation.MGraph;

public class MGraphTest {

	MGraph graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf2.txt";
		this.graph = new MGraph(fg.graphRead(path));
	}
	
	@Test
	public void deleteVertexTest() {
		this.graph.deleteVertex(new Vertex(16));
		assertEquals(3,this.graph.vertexCount());
	}
	
	@Test
	public void addVertexTest(){
		//4 : 1 16 20 8
		this.graph.deleteVertex(new Vertex(16));
		//3: 1 20 8
		this.graph.addVertex(new Vertex(17));
		//4: 1 20 8 17
		this.graph.addVertex(new Vertex(18));
		//5: 1 20 8 17 18
		this.graph.addVertex(new Vertex(19));
		//6: 1 20 8 17 18 19
		assertEquals(6, this.graph.vertexCount());
		assertEquals(true, this.graph.areNeigbours(new Vertex(20), new Vertex(8)));
	}
	
	@Test
	public void addVertexEnlargeTest(){
		this.graph.addVertex(new Vertex(17));
		assertEquals(5, this.graph.vertexCount());
	}
	
	@Test
	public void addEdgeTest(){
//		assertEquals(true,this.graph.addEdge(new Edge(17), new Vertex(16), new Vertex(1)));
		this.graph.addEdge(new Edge(17), new Vertex(16), new Vertex(1));
		assertEquals(4,this.graph.edgeCount());
	}
	
	@Test
	public void deleteEdgeTest(){
		this.graph.deleteEdge(new Vertex(8), new Vertex(20));
		assertEquals(2, this.graph.edgeCount());
		this.graph.addEdge(new Edge(55), new Vertex(20), new Vertex(16));
		assertEquals(3, this.graph.edgeCount());
		this.graph.deleteEdge(new Vertex(1), new Vertex(16));
		assertEquals(2, this.graph.edgeCount());
	}
	
	@Test
	public void vertexNeighboursTest(){
		LinkedList<Vertex> vertex = new LinkedList<Vertex>();
		vertex.add(new Vertex(16));
		vertex.add(new Vertex(20));
		LinkedList<Vertex> neigh = this.graph.vertexNeighbours(new Vertex(1));
		
		for(int i=0; i<neigh.size(); i++){
			for(Vertex v : vertex){
				 if(neigh.get(i).getName() == v.getName()){
					 assertEquals(true, neigh.get(i).isEqual(v));
				 }
			}
		}
		

//		assertEquals(true, neigh.get(0).isEqual(vertex.get(0)));
	}
	@Test
	public void incidentEdgesTest(){
		LinkedList<Edge> edge = new LinkedList<Edge>();
		edge.add(new Edge(48));
		edge.add(new Edge(10));
		
		LinkedList<Edge> neigh = this.graph.incidentEdges(new Vertex(1));
		
		for(int i=0; i<neigh.size(); i++){
			for(Edge e : edge){
				 if(neigh.get(i).getWeight() == e.getWeight()){
					 assertEquals(true, neigh.get(i).isEqual(e));
				 }
			}
		}
		
		
	}
	@Test 
	public void areNeighboursTest(){
		assertEquals(true, this.graph.areNeigbours(new Vertex(8), new Vertex(20)));
		assertEquals(false, this.graph.areNeigbours(new Vertex(8), new Vertex(10)));
	}
	
	@Test
	
	public void weightContTest(){
		assertEquals(163, this.graph.weightCount());
	}

}

