import java.util.ArrayList;

public class Kruskal { 	
	
	public Kruskal(){	
	}
	
	/**
	 * Method to perform kruskal's algorithm 
	 * @param edgeList
	 * @param numVertices
	 * @return
	 */
	public static ArrayList<SpanEdge> kruskal(ArrayList<SpanEdge> edgeList, 
													int numVertices) {
		//fills array that is used to build the heap
		SpanEdge[] edgeArray = new SpanEdge[edgeList.size() ];
		for (int i = 0; i < edgeList.size(); i++) {
			edgeArray[i] = edgeList.get(i);
		}
		
		DisjSets ds = new DisjSets(numVertices + 1);
		BinaryHeap heap = new BinaryHeap(edgeArray);
		ArrayList<SpanEdge> mst = new ArrayList<SpanEdge>();
		
		while(mst.size() != numVertices - 1) { 
			SpanEdge e = (SpanEdge) heap.deleteMin();
		
			int uset = ds.find(e.sourceVertex.vertexNum);
			int vset = ds.find(e.endVertex.vertexNum);
			
			if(uset != vset) { 
				mst.add(e);
				ds.union(uset, vset);
			}
		}
		printSpanPairs(mst);
		return mst;
	}
	
	public static void printSpanPairs(ArrayList<SpanEdge> spanEdgeList) {
		System.out.println("Spanning Tree Edge Pairs: ");
		for(int i = 0; i < spanEdgeList.size(); i++) {
			System.out.print("Source: "+spanEdgeList.get(i).sourceVertex.name);
			System.out.print("\t\tEnd: " +spanEdgeList.get(i).endVertex.name);
			System.out.println("\t\tCost: " +spanEdgeList.get(i).edgeLength);
			
		}
	}
	
	
}