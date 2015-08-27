
import java.util.ArrayList;
import java.util.Scanner;


///////////////////////////////////////////////////////////////////////
//////////////////////////////Vertex Object////////////////////////////
///////////////////////////////////////////////////////////////////////

public class SpanVertex implements Comparable {
	static ArrayList<SpanVertex>	spanVertexList;
	
	String 				name;
	boolean 			isKnown;
	boolean 			onPath;
	boolean 			linked;
	double 				distance;
	SpanVertex 			path; 	//previous vertex in path
	int 				xCoord;
	int 				yCoord;
	int					vertexNum;


	public SpanVertex(String name, int vertexNum){

		isKnown 		= false;
		onPath			= false;
		distance 		= Double.POSITIVE_INFINITY;
		path 			= null;
		this.name 		= name;
		this.vertexNum	= vertexNum;	//identifies the vertex by number
		xCoord			= 0;
		yCoord			= 0;
		
	}

	public int compareTo(Object arg) {
		SpanVertex vertex = (SpanVertex) arg;
		if (distance < vertex.distance) {
			return -1;
		}
		else if (distance > vertex.distance) {
			return 1;
		}
		else return 0;
	}

	/**
	 * Parses through the 2nd command line argument (cityXY.txt) to update
	 * the XY coordinates of each city 
	 * @param cityXY
	 * @return
	 */
	public static ArrayList<SpanVertex> xyUpdater(Scanner cityXY) {
		spanVertexList = new ArrayList<SpanVertex>();
		SpanVertex thisVertex = null;
		int vertexNum = 0; 

		while(cityXY.hasNext()) {
			//goes through three elements at a time 
			//because the cityXY file has formatting problems 
			String cityName = cityXY.next();
			int xCoord = cityXY.nextInt();
			int yCoord = cityXY.nextInt();
			
			vertexNum++;	//numbers the vertex for DisjSets
			thisVertex = new SpanVertex(cityName, vertexNum);
			thisVertex.xCoord = xCoord;
			thisVertex.yCoord = yCoord;
			spanVertexList.add(thisVertex);
		}
		//printVertList(spanVertexList);
		return spanVertexList;
	}

	
	/**
	 * Method to make a list of each of the edges
	 * @param vListXY
	 * @return
	 */
	public static ArrayList<SpanEdge> edgeLister(ArrayList<SpanVertex> vListXY) {

		ArrayList<SpanEdge>	spanEdgeList = new ArrayList<SpanEdge>();
		SpanEdge toAdd = null;
		for(SpanVertex thisVertex : vListXY) {
			thisVertex.linked = true;
			for(SpanVertex adjacentVertex : vListXY) { 
				if(!thisVertex.name.equals(adjacentVertex.name) && !adjacentVertex.linked) {
					toAdd = new SpanEdge(thisVertex, adjacentVertex);
					spanEdgeList.add(toAdd);
				}
			}
		}
		//printSpanList(spanEdgeList);
		return spanEdgeList;
	}

	public static void printVertList(ArrayList<SpanVertex> list) { 
		System.out.println("Size: " + list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).name + "\t");
			System.out.println("xCoord: " + list.get(i).xCoord 
								+ "\tyCoord: " + list.get(i).yCoord);
			System.out.println("Vertex Number: " + list.get(i).vertexNum);
		}
	}

	public static void printSpanList(ArrayList<SpanEdge> spanEdgeList) {
		System.out.println("Size: " + spanEdgeList.size());
		for(int i = 0; i < spanEdgeList.size(); i++) {
			System.out.print("Source: " +spanEdgeList.get(i).sourceVertex.name);
			System.out.print(" End: " +spanEdgeList.get(i).endVertex.name);
			System.out.println(" Cost: " +spanEdgeList.get(i).edgeLength + "\n");
			
		}
	}
}
