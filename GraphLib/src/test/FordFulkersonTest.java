package test;

import files.FileGraph;
import graph.Edge;
import graph.Vertex;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import representation.ListReprGraph;
import representation.MatrixReprGraph;
import algorithms.FordFulkersonList;
import algorithms.FordFulkersonMatrix3;

public class FordFulkersonTest {
	
	FordFulkersonMatrix3 ffm_matrix;
	FordFulkersonList ffm_list;
	int vertices_count = 1000;
//	int vertices_count = 13;
	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
	private MatrixReprGraph matrix_graph;
	private FileGraph fg;
	private ListReprGraph graph;

	@Before
	public void init(){
		fg = new FileGraph();
		
//		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
//		long mend = System.currentTimeMillis() - mstart;
//		System.out.println("Test MatrixFile: "+(double)mend/(double)1000);	
		this.ffm_matrix = new FordFulkersonMatrix3(this.matrix_graph, new Vertex(109), new Vertex(609));

//		mstart = System.currentTimeMillis();
		this.graph = new ListReprGraph(fg.graphRead(path), vertices_count);
//		mend = System.currentTimeMillis() - mstart;
//		System.out.println("TEst ListFile: "+(double)mend/(double)1000);	
		this.ffm_list = new FordFulkersonList(this.graph, new Vertex(109), new Vertex(609));
		}
	
	@Test
	public void test() {
		
		System.out.println("### Matrix ###");
		long mstart1 = System.currentTimeMillis();
		System.out.println("Matrix Upper: "+ bound(new Vertex(109), matrix_graph, false));
		System.out.println("Matrix Lower: "+ bound(new Vertex(609), matrix_graph, true));
		int i = ffm_matrix.go();
		long mend1 = System.currentTimeMillis() - mstart1;
		double matrixtime = (double)mend1/(double)60000;
		System.out.println("Matrix: "+matrixtime);	
		System.out.println("wynik matrix: "+i);
		
		System.out.println("### List ###");
		long mstart = System.currentTimeMillis();
		System.out.println("List Upper: "+ bound(new Vertex(109), graph, false));
		System.out.println("List Lower: "+ bound(new Vertex(609), graph, true));
		int ii = this.ffm_list.go();
		long mend = System.currentTimeMillis() - mstart;
		double listtime = (double)mend/(double)60000;		
		System.out.println("List: "+listtime);	
		System.out.println("wynik list: "+ii);
		
		System.out.println("### Time sum up ###");
		System.out.println("R = ListTime/MatrixTime: "+listtime/matrixtime);	
//		
	}
	
	public int bound(Vertex ver, MatrixReprGraph mat, boolean isIn){
		int result = 0;
		if(isIn){
			for ( Edge ed : mat.incidentEdgesIN(ver)){
				result+=ed.getWeight();
			}
			return result;
		}
		else{
			for ( Edge ed : mat.incidentEdgesOUT(ver)){
				result+=ed.getWeight();
			}
			return result;
		}
	}
	public int bound(Vertex ver, ListReprGraph list, boolean isIn){
		int result = 0;
		if(isIn){
			for ( Edge ed : list.incidentEdgesIN(ver)){
				result+=ed.getWeight();
			}
			return result;
		}
		else{
			for ( Edge ed : list.incidentEdges(ver)){
				result+=ed.getWeight();
			}
			return result;			
		}
	}
}
