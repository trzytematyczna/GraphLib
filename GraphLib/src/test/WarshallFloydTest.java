package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.WarshallFloydList;
import algorithms.WarshallFloydMatrix;
import representation.ListReprGraph;
import representation.MatrixReprGraph;
import files.FileGraph;
import graph.Vertex;

public class WarshallFloydTest {
MatrixReprGraph matrix_graph;
ListReprGraph list_graph;
int vertices_count = 1000;
//int vertices_count = 13;

String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
//String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf_testowy.txt";
FileGraph fg;

@Before
	public void init(){
		fg = new FileGraph();
	}
	
	@Test
	public void test() {
		long mstart = System.currentTimeMillis();
		this.matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);
		
		WarshallFloydMatrix mWF = new WarshallFloydMatrix(vertices_count);
		long mTime = mWF.go(matrix_graph);
		System.out.println("matrixTime: "+(double)mTime/(double)1000);
		
		long lstart = System.currentTimeMillis();
		this.list_graph = new ListReprGraph(fg.graphRead(path), vertices_count);
		long lend = System.currentTimeMillis() - lstart;
		System.out.println("ListFile: "+(double)lend/(double)1000);
		
		WarshallFloydList lWF = new WarshallFloydList(vertices_count);
		long lTime = lWF.go(list_graph);
		System.out.println("listTime: "+(double)lTime/(double)1000);
		
		System.out.println("R = listTime/matrixTime = "+(double)lTime/(double)mTime);

		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
		assertEquals(18, mWF.distances[matrix_graph.hashVertices.get(v1)][matrix_graph.hashVertices.get(v2)]);
//		assertEquals(500, mWF.distances[matrix_graph.hashVertices.get(v1)][matrix_graph.hashVertices.get(v2)]);
		vertexMatrixList(mWF, v1, v2, matrix_graph);
		System.out.println();
		assertEquals(18, lWF.distances[list_graph.getIndex(v1)][list_graph.getIndex(v2)]);
//		assertEquals(500, lWF.distances[list_graph.getIndex(v1)][list_graph.getIndex(v2)]);
		vertexListList(lWF, v1, v2, list_graph);
	}

	public void vertexMatrixList(WarshallFloydMatrix wf, Vertex source, Vertex dest, MatrixReprGraph graph){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = wf.getPrevious(graph, source, between);
			System.out.print(between.getName()+" ");
		}
	}

	public void vertexListList(WarshallFloydList wf, Vertex source, Vertex dest, ListReprGraph graph){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = wf.getPrevious(graph, source, between);
			System.out.print(between.getName()+" ");
		}
	}
	
}
