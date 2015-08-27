
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;



public class DPlotter extends JApplet {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height =(int) screenSize.getHeight();

	static ArrayList<Vertex> cities;
	static String source;
	static Vertex sourceVertex;
	static String end;
	static Vertex endVertex;
	static boolean doDijkstra; 
	static final int xDimension = width;
	static final int yDimension = height;
	static final int scaleFactor = 2;
	
	

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
			
		}
	}

	private void paintDijkstra(Graphics2D g2) {
		g2.setPaint(Color.BLACK);
		for(Vertex thisCity : cities) {
			String thisName = thisCity.name;
			int thisX		= thisCity.xCoord / scaleFactor;
			int firstY		= thisCity.yCoord / scaleFactor;
			int thisY		= (yDimension - 170) - firstY;
			boolean onPath	= thisCity.onPath;
			
			if(onPath && thisCity.path != null) {
				int prevX		= thisCity.path.xCoord / scaleFactor ;
				int firstPrevY	= thisCity.path.yCoord / scaleFactor;
				int prevY 		= (yDimension - 170) - firstPrevY;
				String stopString = thisName + "\n";
				g2.drawString(stopString, thisX, thisY);
				g2.drawLine(thisX, thisY, prevX, prevY);
			}
			else {
				g2.drawString(thisName, thisX, thisY);
			}
			double totalDistance = endVertex.distance;
			String castDist = Double.toString(totalDistance);
			String distanceLabel = "Total Distance from " + sourceVertex.name 
					+ " to " + endVertex.name +": \n" + castDist + " miles";
			g2.drawString(distanceLabel, 10, 20);
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

	public static void callDijkstra(ArrayList<Vertex> citiesList, 
						String sourceName, String endName, boolean dijkstra) {
		cities 	= citiesList;
		source 	= sourceName;
		end 	= endName;
		doDijkstra = dijkstra;
		for(Vertex thisCity : citiesList) {
			if(thisCity.name.equals(sourceName)) {
				sourceVertex = thisCity;
			}
			if(thisCity.name.equals(endName)){
				endVertex = thisCity;
			}
		}
		DPlotter plot = new DPlotter();
		plot.drawPlot();

	}
}