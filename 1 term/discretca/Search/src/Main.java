import Graph.Graph;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        mergeSort();
        graph();
    }

    public static void mergeSort() {
        Sorting sorting = new Sorting();
        int[] array = new int[]{6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sorting.mergeSort(array)));
        System.out.println(sorting.getCounter());
    }

    public static void graph() {
        Graph graph = new Graph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.BFS(3);
        System.out.println("---");
        graph.DFS(1);
    }
}