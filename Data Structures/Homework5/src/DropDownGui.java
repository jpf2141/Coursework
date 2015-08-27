
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.*;

public class DropDownGui { 
	

///////////////////////////////////////////////////////////////////////
////////////////////////////////Origin/////////////////////////////////
///////////////////////////////////////////////////////////////////////

	    public static class OriginResponse implements Runnable {

	        private ArrayList<Vertex> vList;
	        private String originResponse;

	        public OriginResponse(ArrayList<Vertex> vertexList) {
	            this.vList = vertexList;
	        }

	        @Override
	        public void run() {
	            originResponse = originPrompt(vList);
	        }
	        public String getOrigin() {
	            return originResponse;
	        }
	    }
	    
	public static String originPrompt(ArrayList<Vertex> vListXY) {
		String result = null;
        
        if (EventQueue.isDispatchThread()) {

            JPanel panel = new JPanel();
            JLabel head = new JLabel("Select Origin");
            head.setLocation(500, 200);
            panel.add(head);
            
            DefaultComboBoxModel cityList = new DefaultComboBoxModel();
            
            
            for (Vertex value : vListXY) {
                cityList.addElement(value.name);
            }
            
            JComboBox comboBox = new JComboBox(cityList);
            panel.add(comboBox);

            int iResult = JOptionPane.showConfirmDialog(null,panel,"Origin"
            , JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (iResult) {
                case JOptionPane.OK_OPTION:
                    result = (String) comboBox.getSelectedItem();
                    break;
            }

        } else {

            OriginResponse response = new OriginResponse(vListXY);
            try {
                SwingUtilities.invokeAndWait(response);
                result = response.getOrigin();
            } catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }
        return result;
    }

	
	
	
///////////////////////////////////////////////////////////////////////
////////////////////////////////Destination////////////////////////////
///////////////////////////////////////////////////////////////////////
	
	public static class DestinationResponse implements Runnable {

        private ArrayList<Vertex> vList;
        private String destinationResponse;

        public DestinationResponse(ArrayList<Vertex> vertexList) {
            this.vList = vertexList;
        }

        @Override
        public void run() {
            destinationResponse = destinationPrompt(vList);
            
        }

        public String getDestination() {
            return destinationResponse;
        }
    }
	public static String destinationPrompt(ArrayList<Vertex> vListXY) {
		String result = null;
        
        if (EventQueue.isDispatchThread()) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Select Destination"));
            DefaultComboBoxModel cityList = new DefaultComboBoxModel();
            
            for (Vertex value : vListXY) {
                cityList.addElement(value.name);
            }
            
            JComboBox comboBox = new JComboBox(cityList);
            panel.add(comboBox);

            int iResult =JOptionPane.showConfirmDialog(null,panel,"Destination"
            , JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (iResult) {
                case JOptionPane.OK_OPTION:
                    result = (String) comboBox.getSelectedItem();
                    break;
            }

        } else {

            DestinationResponse response = new DestinationResponse(vListXY);
            try {
                SwingUtilities.invokeAndWait(response);
                result = response.getDestination();
            } catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
        }
        return result;
    }
}

	
