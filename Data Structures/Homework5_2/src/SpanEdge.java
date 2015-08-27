

public class SpanEdge implements Comparable {
	
	SpanVertex sourceVertex;
	SpanVertex endVertex;
	double edgeLength;
	
	public SpanEdge(SpanVertex thisVertex, SpanVertex adjacentVertex) {
		this.sourceVertex = thisVertex;
		this.endVertex = adjacentVertex;
		this.edgeLength = calculateLength(thisVertex, adjacentVertex);
	}

	public int compareTo(Object arg) {
		SpanEdge otherEdge = (SpanEdge) arg;
		if(this.edgeLength < otherEdge.edgeLength) {
			return -1;
		}
		else if(this.edgeLength > otherEdge.edgeLength) { 
			return 1; 
		}
		else return 0;
	}
	
	/**
	 * Method used to calculate the edge costs between 2 vertices
	 * @param thisVertex
	 * @param adjacentVertex
	 * @return
	 */
	private double calculateLength(SpanVertex thisVertex,
										SpanVertex adjacentVertex) {
		double edgeCost;
		int x1 = thisVertex.xCoord;
		int x2 = adjacentVertex.xCoord;
		int y1 = thisVertex.yCoord;
		int y2 = adjacentVertex.yCoord;
		edgeCost = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
		
		return edgeCost;
	}
	
	

}
