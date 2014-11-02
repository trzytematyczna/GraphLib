package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListGraph;
import files.FileGraph;

public class ListGraphTest {
	ListGraph graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf2.txt";
		this.graph = new ListGraph(fg.graphRead(path));
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
}


