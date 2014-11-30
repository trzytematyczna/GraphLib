package test;

import files.FileGraph;
import graph.Vertex;

import org.junit.Before;
import org.junit.Test;

import representation.ListReprGraph;
import representation.MatrixReprGraph;
import algorithms.FordFulkersonList;
import algorithms.FordFulkersonMatrix3;

public class FordFulkersonTest {
	
	FordFulkersonMatrix3 ffm_matrix;
	FordFulkersonList ffm_list;
	int vertices_count = 1000;
	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
	private MatrixReprGraph matrix_graph;
	private FileGraph fg;
	private ListReprGraph graph;

	@Before
	public void init(){
		fg = new FileGraph();
		
		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
		this.ffm_matrix = new FordFulkersonMatrix3(this.matrix_graph, new Vertex(109), new Vertex(609));

		mstart = System.currentTimeMillis();
		this.graph = new ListReprGraph(fg.graphRead(path), vertices_count);
		mend = System.currentTimeMillis() - mstart;
		System.out.println("ListFile: "+(double)mend/(double)1000);	
		this.ffm_list = new FordFulkersonList(this.graph, new Vertex(109), new Vertex(609));
		}
	
	@Test
	public void test() {
		long mstart = System.currentTimeMillis();
		int i = ffm_matrix.go();
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("wynik matrix: "+i);
		double matrixtime = (double)mend/(double)1000;
		System.out.println("Matrix: "+matrixtime);	
		
		
		mstart = System.currentTimeMillis();
		i = ffm_list.go();
		mend = System.currentTimeMillis() - mstart;
		System.out.println("wynik list: "+i);
		double listtime = (double)mend/(double)1000;		
		System.out.println("List: "+listtime);	
		
		System.out.println("R = ListTime/MatrixTime: "+listtime/matrixtime);	
		
	}
	
	
}
