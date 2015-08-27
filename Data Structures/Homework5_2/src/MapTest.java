
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapTest { 

public static void main(String[] args) {
		boolean again=true;
		while(again){
			try{
				//file encoding
				File cityPair = new File(args[0]);	//command line argument
				File cityXY = new File(args[1]);
				
				Scanner cityPairs = new Scanner(cityPair);
				Scanner citiesXY = new Scanner(cityXY);
				
				ArrayList<Vertex> vList = Vertex.vertexList(cityPairs);
				ArrayList<Vertex> vListXY = Vertex.xyUpdater(citiesXY, vList);
				
				String origin = DropDownGui.originPrompt(vListXY);
				String destination = DropDownGui.destinationPrompt(vListXY);
				
				Dijkstra dGraph = new Dijkstra(vListXY, origin, destination);
				
				dGraph.dijkstra();
				dGraph.pathFinder();

				again = false;
			}
			catch(IOException e ){
				System.out.println("Please enter a correct input file name:");
				Scanner userChoice = new Scanner(System.in);
				int pick = userChoice.nextInt();
				//Encoder fileEncoderChoice = new Encoder();
				main(args);
				again = false;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter the file name in "
						+ "the command line!");
				again=false;
			}
			catch(NullPointerException e) {
				System.out.println("You did not pick a destination and/or"
						+ " a source.");
				
			}
		}
	}
}