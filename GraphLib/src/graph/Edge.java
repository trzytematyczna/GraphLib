package graph;

public class Edge {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + weight;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge other = (Edge) obj;
		if (weight != other.weight) {
			return false;
		}
		return true;
	}

	int weight;
	
	public Edge(int weight){
		this.weight = weight;
	}
	
	public Edge() {
		// TODO Auto-generated constructor stub
	}

	public int getWeight(){
		return this.weight;
	}
	
	public void setWeight(int newWeight){
		this.weight = newWeight;
	}

	public boolean isEqual(Edge e) {
		if (this.weight == e.getWeight()) return true;
		return false;
	}
}
