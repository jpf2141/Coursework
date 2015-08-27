
import java.util.ArrayList;
import java.util.Scanner;


///////////////////////////////////////////////////////////////////////
//////////////////////////////Vertex Object////////////////////////////
///////////////////////////////////////////////////////////////////////


public class Vertex implements Comparable {
	static ArrayList<Vertex>	vertexList;


	ArrayList<Vertex> 	adjacencyList;
	ArrayList<Double> 	adjacencyCost; 	//costs w/ same indices as adjacencyList
	//int 				adjacencySize;	//number of vertices this edge goes to 
	String 				name;
	boolean 			isKnown;
	boolean 			onPath;
	double 				distance;
	Vertex 				path; 	//previous vertex in path
	int 				xCoord;
	int 				yCoord;


	public Vertex(String name){
		adjacencyList 	= new ArrayList<Vertex>();
		adjacencyCost 	= new ArrayList<Double>();
		isKnown 		= false;
		onPath			= false;
		distance 		= Double.POSITIVE_INFINITY;
		path 			= null;
		this.name 		= name;
		xCoord			= 0;
		yCoord			= 0; 
		
	}

	public int compareTo(Object arg) {
		Vertex vertex = (Vertex) arg;
		if (distance < vertex.distance) {
			return -1;
		}
		else if (distance > vertex.distance) {
			return 1;
		}
		else return 0;
	}


	/**
	 * Creates a list of vertices using the 1st command line argument 
	 * cityNames.txt
	 * @param cityPairs
	 * @return vertexList
	 */
	public static ArrayList<Vertex> vertexList(Scanner cityPairs) {
		vertexList = new ArrayList<Vertex>();
		Vertex vertex1 = null;
		Vertex vertex2 = null;
		
		while(cityPairs.hasNextLine()) { 

			String currentLine = cityPairs.nextLine();
			String[] currentArray = currentLine.split(" ");

			String vertexName 	= currentArray[0];
			String linkedToName = currentArray[1];
			double dist 	= Double.parseDouble(currentArray[2]);	
			boolean vertexNameFound = false;
			boolean linkedToNameFound = false;

			//checks to see if the first city is already in the list
			for (Vertex currentVertex: vertexList) {
				if (currentVertex.name.equals(vertexName)){
					vertexNameFound = true;
					vertex1 = currentVertex;
				}
			}
			if (!vertexNameFound) {
				vertex1 = new Vertex(vertexName);
				vertexList.add(vertex1);
			}

			//checks to see if the second city is already in the list
			for (Vertex currentVertex2: vertexList)
				if (currentVertex2.name.equals(linkedToName)){
					linkedToNameFound = true;
					vertex2 = currentVertex2;
				}
			if (!linkedToNameFound){
				vertex2 = new Vertex(linkedToName);
				vertexList.add(vertex2);
			}
			vertex1.makeAdjacent(vertex2, dist);	//makes the vertices adjacent 
		}
		return vertexList;
	}
	
	/**
	 * Parses through the 2nd command line argument (cityXY.txt) to update
	 * the XY coordinates of each city 
	 * @param cityXY
	 * @param vList
	 * @return
	 */
	public static ArrayList<Vertex> xyUpdater(Scanner cityXY, ArrayList<Vertex> vList) {
		while(cityXY.hasNext()) {
 
			//goes through three elements at a time 
			//because the cityXY file has formatting problems 
			String cityName = cityXY.next();
			int xCoord = cityXY.nextInt();
			int yCoord = cityXY.nextInt();


			for (Vertex currentVertex: vertexList) {
				if (currentVertex.name.equals(cityName)) {
					currentVertex.xCoord = xCoord;
					currentVertex.yCoord = yCoord;
				}
			}	
		}
		//printList(vertexList);	//TEST PRINT
		return vertexList;
	}
	
	public void makeAdjacent(Vertex v, double dist) { //adds v to this's adj list and this to v's adj list
		this.adjacencyList.add(v);
		this.adjacencyCost.add(dist);

		v.adjacencyList.add(this);
		v.adjacencyCost.add(dist);
	}

	public static void printList(ArrayList<Vertex> list) { 
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).name + "\t");
			System.out.println("xCoord: " + list.get(i).xCoord);
			System.out.println("Source: " + list.get(i).adjacencyList.size());
			
		}
	}
}
