package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import algorithms.BellmanFordMatrix;
import representation.MatrixReprGraph;
import files.FileGraph;
import graph.Vertex;

public class BellmanFordMatrixTest {
	MatrixReprGraph matrix_graph;
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
			this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
			long mend = System.currentTimeMillis() - mstart;
			System.out.println("MatrixFile: "+(double)mend/(double)1000);			

		}
	@Test
	public void BFMatrixTest(){
		BellmanFordMatrix bf= new BellmanFordMatrix(matrix_graph, vertices_count);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
//		long mstart = System.currentTimeMillis();
		long mend = bf.go(v1);
//		long mend = System.currentTimeMillis() - mstart;
		System.out.println("BellmanFord Matrix Time: "+(double)mend/(double)1000);
		
		assertEquals(18, bf.distance[bf.graph.hashVertices.get(v2)]);
		vertexMatrixList(bf, v1, v2);
		allDistancesFromVertex(v1, bf);
	}
	
	
	public void vertexMatrixList(BellmanFordMatrix bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.hashVertices.get(between)];//wf.getPrevious(source, between);
			System.out.print(between.getName()+" ");
		}
	}

	public void printDistanceFromVertex(BellmanFordMatrix bf, Vertex source, Vertex dest){
		System.out.println("From vertex "+source.getName());
		System.out.println("to vertex "+dest.getName()+" distance: "+bf.distance[bf.graph.hashVertices.get(dest)]);
	}

	//print all methods
	public void printPrevious(BellmanFordMatrix bf){
		for(int i = 0; i< bf.previous.length; i++){
			if(bf.previous[i] == null) System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i]);//.getName());
			else System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i].getName());
		}
	}
	public void allDistancesFromVertex(Vertex source, BellmanFordMatrix bf){
		System.out.println("From vertex "+source.getName());
		int unavailable = 0;
		for(int i = 0; i<bf.distance.length; i++){
			if(bf.distance[i] == Integer.MAX_VALUE) unavailable++;
			System.out.println("to vertex "+bf.graph.getVertexFromValue(i).getName()+" distance: "+bf.distance[i]);
		}
		System.out.println("unavailable: "+unavailable);
	}

}
