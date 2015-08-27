
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpanTest { 

public static void main(String[] args) {
		boolean again=true;
		while(again){
			try{
				File cityXY = new File(args[0]);
				Scanner citiesXY = new Scanner(cityXY);
				
				ArrayList<SpanVertex> vListXY = SpanVertex.xyUpdater(citiesXY);
				ArrayList<SpanEdge> edgeList = SpanVertex.edgeLister(vListXY);
				int numVertices = vListXY.size();
				
				ArrayList<SpanEdge> minSpanTreeList = 
										Kruskal.kruskal(edgeList, numVertices);
				
				DPlotter.drawMST(minSpanTreeList, vListXY);

				again = false;
			}
			catch(IOException e ){
				System.out.println("Please enter a correct input value:");
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
		}
	}
}