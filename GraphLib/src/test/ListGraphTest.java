package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListGraph;
import files.FileGraph;
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
		assertEquals();
	}
	@Test
	public void addVertexTest() {
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
}


