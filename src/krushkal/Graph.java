package krushkal;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Graph {
	 Vertex[] vertices;
     Edge edgeList;
     int maxSize;
     int size;
     int edgeNum;

     public Graph(int maxSize) {
         this.maxSize = maxSize;
         vertices = new Vertex[maxSize];
     }
     public void addVertex(int name) {
         vertices[size++] = new Vertex(name);
     }

     public void addEdge(int src, int dest, int weight) {
         vertices[src - 1].adj = new Neighbour(dest - 1, weight, vertices[src - 1].adj);
         edgeList = new Edge(vertices[src - 1], vertices[dest - 1], weight, edgeList);
         edgeNum++;
     }

     public void applyKrushkalAlgo() throws FileNotFoundException, UnsupportedEncodingException {
 		 long start2 = KrushkalAlgorithm.start1;
    	 long total = Runtime.getRuntime().totalMemory();
    	 long used  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    	 PrintWriter writer1 = new PrintWriter("resultJava1.txt", "UTF-8");
    	 PrintWriter writer2 = new PrintWriter("resultJava2.txt", "UTF-8");
         writer1.println("Total number of nodes = "+maxSize);
         ArrayList<Integer>cost = new ArrayList<Integer>();

         Edge[] edges = new Edge[edgeNum];
         int i = 0;
         while (edgeList != null) {
             edges[i] = edgeList;
             i++;
             edgeList = edgeList.next;
         }
         quicksort(edges, 0, edgeNum - 1);
         for (i = 0; i < edgeNum; i++) {
             Vertex u = findSet(edges[i].src);
             Vertex v = findSet(edges[i].desti);
             if (u != v) {
            	 writer2.println("("+edges[i].src.name + " , " + edges[i].desti.name+")"+" edge cost: "+edges[i].weight);
                 union(u, v);
                  cost.add(edges[i].weight) ;
             }
         }
         int sum = 0;
         for (int count=0;count<cost.size();count++){
        	 sum += cost.get(count);
         }
         writer1.println("Total number of edges in the minimum spanning three= "+cost.size());
         writer1.println("Total cost of minimum spanning three is= "+sum);
         writer1.println("Total Memory= "+total);
         writer1.println("Used Memory= "+used);
         long end = System.currentTimeMillis();
         long secs = (end - start2)/1000;
         writer1.println("Total execution time = "+secs+" seconds");
         writer1.println("CLICK SKIP TO VIEW -----> List of edges & their costs");

         writer1.close();
         writer2.close();

     }

     public Vertex findSet(Vertex u) {
         if (u.representative != u) {
             u.representative = findSet(u.representative); // path compression
         }
         return u.representative;
     }

     public void union(Vertex u, Vertex v) {
         if(u.rank == v.rank){
             v.representative = u;
             u.rank++;
         }else if(u.rank < v.rank){
             v.representative = u;
         }else{
             u.representative = v;
         }
     }

     public void quicksort(Edge[] edges, int start, int end) {
         if (start < end) {
             swap(edges, end, start + (end - start) / 2);
             int pIndex = pivot(edges, start, end);
             quicksort(edges, start, pIndex - 1);
             quicksort(edges, pIndex + 1, end);
         }
     }

     public int pivot(Edge[] edges, int start, int end) {
         int pIndex = start;
         Edge pivot = edges[end];
         for (int i = start; i < end; i++) {
             if (edges[i].weight < pivot.weight) {
                 swap(edges, i, pIndex);
                 pIndex++;
             }
         }
         swap(edges, end, pIndex);
         return pIndex;
     }

     public void swap(Edge[] edges, int index1, int index2) {
         Edge temp = edges[index1];
         edges[index1] = edges[index2];
         edges[index2] = temp;
     }
 }

