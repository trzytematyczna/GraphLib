package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListReprGraph;
import algorithms.WarshallFloydList;
import files.FileGraph;
import graph.Vertex;

public class WarshallFloydListTest {

	ListReprGraph graph;
	WarshallFloydList wf;
	int vertices_count=1000;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf_3.txt";
//		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";

		long mstart = System.currentTimeMillis();
		this.graph = new ListReprGraph(fg.graphRead(path), this.vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);
		
		this.wf = new WarshallFloydList(this.graph, this.vertices_count);
	}
	
	@Test
	public void test() {
		
		long lTime = wf.go();
		System.out.println("listTime: "+(double)lTime/(double)1000);
		
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		
		assertEquals(18, this.wf.distances[this.graph.getIndex(v1)][this.graph.getIndex(v2)]);
//		assertEquals(55, wf.distances[graph.getIndex(v1)][graph.getIndex(v2)]);
	}

}
