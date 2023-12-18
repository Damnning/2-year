package Graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int index;
    List<Edge> edges;
    List<Node> connected;
    public Node(int index) {
        this.index = index;
        edges = new ArrayList<>();
        connected = new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
