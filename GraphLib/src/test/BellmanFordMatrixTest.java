package test;

import org.junit.Before;
import org.junit.Test;

import algorithms.BellmanFord;
import representation.ListReprGraph;
import representation.MatrixReprGraph;
import files.FileGraph;
import graph.Vertex;

public class BellmanFordMatrixTest {
	MatrixReprGraph matrix_graph;
	ListReprGraph list_graph;
	int vertices_count = 1000;
//	int vertices_count = 13;

	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
//	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_BF.txt";
	FileGraph fg;

	@Before
		public void init(){
			fg = new FileGraph();
			long mstart = System.currentTimeMillis();
//			this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
			this.matrix_graph = new MatrixReprGraph(fg.graphRead(path));
			long mend = System.currentTimeMillis() - mstart;
			System.out.println("MatrixFile: "+(double)mend/(double)1000);			

		}
	@Test
	public void BFMatrixTest(){
		BellmanFord bf= new BellmanFord(matrix_graph, vertices_count);
		Vertex v = new Vertex(109);
		long mstart = System.currentTimeMillis();
		bf.go(v);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);			
		distancesFromVertex(v, bf);
		int i=0;
//		assertEquals()
	}
	
	public void distancesFromVertex(Vertex source, BellmanFord bf){
		System.out.println("From vertex "+source.getName());
		for(int i = 0; i<bf.distance.length; i++){
			System.out.println("to vertex "+bf.graph.getVertexFromValue(i).getName()+" distance: "+bf.distance[i]);
		}
	}
}
