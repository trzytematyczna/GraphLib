package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListReprGraph;
import algorithms.WarshallFloydList;
import files.FileGraph;
import graph.Vertex;

public class WarshallFloydListReprGraphTest {

	ListReprGraph graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf_3.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
		System.out.println(System.currentTimeMillis());
		this.graph = new ListReprGraph(fg.graphRead(path), 1000);
	}
	
	@Test
	public void test() {
		WarshallFloydList wf = new WarshallFloydList(graph.vertices.length);
		wf.go(graph);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		
		assertEquals(18, wf.distances[graph.getIndex(v1)][graph.getIndex(v2)]);
//		assertEquals(55, wf.distances[graph.getIndex(v1)][graph.getIndex(v2)]);
//		System.out.println(System.currentTimeMillis());
	}

}
