import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ChapFourExcerThirty {
    public class Edge {
        int vertex1;
        int vertex2;
        boolean traversed;

        Edge(int vertex1, int vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            traversed = false;
        }

        public int otherVertex(int vertex) {
            if (vertex == vertex1)
                return vertex2;
            else
                return vertex2;
        }
    }

    public class EulerCycle {
        public Stack<Integer> getEulerCycle(Graph graph) {
            if (graph.E() == 0)
                return new Stack<Integer>();

            for (int i = 0; i < graph.V(); i++) {
                if (graph.degree(i) % 2 == 1)
                    return null;
            }
            Queue<Edge>[] adjacent = (Queue<Edge>[]) new Queue[graph.V()];
            for (int vertex = 0; vertex < graph.V(); vertex++) {
                adjacent[vertex]=new Queue<>();
            }
            for (int i = 0; i < graph.V(); i++) {
                int selfLoop = 0;
                for (int neighbor : graph.adj(i)) {
                    if (i == neighbor) {
                        // I have a self loop
                        if (selfLoop % 2 == 0) {
                            Edge edge = new Edge(i, neighbor);
                            adjacent[i].enqueue(edge);
                            adjacent[neighbor].enqueue(edge);
                        }
                        selfLoop++;
                    } else if (i < neighbor) {
                        Edge edge = new Edge(i, neighbor);
                        adjacent[i].enqueue(edge);
                        adjacent[neighbor].enqueue(edge);
                    }
                }
            }
            int connectedVertex = connectedVertex(graph);
            Stack<Integer> dfsStack = new Stack<>();
            dfsStack.push(connectedVertex);
            Stack<Integer> eulerCycle = new Stack<>();
            while (!dfsStack.isEmpty()) {
                int vertex = dfsStack.pop();
                while (!adjacent[vertex].isEmpty()) {
                    Edge edge = adjacent[vertex].dequeue();
                    if (edge.traversed)
                        continue;
                    edge.traversed = true;
                    dfsStack.push(vertex);
                    vertex = edge.otherVertex(vertex);
                }
                // push vertex with no more leaving edges to the stack
                eulerCycle.push(vertex);
            }
            if (eulerCycle.size() == graph.E() + 1) {
                return eulerCycle;
            } else {
                return null;
            }
        }

        private int connectedVertex(Graph graph) {
            int connectedVertex = -1;
            for (int i = 0; i < graph.V(); i++) {
                if (graph.degree(i) > 0) {
                    connectedVertex = i;
                    break;
                }
            }
            return connectedVertex;
        }

    }

    private void printCycle(Stack<Integer> eulerCycle) {
        StdOut.println("Euler cycle:");

        while (!eulerCycle.isEmpty()) {
            int vertex = eulerCycle.pop();

            if (!eulerCycle.isEmpty()) {
                StdOut.print(vertex + "-" + eulerCycle.peek());

                if (eulerCycle.size() > 1) {
                    StdOut.print(" ");
                }
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        ChapFourExcerThirty chapFourExcerThirty = new ChapFourExcerThirty();
        EulerCycle eulerCycle = chapFourExcerThirty.new EulerCycle();
        Graph graphWithEulerPath1 = new Graph(4);
        graphWithEulerPath1.addEdge(0, 1);
        graphWithEulerPath1.addEdge(1, 2);
        graphWithEulerPath1.addEdge(2, 3);
        graphWithEulerPath1.addEdge(3, 0);
        graphWithEulerPath1.addEdge(3, 2);
        Stack<Integer> eulerCycle1 = eulerCycle.getEulerCycle(graphWithEulerPath1);
        if (eulerCycle1 != null) {
            chapFourExcerThirty.printCycle(eulerCycle1);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: There is no Eulerian cycle\n");
        Graph graphWithEulerCycle1 = new Graph(4);
        graphWithEulerCycle1.addEdge(0, 1);
        graphWithEulerCycle1.addEdge(1, 2);
        graphWithEulerCycle1.addEdge(2, 3);
        graphWithEulerCycle1.addEdge(3, 0);

        Stack<Integer> eulerCycle2 = eulerCycle.getEulerCycle(graphWithEulerCycle1);

        if (eulerCycle2 != null) {
            chapFourExcerThirty.printCycle(eulerCycle2);
        } else {
            StdOut.println("There is no Eulerian cycle");
        }
        StdOut.println("Expected: 0-3 3-2 2-1 1-0\n");
    }

}
