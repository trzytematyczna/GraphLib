package test_done;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms_done.WarshallFloydMatrix;
import representation.MatrixReprGraph;
import files.FileGraph;
import graph.Vertex;

public class WarshallFloydMatrixTest {
	MatrixReprGraph graph;
	WarshallFloydMatrix wf;
	int vertices_count = 1000;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf_3.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
		
		long mstart = System.currentTimeMillis();
		this.graph = new MatrixReprGraph(fg.graphRead(path), this.vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
		
		this.wf = new WarshallFloydMatrix(this.graph, this.vertices_count);
	}
	
	@Test
	public void test() {
		long mTime = this.wf.go();
		
		System.out.println("matrixTime: "+(double)mTime/(double)1000);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		assertEquals(18, this.wf.distances[this.graph.hashVertices.get(v1)][this.graph.hashVertices.get(v2)]);
	}

}
