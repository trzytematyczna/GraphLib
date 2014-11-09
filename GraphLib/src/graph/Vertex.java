package graph;

public class Vertex {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
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
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		if (name != other.name) {
			return false;
		}
		return true;
	}

	int name; 
	
	public Vertex(int i) {
		this.name = i;
	}
	public Vertex() {
		// TODO Auto-generated constructor stub
	}
	
//	public boolean isEqual(Vertex vertex){
//		if(this.name == vertex.name){
//			return true;
//		}
//		return false;
//	}
	
	public int getName(){
		return this.name;
	}
	
//	@Override
//	public boolean equals(Object obj) {		
//		if (obj instanceof Vertex){
//			Vertex v = (Vertex) obj;
//			return this.getName() == v.getName();
//		}
//		return false;
//	}

}
