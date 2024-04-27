import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class CopyGraph {
    int edges = 0;
    int vertices = 0;
    Bag<Integer>[] adjacencies;

    CopyGraph(Graph G) {
        adjacencies = (Bag<Integer>[]) new Bag[G.V()];
        for (int i = 0; i < G.V(); i++) {
            adjacencies[i] = new Bag<Integer>();
        }
        Stack<Integer> neighbors;
        for (int vertex = 0; vertex < G.V(); vertex++) {
            vertices++;
            neighbors = new Stack<Integer>();
            for (int neighbor : G.adj(vertex)) {
                neighbors.push(neighbor);
            }
            for (int neighbor : neighbors) {
                    addEdge(vertex, neighbor);
            }
        }
    }

    public int E() {
        return edges;
    }

    public int V() {
        return vertices;
    }

    public void addEdge(int vertex1, int vertex2) {
        adjacencies[vertex1].add(vertex2);
        edges++;
    }

    public Iterable<Integer> adj(int vertex) {
        return adjacencies[vertex];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int vertex = 0; vertex < vertices; vertex++) {
            sb.append(vertex + " :");
            for (int neighbor : adjacencies[vertex]) {
                sb.append(" " + neighbor + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        CopyGraph copyGraph = new CopyGraph(graph);
        System.out.println("Here is graph: " + graph);
        System.out.println("Here is CopyGraph: " + copyGraph);
    }
}
