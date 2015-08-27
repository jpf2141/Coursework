
import java.util.ArrayList;

import javax.swing.JApplet;

public class Dijkstra { 
	ArrayList<Vertex> cityList;
	String sourceName;
	String endName;


	public Dijkstra(ArrayList<Vertex> vListXY, String origin, 
			String destination) {
		this.cityList		= vListXY;
		this.sourceName 	= origin;
		this.endName 		= destination;


	}

	public void dijkstra() {
		Vertex sourceVertex = null;
		for (Vertex currentVertex: cityList) {
			if (currentVertex.name.equals(sourceName)){
				sourceVertex = currentVertex;
			}
		}
		dijkstra(cityList, sourceVertex);
	}

	/**
	 * Matches the source name with the corresponding Vertex object 
	 * @param citiesList
	 * @param source
	 */
	private void dijkstra(ArrayList<Vertex> citiesList,  Vertex source) {

		BinaryHeap heap = new BinaryHeap();

		for (Vertex currentVertex : citiesList) {	//for each vertex 
			currentVertex.distance 	= Double.POSITIVE_INFINITY;
		}

		//System.out.println("Source: " + source.name);	//TEST PRINT
		source.distance = 0;
		source.isKnown = true;
		heap.insert(source);

		while(!heap.isEmpty()) {
			//casts from comparable to vertex 

			Vertex v = (Vertex) heap.deleteMin();

			//System.out.println(v.adjacencyList.size());	//TEST PRINT
			//System.out.println("Min: " + v.name);	//TEST PRINT

			//for each vertex w adjacent to v
			for(int i = 0; i < v.adjacencyList.size(); i++) {	 
				Vertex w = v.adjacencyList.get(i);	//the ith neighbor
				//adjacency cost of W	
				double edgeCurtoNxt = v.adjacencyCost.get(i);	

				if(v.distance + edgeCurtoNxt < w.distance) {
					//line 1: update distance; line 2: update path
					w.distance = v.distance + edgeCurtoNxt;	 
					w.path = v;
					if(!w.isKnown) {
						heap.insert(w);
						w.isKnown = true;	//if w has been inserted 
					}
				}
			}
		}
		DPlotter.callDijkstra(citiesList, sourceName, endName, true);
	}


	public void pathFinder() {
		Vertex endVertex = null;
		for (Vertex currentVertex : cityList) {
			if (currentVertex.name.equals(endName)){
				endVertex = currentVertex;
			}
		}
		printPath(endVertex);
	}

	private void printPath(Vertex endVertex) {
		if(endVertex.path != null) {
			printPath(endVertex.path);
			System.out.print(" to ");
		}
		endVertex.onPath = true;
		System.out.print(endVertex.name);	
	}







}