import java.util.Stack;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * ExerciseFourThree
 */
public class ExerciseFourThree {

    public class CopyGraph {
        private final int vertices;
        private int edges;
        private Bag<Integer>[] adjacent;

        public CopyGraph(int vertices) {
            this.vertices = vertices;
            this.edges = 0;
            adjacent = (Bag<Integer>[]) new Bag[vertices];
        }

        public CopyGraph(In in) {
            this(in.readInt());
            int edges = in.readInt();
            for (int i = 0; i < edges; i++) {
                int vertex1 = in.readInt();
                int vertex2 = in.readInt();
                addEdge(vertex1, vertex2);
            }
        }

        public CopyGraph(Graph graph) {
            if (graph == null) {
                vertices = 0;
            } else {
                this.vertices = graph.V();// it was .vertices()
                this.edges = graph.E();// it was .edges()
                adjacent = (Bag<Integer>[]) new Bag[vertices];
                for (int i = 0; i < vertices; i++) {
                    adjacent[i] = new Bag<>();
                }
                for (int vertex = 0; vertex < graph.V(); vertex++) {
                    Stack<Integer> stack = new Stack<>();
                    for (int neighbor : graph.adj(vertex)) {
                        // you need to push neighbors to stack, then...
                        stack.push(neighbor);
                    }
                    // ...add them to the bag
                    while (!stack.isEmpty()) {
                        adjacent[vertex].add(stack.pop());
                    }
                    // I also found out that a for loo as follows seems to pop all of the values
                    // first or something
                    /*
                     * for(int neighbor: stack){
                     * it adds the items like a queue would; not like a stack
                     * }
                     */
                }
            }
        }

        public int vertices() {
            return vertices;
        }

        public int edges() {
            return edges;
        }

        public void addEdge(int vertex1, int vertex2) {
            adjacent[vertex1].add(vertex2);
            adjacent[vertex2].add(vertex1);
            edges++;
        }

        public Iterable<Integer> adjacent(int vertex) {
            return adjacent[vertex];
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int vertex = 0; vertex < vertices(); vertex++) {
                stringBuilder.append(vertex).append(": ");
                for (int neighbor : adjacent(vertex)) {
                    stringBuilder.append(neighbor).append(" ");
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        ExerciseFourThree exerciseFourThree = new ExerciseFourThree();
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);

        CopyGraph copyGraph = exerciseFourThree.new CopyGraph(graph);
        System.out.println("Here is Graph: " + graph);
        StdOut.println("Here is copyGraph: " + copyGraph);
        StdOut.print("Expected:\n" +
                "0: 3 2 1\n" +
                "1: 4 2 0\n" +
                "2: 3 1 0\n" +
                "4: 1\n");

        copyGraph.addEdge(0, 4);
        System.out.println("Edges in original graph: " + graph.E() + "Expected: 6");
        System.out.println("Edges in copy graph: " + copyGraph.edges() + "Expected: 7");
    }

}