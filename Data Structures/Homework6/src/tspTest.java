import java.util.ArrayList;

public class tspTest {

	public static void main(String[] args) {


		tspVertex.userInput();
		
		tspVertex[] vertexArray = tspVertex.generateVertices();
		
		
		
		
		
		tspVertex[] pathArray = tspVertex.pathMaker(vertexArray);
		
	
		
		
		
		
			
//		//printList(nodesList);
//		SalesPlotter plotter = new SalesPlotter();
//		plotter.callSalesman(nodesList, edgeList);
//		
		
	}
	
	
	
	
	public static void printList(ArrayList<tspVertex> list) { 
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).label + "\t");
			System.out.println("xCoord: " + list.get(i).xCoord);
			System.out.println("yCoord" + list.get(i).yCoord);

		}
	}
}