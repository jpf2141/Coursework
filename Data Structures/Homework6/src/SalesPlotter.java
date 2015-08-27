
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class SalesPlotter extends JApplet {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int) screenSize.getWidth();
	static int height =(int) screenSize.getHeight();
	static final int xDimension = width;
	static final int yDimension = height;
	static final int xShiftAdd = 500;
	static final int yShiftAdd = 200;
	
	static tspVertex[] nodes;
	
	
	
	public SalesPlotter() {
	}

	public void init() {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.white);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		drawRoute(g2);	
	}

	private static void drawRoute(Graphics2D g2) {
		for(int i = 0; i < nodes.length; i++) { 
			tspVertex thisNode = nodes[i];
			int L = thisNode.label;
			String label = Integer.toString(L);
			int x = thisNode.scaledX;
			int y = thisNode.scaledY;
			g2.drawString(label, x, y);
		}
		
		g2.drawLine(nodes[0].scaledX, nodes[0].scaledY, nodes[1].scaledY, nodes[1].scaledY);
//		g2.drawLine(nodes[1].scaledX, nodes[1].scaledY, nodes[2].scaledY, nodes[2].scaledY);
//		g2.drawLine(nodes[2].scaledX, nodes[2].scaledY, nodes[3].scaledY, nodes[3].scaledY);
//		g2.drawLine(nodes[3].scaledX, nodes[3].scaledY, nodes[4].scaledY, nodes[4].scaledY);
//		g2.drawLine(nodes[4].scaledX, nodes[4].scaledY, nodes[5].scaledY, nodes[5].scaledY);
//		g2.drawLine(nodes[5].scaledX, nodes[5].scaledY, nodes[6].scaledY, nodes[6].scaledY);
//		g2.drawLine(nodes[6].scaledX, nodes[6].scaledY, nodes[7].scaledY, nodes[7].scaledY);
//		g2.drawLine(nodes[7].scaledX, nodes[7].scaledY, nodes[0].scaledY, nodes[0].scaledY);
	}

	public static void drawPoints() {
		JFrame f = new JFrame("City Graph");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JApplet applet = new SalesPlotter();
		f.getContentPane().add(applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(xDimension, yDimension));
		f.setVisible(true);
	}
	
	public static void callSalesman(tspVertex[] pathList) {
		nodes = pathList;
		drawPoints();
	}

	

	
	
	

}