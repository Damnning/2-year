package Graph;

import java.util.*;

public class Graph {
    List<Edge> edges;
    List<Node> nodes;
    public Graph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }
    public void addNode(int index) {
        for (Node node : nodes) {
            if(node.index == index) {
                return;
            }
        }
        nodes.add(new Node(index));
    }
    public void addEdge(int first, int second) {
        for (Edge edge : edges) {
            if((edge.first.index  == first || edge.first.index == second) &&
                    (edge.second.index == second|| edge.second.index == first)) {
                return;
            }
        }
        Node firstNode = getNode(first);
        Node secondNode = getNode(second);
        if (firstNode == null || secondNode == null) {
            return;
        }
        Edge newEdge = new Edge(firstNode, secondNode);
        firstNode.connected.add(secondNode);
        firstNode.edges.add(newEdge);
        secondNode.connected.add(firstNode);
        secondNode.edges.add(newEdge);
        edges.add(newEdge);
    }
    public Node getNode(int index) {
        for (Node node : nodes) {
            if(node.index == index) {
                return node;
            }
        }
        return null;
    }
    public Edge getEdge(int first, int second) {
        for (Edge edge : edges) {
            if(edge.first.index == first && edge.second.index == second) {
                return edge;
            }
        }
        return null;
    }
    public void BFS(int index) {
        List<Node> visited = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        Node curr = getNode(index);
        queue.add(curr);
        visited.add(curr);
        while(!queue.isEmpty()){
            curr = queue.remove();
            System.out.println(curr.index);
            for (Node node : curr.connected) {
                if(!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
            }

        }
    }
    public void DFS(int index) {
        List<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curr = getNode(index);
        stack.add(curr);
        visited.add(curr);
        while(!stack.isEmpty()){
            curr = stack.pop();
            System.out.println(curr.index);
            for (Node node : curr.connected) {
                if(!visited.contains(node)) {
                    stack.add(node);
                    visited.add(node);
                }
            }

        }
    }
}
