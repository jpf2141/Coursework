
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class DPlotter extends JApplet {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height =(int) screenSize.getHeight();
	static final int xDimension = width;
	static final int yDimension = height;
	static final int scaleFactor = 2;

	static ArrayList<Vertex> cities;
	static ArrayList<SpanEdge> cityEdges;
	static ArrayList<SpanVertex> spanCities; 
	static String source;
	static Vertex sourceVertex;
	static String end;
	static Vertex endVertex;
	static boolean doDijkstra;


	public DPlotter() {
	}

	public void init() {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.white);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(doDijkstra) { 
			paintDijkstra(g2);
		}
		else if(!doDijkstra) { 
			paintMST(g2);
		}
		
		
	}

	/**
	 * Method for printing the shortest path on the map
	 * @param g2
	 */
	private void paintDijkstra(Graphics2D g2) {
		g2.setPaint(Color.BLACK);
		
		for(Vertex thisCity : cities) {
			String thisName = thisCity.name;
			int thisX		= thisCity.xCoord / scaleFactor;
			int firstY		= thisCity.yCoord / scaleFactor;
			int thisY		= (yDimension - 170) - firstY;
			boolean onPath	= thisCity.onPath;

			//if the city is on the path, it prints the city name, 
			//as well as a line from the that city to the previous city, 
			//provided the previous city is not null (which means we have 
			//reached the starting city, and there are no more lines to 
			//be drawn
			if(onPath && thisCity.path != null) {
				int prevX		= thisCity.path.xCoord / scaleFactor ;
				int firstPrevY	= thisCity.path.yCoord / scaleFactor;
				int prevY 		= (yDimension - 170) - firstPrevY;
				String stopString = thisName + "\n";
				g2.drawString(stopString, thisX + 8, thisY);
				g2.drawLine(thisX, thisY, prevX, prevY);
				g2.fillOval(thisX -3, thisY - 3, 10, 10);
			}
			//if the city is not on the path, it prints just the city 
			//name, without adding any lines
			else {
				g2.drawString(thisName, thisX + 8, thisY);
				g2.fillOval(thisX -3, thisY - 3, 10, 10);
			}
			double totalDistance = endVertex.distance;
			String castDist = Double.toString(totalDistance);
			String distanceLabel = "Total Distance from " + sourceVertex.name 
					+ " to " + endVertex.name +": \n" + castDist + " miles";
			g2.drawString(distanceLabel, 10, 20);
		}
	}

	/**
	 * Method for printing the MST on the map
	 * @param g2
	 */
	private void paintMST(Graphics2D g2) {
		g2.setPaint(Color.BLACK);
		//prints all the cities 
		for(SpanVertex thisCity : spanCities) {
			String thisName = thisCity.name;
			int thisX 		= thisCity.xCoord / scaleFactor;
			int firstY 		= thisCity.yCoord / scaleFactor;
			int thisY 		= (yDimension - 170) - firstY;
			g2.drawString(thisName, thisX + 8, thisY);
			g2.fillOval(thisX -3, thisY - 3, 10, 10);
		}
		//prints all the edges
		for(SpanEdge thisEdge : cityEdges) {
			SpanVertex source 	= thisEdge.sourceVertex;
			SpanVertex end 		= thisEdge.endVertex;
			int sourceX			= source.xCoord / scaleFactor;
			int firstYsource  	= source.yCoord / scaleFactor;
			int sourceY			= (yDimension - 170) - firstYsource;
			
			int endX 			= end.xCoord / scaleFactor;
			int firstYend		= end.yCoord / scaleFactor;
			int endY 			= (yDimension - 170) - firstYend;
			
			g2.drawLine(sourceX, sourceY, endX, endY);	
			
		}
	}

	public void drawPlot() {
		JFrame f = new JFrame("City Graph");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JApplet applet = new DPlotter();
		f.getContentPane().add(applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(xDimension, yDimension));
		f.setVisible(true);

	}

	/**
	 * Precursor method to drawing MST
	 * @param minSpanTreeList
	 * @param vListXY
	 */
	public static void drawMST(ArrayList<SpanEdge> minSpanTreeList, 
										ArrayList<SpanVertex> vListXY) {
		cityEdges = minSpanTreeList;
		spanCities = vListXY;
		doDijkstra = false;

		DPlotter mstPlot = new DPlotter();
		mstPlot.drawPlot();
	}

	/**
	 * Precursor method to drawing shortest path
	 * @param citiesList
	 * @param sourceName
	 * @param endName
	 */
	public static void callDijkstra(ArrayList<Vertex> citiesList, 
								String sourceName, String endName) {
		doDijkstra = true;
		cities 	= citiesList;
		source 	= sourceName;
		end 	= endName;
		for(Vertex thisCity : citiesList) {
			if(thisCity.name.equals(sourceName)) {
				sourceVertex = thisCity;
			}
			if(thisCity.name.equals(endName)){
				endVertex = thisCity;
			}
		}
		DPlotter dPlot = new DPlotter();
		dPlot.drawPlot();
	}
	
	
	
	

}