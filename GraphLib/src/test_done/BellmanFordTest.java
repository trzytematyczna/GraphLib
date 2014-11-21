package test_done;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListReprGraph;
import representation.MatrixReprGraph;
import algorithms_done.BellmanFordList;
import algorithms_done.BellmanFordMatrix;
import files.FileGraph;
import graph.Vertex;

public class BellmanFordTest {
	MatrixReprGraph matrix_graph;
	int vertices_count = 1000;
//	int vertices_count = 13;
	ListReprGraph list_graph;
	
	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//	String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
	FileGraph fg;

	@Before
		public void init(){
			fg = new FileGraph();
			
					

		}
	@Test
	public void BFMatrixTest(){
		
		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
//		System.out.println("MatrixFile: "+(double)mend/(double)1000);	
		
		BellmanFordMatrix mbf= new BellmanFordMatrix(matrix_graph, vertices_count);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
//		long mTimeBF = mbf.go_edges(v1);
		long mTimeBF = mbf.go(v1);
		System.out.println("BellmanFord Matrix Time: "+(double)mTimeBF/(double)1000);
		assertEquals(18, mbf.distance[mbf.graph.hashVertices.get(v2)]);

		printDistanceFromVertex(mbf, v1, v2);
//		assertEquals(18, mbf.distance[109]);
		vertexMatrixList(mbf, v1, v2);
		System.out.println("========================");
		allDistancesFromVertex(v1, mbf);
		
		System.out.println("========================");
		
		mstart = System.currentTimeMillis();
		this.list_graph = new ListReprGraph(fg.graphRead(path), vertices_count);
		mend = System.currentTimeMillis() - mstart;
//		System.out.println("ListFile: "+(double)mend/(double)1000);			
		BellmanFordList lbf= new BellmanFordList(list_graph, vertices_count);
		long lTimeBF = lbf.go(v1);
		System.out.println("BellmanFord List Time: "+(double)lTimeBF/(double)1000);
		assertEquals(18, lbf.distance[lbf.graph.getIndex(v2)]);
		
		printDistanceFromVertex(lbf, v1, v2);
		vertexListList(lbf, v1, v2);
		System.out.println("========================");
		allDistancesFromVertex(v1, lbf);
		
		System.out.println("========================");
		
		System.out.println("BellmanFord R = listTime/matrixTime = "+(double)lTimeBF/(double)mTimeBF);

	}
	
	
	public void vertexListList(BellmanFordList bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.getIndex(between)];//wf.getPrevious(source, between);
			System.out.print(between.getName()+" ");
		}
		System.out.println();
	}

	public void printDistanceFromVertex(BellmanFordList bf, Vertex source, Vertex dest){
		System.out.print("From vertex "+source.getName());
		System.out.println(" to vertex "+dest.getName()+" distance: "+bf.distance[bf.graph.getIndex(dest)]);
	}

//	//print all methods
//	public void printPrevious(BellmanFordList bf){
//		for(int i = 0; i< bf.previous.length; i++){
//			if(bf.previous[i] == null) System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i]);//.getName());
//			else System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i].getName());
//		}
//	}
	public void allDistancesFromVertex(Vertex source, BellmanFordList bf){
		System.out.println("From vertex "+source.getName());
		int unavailable = 0;
		for(int i = 0; i<bf.distance.length; i++){
			if(bf.distance[i] == Integer.MAX_VALUE) unavailable++;
			System.out.print("to vertex "+bf.graph.vertices[i].getVertex().getName()+" distance: "+bf.distance[i]+"\t");
		}
		System.out.println();
		System.out.println("unavailable: "+unavailable);
	}
	
	public void vertexMatrixList(BellmanFordMatrix bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.hashVertices.get(between)];//wf.getPrevious(source, between);
			System.out.print(between.getName()+" ");
		}
		System.out.println();
	}

	public void printDistanceFromVertex(BellmanFordMatrix bf, Vertex source, Vertex dest){
		System.out.print("From vertex "+source.getName());
		System.out.println(" to vertex "+dest.getName()+" distance: "+bf.distance[bf.graph.hashVertices.get(dest)]);
	}

//	//print all methods
//	public void printPrevious(BellmanFordMatrix bf){
//		for(int i = 0; i< bf.previous.length; i++){
//			if(bf.previous[i] == null) System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i]);//.getName());
//			else System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i].getName());
//		}
//	}
	public void allDistancesFromVertex(Vertex source, BellmanFordMatrix bf){
		System.out.println("From vertex "+source.getName());
		int unavailable = 0;
		for(int i = 0; i<bf.distance.length; i++){
			if(bf.distance[i] == Integer.MAX_VALUE) unavailable++;
			System.out.print("to vertex "+bf.graph.getVertexFromValue(i).getName()+" distance: "+bf.distance[i]+"\t");
		}
		System.out.println();
		System.out.println("unavailable: "+unavailable);
	}
}
