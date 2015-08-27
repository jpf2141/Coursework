import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ResponseGui extends JFrame implements ActionListener{

	public JButton tsp = new JButton("Travelling Salesman");
	public JButton nearestNeighbor = new JButton("Nearest Neighbor");
	public JPanel panel = new JPanel();

	public ResponseGui(){
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200,200);
		this.setTitle("Choose Option");

		panel.add(tsp);
		panel.add(nearestNeighbor);

		tsp.addActionListener(this); 
		nearestNeighbor.addActionListener(this);
		this.add(panel);
		this.pack();
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e){ 
		Object actionSource = e.getSource();
		if(actionSource == tsp) {
			System.out.print("TSP");
			tspVertex.selectSales();
		}
		else if (actionSource == nearestNeighbor) { 
			System.out.print("NN");
		}
	}

	
	
	

}

