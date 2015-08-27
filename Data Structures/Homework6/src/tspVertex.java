import java.util.ArrayList;

public class tspVertex { 

	boolean visited;
	double xCoord;
	double yCoord;
	int scaledX;
	int scaledY;
	int label;
	static final int scaleFactor = 650;

	public tspVertex() {

		this.visited = false; 
		this.xCoord = randGen();
		this.yCoord = randGen();
		this.scaledX = (int) (this.xCoord * scaleFactor);
		this.scaledY = (int) (this.yCoord * scaleFactor);
	}

	/**
	 * Method used to calculate the edge costs between 2 vertices
	 * @param thisVertex
	 * @param adjacentVertex
	 * @return
	 */
	public double calculateLength(tspVertex adjacentVertex) {
		double edgeCost;
		double x1 = this.xCoord;
		double x2 = adjacentVertex.xCoord;
		double y1 = this.yCoord;
		double y2 = adjacentVertex.yCoord;
		edgeCost = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));

		return edgeCost;
	}

	public double randGen() {
		double result = Math.random();
		return result;
	}

	
	
	public static void selectSales() {
		tspVertex[] randV = generateVertices();
		
		tspVertex[] path = pathMaker(randV);
		
		SalesPlotter.callSalesman(path);
	}
	
	
	
	public static tspVertex[] generateVertices() {
		tspVertex[] vertexArray = new tspVertex[7];
		for (int i=0; i< vertexArray.length; i++) {
			vertexArray[i] = new tspVertex();
			vertexArray[i].label = i;
		}
		
		return vertexArray;	
	}

	public static tspVertex[] pathMaker(tspVertex[] vertexArray) {  

		double currentMin = Double.POSITIVE_INFINITY;
		tspVertex[] pathArray = new tspVertex[8];
		
		int cityCount = vertexArray.length;
		for (int source = 0; source < cityCount ; source++) 
			for (int v1 =0; v1 < cityCount; v1++)
				for (int v2 = 0; v2 < cityCount; v2++)
					for (int v3 = 0; v3 < cityCount; v3++)
						for (int v4 = 0; v4 < cityCount; v4++)
							for (int v5 = 0; v5 < cityCount; v5++)
								for (int v6 = 0; v6 < cityCount; v6++) {
		double distance = pathChecker(vertexArray[source], vertexArray[v1], 
						vertexArray[v2], vertexArray[v3], vertexArray[v4],
						vertexArray[v5], vertexArray[v6]);
		if (distance < currentMin) {
			currentMin = distance;
			pathArray[0] = vertexArray[source];
			pathArray[1] = vertexArray[v1];
			pathArray[2] = vertexArray[v2];
			pathArray[3] = vertexArray[v3];
			pathArray[4] = vertexArray[v4];
			pathArray[5] = vertexArray[v5];
			pathArray[6] = vertexArray[v6];
			pathArray[7] = vertexArray[source];
			}
		}
		System.out.println(currentMin);
		return pathArray;
	}


	private static double pathChecker(tspVertex source, tspVertex v1, tspVertex v2, 
			tspVertex v3, tspVertex v4, tspVertex v5, tspVertex v6) {
	
		if(source.label == v1.label || source.label == v2.label || source.label 
				== v3.label || source.label == v4.label || source.label
				== v5.label || source.label == v6.label || v1.label == v2.label
				|| v1.label == v3.label || v1.label == v4.label || v1.label 
				== v5.label || v1.label == v6.label || v2.label == v3.label || 
				v2.label == v4.label || v2.label == v5.label || v2.label 
				== v6.label || v3.label == v4.label ||v3.label == v5.label || 
				v3.label == v6.label ||v4.label == v5.label || v4.label == 
				v6.label || v5.label == v6.label) {
			return Double.POSITIVE_INFINITY;
		}
		else {
			return source.calculateLength(v1) + v1.calculateLength(v2) 
					+ v2.calculateLength(v3) + v3.calculateLength(v4) 
					+ v4.calculateLength(v5) + v5.calculateLength(v6) +
					v6.calculateLength(source);		
		}	
	}



	public void nN() {
		System.out.print("NN");
	}

	
	public static void userInput() {
		ResponseGui b = new ResponseGui();	
	}
}