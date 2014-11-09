package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.WarshalFloyd;
import representation.MGraph;
import representation.MGraph2;
import files.FileGraph;
import graph.Vertex;

public class WarshalFloydRest {
	MGraph2 graph;
	
	@Before
	public void init(){
		FileGraph fg = new FileGraph();
		String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf_3.txt";
		this.graph = new MGraph2(fg.graphRead(path));
	}
	
	@Test
	public void test() {
		WarshalFloyd wf = new WarshalFloyd(graph.matrix.length);
		wf.go(graph);
		Vertex v1 = new Vertex(109);
		Vertex v2 = new Vertex(609);
//		int index1 = graph.getVertexValue(v1.getName());
//		int index2 = graph.getVertexValue(v2.getName());
		assertEquals(18, wf.distances[graph.hashVertices.get(v1)][graph.hashVertices.get(v2)]);
	}

}
