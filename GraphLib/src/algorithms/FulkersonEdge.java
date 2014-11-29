package algorithms;

public class FulkersonEdge {
		int weight;
		int flow;
		int begin;
		int end;
		
		public FulkersonEdge(int wei, int initFlow, int beg, int end) {
			this.weight = wei;
			this.flow = initFlow;
			this.begin = beg;
			this.end = end;
		}
		
}