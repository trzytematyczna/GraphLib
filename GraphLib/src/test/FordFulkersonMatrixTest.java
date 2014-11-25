package test;

import static org.junit.Assert.*;
import files.FileGraph;
import graph.Vertex;

import org.junit.Before;
import org.junit.Test;

import representation.MatrixReprGraph;
import algorithms.FordFulkersonMatrix;

public class FordFulkersonMatrixTest {

FordFulkersonMatrix ffm;
//int vertices_count = 1000;
//int vertices_count = 13;
int vertices_count = 5;
//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_dfs.txt";
private MatrixReprGraph matrix_graph;
private FileGraph fg;

	@Before
	public void init(){
		fg = new FileGraph();
		
		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
		this.ffm = new FordFulkersonMatrix(this.matrix_graph);
	}
	@Test
	public void test() {
		ffm.findPath(new Vertex(109), new Vertex(609));
	
	}

}
