package test;

import static org.junit.Assert.*;
import files.FileGraph;
import graph.Vertex;
import old.FordFulkersonMatrix;
import old_fordfulkerson.FordFulkersonMatrix2;
import old_fordfulkerson.FordFulkersonMatrix4;

import org.junit.Before;
import org.junit.Test;

import representation.MatrixReprGraph;
import algorithms.FordFulkersonMatrix3;

public class FordFulkersonMatrixTest {

	FordFulkersonMatrix3 ffm;
	int vertices_count = 1000;
	//int vertices_count = 20;
	//int vertices_count = 13;
	//int vertices_count = 5;
	//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy_duzy.txt";
	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
	//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
	//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_dfs.txt";
	private MatrixReprGraph matrix_graph;
	private FileGraph fg;

	@Before
	public void init(){
		fg = new FileGraph();
		
		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
		this.ffm = new FordFulkersonMatrix3(this.matrix_graph, new Vertex(109), new Vertex(609));
//		this.ffm = new FordFulkersonMatrix3(this.matrix_graph, new Vertex(9), new Vertex(3));
	}
	@Test
	public void test() {
		long mstart = System.currentTimeMillis();
		int i = ffm.go();
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("wynik: "+i);
		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
	}

}
