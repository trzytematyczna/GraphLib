package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import representation.ListGraph;
import files.FileGraph;
import graph.Edge;
import graph.Vertex;

public class ListGraphTest {
	ListGraph graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf2.txt";
		this.graph = new ListGraph(fg.graphRead(path));
	}
	
	@Test
	public void deleteVertexTest(){
		this.graph.deleteVertex(new Vertex(16));
		assertEquals(3, this.graph.vertexCount());
		assertEquals(2, this.graph.edgeCount());
		assertEquals(false, this.graph.areNeigbours(new Vertex(16), new Vertex(1)));
		
	}
	@Test
	public void addVertexTest() {
		this.graph.deleteVertex(new Vertex(16));
		assertEquals(3, this.graph.vertexCount());
		assertEquals(2, this.graph.edgeCount());
		this.graph.addVertex(new Vertex(16));
		assertEquals(4, this.graph.vertexCount());
		assertEquals(2, this.graph.edgeCount());
		assertEquals(false, this.graph.areNeigbours(new Vertex(16), new Vertex(1)));
		this.graph.addEdge(new Edge(100), new Vertex(16), new Vertex(1));
		assertEquals(3, this.graph.edgeCount());
		assertEquals(true, this.graph.areNeigbours(new Vertex(1), new Vertex(16)));
	}
	

	@Test 
	public void vertexCountTest(){
		assertEquals(4, this.graph.vertexCount());
	}
	
	@Test 
	public void edgeCountTest(){
		assertEquals(3, this.graph.edgeCount());
	}
	
	@Test
	public void deleteEdgeTest(){
		this.graph.deleteEdge(new Vertex(20), new Vertex(1));
		assertEquals(2, this.graph.edgeCount());
	}
	
	@Test
	public void areNeighboursTest(){
		assertEquals(true, this.graph.areNeigbours(new Vertex(16), new Vertex(1)));
	}
	
	
	@Test
	public void vertexNeighboursTest(){
		LinkedList<Vertex> neighbours1 = new LinkedList<Vertex>();
		LinkedList<Vertex> neighbours2 = new LinkedList<Vertex>();
		neighbours1.add(new Vertex(16));
		neighbours1.add(new Vertex(20));
		
		neighbours2.add(new Vertex(20));
		
		LinkedList<Vertex> newList = this.graph.vertexNeighbours(new Vertex(1));
		
		for(int i=0; i<neighbours1.size(); i++){
			for(Vertex v : newList){
				 if(neighbours1.get(i).getName() == v.getName()){
					 assertEquals(true, neighbours1.get(i).isEqual(v));
				 }
			}
		}		
		
		this.graph.deleteVertex(new Vertex(16));
		
		newList = this.graph.vertexNeighbours(new Vertex(1));
		for(int i=0; i<neighbours2.size(); i++){
			for(Vertex v : newList){
				 if(neighbours2.get(i).getName() == v.getName()){
					 assertEquals(true, neighbours2.get(i).isEqual(v));
				 }
			}
		}		
		
		this.graph.addVertex(new Vertex(16));
		newList = this.graph.vertexNeighbours(new Vertex(1));
		for(int i=0; i<neighbours2.size(); i++){
			for(Vertex v : newList){
				 if(neighbours2.get(i).getName() == v.getName()){
					 assertEquals(true, neighbours2.get(i).isEqual(v));
				 }
			}
		}	
		this.graph.addEdge(new Edge(100), new Vertex(16), new Vertex(1));
		newList = this.graph.vertexNeighbours(new Vertex(1));
		
		for(int i=0; i<neighbours1.size(); i++){
			for(Vertex v : newList){
				 if(neighbours1.get(i).getName() == v.getName()){
					 assertEquals(true, neighbours1.get(i).isEqual(v));
				 }
			}
		}

	}
	
	@Test
	public void incidentEdgesTest(){
		LinkedList<Edge> neighbours1 = new LinkedList<Edge>();
		LinkedList<Edge> neighbours2 = new LinkedList<Edge>();
		neighbours1.add(new Edge(48));
		neighbours1.add(new Edge(10));
		
		neighbours2.add(new Edge(10));
		
		LinkedList<Edge> newList = this.graph.incidentEdges(new Vertex(1));
		
		for(int i=0; i<neighbours1.size(); i++){
			for(Edge v : newList){
				 if(neighbours1.get(i).isEqual(v)){
					 assertEquals(true, neighbours1.get(i).isEqual(v));
				 }
			}
		}		
		
		this.graph.deleteVertex(new Vertex(16));
		
		newList = this.graph.incidentEdges(new Vertex(1));
		for(int i=0; i<neighbours2.size(); i++){
			for(Edge v : newList){
				 if(neighbours2.get(i).isEqual(v)){
					 assertEquals(true, neighbours2.get(i).isEqual(v));
				 }
			}
		}			
		
		this.graph.addVertex(new Vertex(16));
		
		newList = this.graph.incidentEdges(new Vertex(1));
		for(int i=0; i<neighbours2.size(); i++){
			for(Edge v : newList){
				 if(neighbours2.get(i).isEqual(v)){
					 assertEquals(true, neighbours2.get(i).isEqual(v));
				 }
			}
		}				
		this.graph.addEdge(new Edge(100), new Vertex(16), new Vertex(1));
		
		newList = this.graph.incidentEdges(new Vertex(1));

		for(int i=0; i<neighbours2.size(); i++){
			for(Edge v : newList){
				 if(neighbours2.get(i).isEqual(v)){
					 assertEquals(true, neighbours2.get(i).isEqual(v));
				 }
			}
		}			
		
	}
	
@Test
	
	public void suma(){
//		assertEquals(4790, this.graph.weightCount());
		assertEquals(163, this.graph.weightCount());
	}
}


