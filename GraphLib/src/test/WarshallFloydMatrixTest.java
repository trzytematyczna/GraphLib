package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.WarshallFloydMatrix;
import representation.MatrixReprGraph;
import files.FileGraph;
import graph.Vertex;

public class WarshallFloydMatrixTest {
	MatrixReprGraph graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf_3.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
		System.out.println(System.currentTimeMillis());
		this.graph = new MatrixReprGraph(fg.graphRead(path), 1000);
	}
	
	@Test
	public void test() {
		WarshallFloydMatrix wf = new WarshallFloydMatrix(graph.matrix.length);
		wf.go(graph);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		assertEquals(18, wf.distances[graph.hashVertices.get(v1)][graph.hashVertices.get(v2)]);
//		System.out.println(System.currentTimeMillis());
	}

}
