package representation;

import graph.Edge;
import graph.Vertex;

import java.util.LinkedList;

public interface Graph {

	
	public boolean addVertex(Vertex vertex);
	public boolean deleteVertex(Vertex vertex);
	public boolean addEdge(Edge edge, Vertex v1, Vertex v2);
	public boolean deleteEdge(Edge edge);
	public LinkedList<Vertex> vertexNeighbours(Vertex vertex);
	public LinkedList<Edge>incidentEdges(Vertex vertex);
	public int vertexCount();
	public int edgeCount();
	public boolean areNeigbours(Vertex v1, Vertex v2);
	
	
}
