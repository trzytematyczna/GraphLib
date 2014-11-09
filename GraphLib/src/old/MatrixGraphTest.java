package old;

import static org.junit.Assert.*;

import java.util.LinkedList;

import files.FileGraph;
import graph.Edge;
import graph.Vertex;

import org.junit.Before;
import org.junit.Test;

public class MatrixGraphTest {

	private MatrixGraph graph;
	private Vertex v1;
	private Vertex v2;
	private Edge e1;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf.txt";
		this.graph = new MatrixGraph(fg.graphRead(path), fg.graphSize);
//		graph = new MatrixGraph();
//		v1 = new Vertex(1);
//		v2 = new Vertex(2);
//		e1 = new Edge(5);
//		Vertex [] ver = new Vertex[2];
//		ver[0] = v1;		
//		ver[1] = v2;
//		Integer [][] list = {{1, 3, 5},{3, 2, 6}};
//		graph = new MatrixGraph(list, maxList(list));
////		
//		graph.addVertex(v1);
//		graph.addVertex(v2);
//		graph.addEdge(e1, v1, v2);
	}
	
	private Integer maxList(Integer[][] list) {
		int max=0;
		for(int i=0; i<list.length; i++){
			for(int j=0; j<list.length; j++){
				if(list[i][j] > max) max = list[i][j];
			}
			
		}
		return max;
	}

//	@Test
//	public void addVertexTest() {
//		Vertex v3 = new Vertex(3);
//		graph.addVertex(v3);
//		assertEquals(graph.vertexCount(),3);
//	}
//	
//	@Test
//	public void deleteVertexTest() {
//		Vertex v3 = new Vertex();
//		graph.addVertex(v3);
//		assertEquals(graph.deleteVertex(v1),true);
//		assertEquals(graph.vertexCount(),2);
//	}
//	
//	@Test
//	public void areNeighboursTest(){
//		assertEquals(graph.areNeigbours(v1, v2),true);
//		
//	}
//	
//	@Test
//	public void vertexNeigboursTest(){
//		Vertex v1 = new Vertex(1);
//		Vertex v2 = new Vertex(3);
//		LinkedList<Vertex> ver = new LinkedList<Vertex>();
//		ver.add(v2);
//		assertEquals(v2.getValue(),graph.vertexNeighbours(v1).get(0).getValue());
//	}
	
//	@Test
//	public void incidentEdgesTest(){
//		Vertex v2 = new Vertex(3);
//		LinkedList<Edge> ed = new LinkedList<Edge>();
//		ed.add(new Edge(5));
//			assertEquals(ed.get(0).getWeight(), graph.incidentEdges(v2));//.get(0).getWeight());
//	}
	@Test
	public void fileTest(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\graf.txt";
		this.graph = new MatrixGraph(fg.graphRead(path), fg.graphSize);
	}
	
	

}
