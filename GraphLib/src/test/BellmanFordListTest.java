package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import representation.ListReprGraph;
import representation.MatrixReprGraph;
import algorithms.BellmanFordList;
import algorithms.BellmanFordMatrix;
import files.FileGraph;
import graph.Vertex;

public class BellmanFordListTest {
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
			this.list_graph = new ListReprGraph(fg.graphRead(path), vertices_count);
			long mend = System.currentTimeMillis() - mstart;
			System.out.println("ListFile: "+(double)mend/(double)1000);			

		}
	@Test
	public void BFListTest(){
		BellmanFordList bf= new BellmanFordList(list_graph, vertices_count);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		long mstart = System.currentTimeMillis();
		bf.go(v1);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("BellmanFord List Time: "+(double)mend/(double)1000);
		
		assertEquals(18, bf.distance[bf.graph.getIndex(v2)]);
		vertexListList(bf, v1, v2);
	}
	
	
	public void vertexListList(BellmanFordList bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.getIndex(between)];//wf.getPrevious(source, between);
			System.out.print(between.getName()+" ");
		}
	}

	public void printDistanceFromVertex(BellmanFordList bf, Vertex source, Vertex dest){
		System.out.println("From vertex "+source.getName());
		System.out.println("to vertex "+dest.getName()+" distance: "+bf.distance[bf.graph.getIndex(dest)]);
	}

//	//print all methods
//	public void printPrevious(BellmanFordList bf){
//		for(int i = 0; i< bf.previous.length; i++){
//			if(bf.previous[i] == null) System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i]);//.getName());
//			else System.out.print("Vertex "+bf.graph.getVertexFromValue(i).getName()+"<--"+ bf.previous[i].getName());
//		}
//	}
//	public void allDistancesFromVertex(Vertex source, BellmanFordList bf){
//		System.out.println("From vertex "+source.getName());
//		for(int i = 0; i<bf.distance.length; i++){
//			System.out.println("to vertex "+bf.graph.getVertexFromValue(i).getName()+" distance: "+bf.distance[i]);
//		}
//	}

}
