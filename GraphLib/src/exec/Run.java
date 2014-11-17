package exec;


import representation.ListReprGraph;
import representation.MatrixReprGraph;
import algorithms.BellmanFordList;
import algorithms.BellmanFordMatrix;
import algorithms.WarshallFloydList;
import algorithms.WarshallFloydMatrix;
import files.FileGraph;
import graph.Vertex;

public class Run {
	
	static MatrixReprGraph matrix_graph;
	static ListReprGraph list_graph;
	static int vertices_count = 1000;
	static Vertex v1;
	static Vertex v2; 
	static String path = "C:\\Users\\moni\\Documents\\agh\\IXsem\\algo\\duzy_graf.txt";
	static FileGraph fg;

	public static void init(){
		fg = new FileGraph();
		long mstart = System.currentTimeMillis();
		matrix_graph = new MatrixReprGraph(fg.graphRead(path), vertices_count);
		long mend = System.currentTimeMillis() - mstart;
		System.out.println("MatrixFile: "+(double)mend/(double)1000);

		long lstart = System.currentTimeMillis();
		list_graph = new ListReprGraph(fg.graphRead(path), vertices_count);
		long lend = System.currentTimeMillis() - lstart;
		System.out.println("ListFile: "+(double)lend/(double)1000);
		v1 = new Vertex(109);
		v2 = new Vertex(609);
	}
	public static void main(String[] args) {
		init();
		/*
		WarshallFloydMatrix mWF = new WarshallFloydMatrix(matrix_graph, vertices_count);
		long mTimeWF = mWF.go();
		System.out.println("WarshallFloyd matrixTime: "+(double)mTimeWF/(double)1000);
		
		System.out.println("WarshallFloyd matrix distance 109 - 609 "+mWF.distances[matrix_graph.hashVertices.get(v1)][matrix_graph.hashVertices.get(v2)]);
		path(mWF, v1, v2, matrix_graph);
		
		WarshallFloydList lWF = new WarshallFloydList(list_graph, vertices_count);
		long lTimeWF = lWF.go();
		System.out.println("WarshallFloyd listTime: "+(double)lTimeWF/(double)1000);
		
		System.out.println("WarshallFloyd matrix distance 109 - 609 "+lWF.distances[list_graph.getIndex(v1)][list_graph.getIndex(v2)]);
		path(lWF, v1, v2, list_graph);

		System.out.println("WarshallFloyd R = listTime/matrixTime = "+(double)lTimeWF/(double)mTimeWF);
		*/////////////////////////////
		
		
		BellmanFordMatrix mBF= new BellmanFordMatrix(matrix_graph, vertices_count);
		long mTimeBF = mBF.go(v1);
		System.out.println("BellmanFord Matrix Time: "+(double)mTimeBF/(double)1000);
		
		System.out.println("BellmanFord matrix distance 109 - 609 "+ mBF.distance[mBF.graph.hashVertices.get(v2)]);
		path(mBF, v1, v2);
		
		BellmanFordList lBF= new BellmanFordList(list_graph, vertices_count);
		long lTimeBF = lBF.go(v1);
		System.out.println("BellmanFord List Time: "+(double)lTimeBF/(double)1000);
		
		System.out.println("BellmanFord list distance 109 - 609 "+  lBF.distance[lBF.graph.getIndex(v2)]);
		path(lBF, v1, v2);
		
		System.out.println("BellmanFord R = listTime/matrixTime = "+(double)lTimeBF/(double)mTimeBF);
		/**/
	}

	public static void path(WarshallFloydMatrix wf, Vertex source, Vertex dest, MatrixReprGraph graph){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = wf.getPrevious(graph, source, between);
			System.out.print(between.getName()+" ");
		}
		System.out.println();
	}

	public static void path(WarshallFloydList wf, Vertex source, Vertex dest, ListReprGraph graph){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = wf.getPrevious(graph, source, between);
			System.out.print(between.getName()+" ");
		}
		System.out.println();
	}
	public static void path(BellmanFordList bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.getIndex(between)];//wf.getPrevious(source, between);this.previous[graph.getIndex(source)][graph.getIndex(between)];
			System.out.print(between.getName()+" ");
		}
	}
	public static void path(BellmanFordMatrix bf, Vertex source, Vertex dest){
		Vertex between = dest;
		System.out.print(between.getName()+" ");
		while(!between.equals(source)){
			between = bf.previous[bf.graph.hashVertices.get(between)];//wf.getPrevious(source, between);
			System.out.print(between.getName()+" ");
		}
	}

	
}
