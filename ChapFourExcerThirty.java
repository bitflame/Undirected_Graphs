import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;

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
            Queue<Edge>[] adjacent = (Queue<Edge>[]) new Queue[G.V()];
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

    public static void main(String[] args) {

    }

}
