
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;;

public class ExcerFourThirty {
    boolean[] marked;
    Queue<Integer> queue = new Queue<>();
    int[] edgeTo;

    public boolean eulerian(Graph G) {
        // Find the cycle, save it on a stack then check to see if all the vertices have
        // even degrees or not
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            bfs(G, s);
        }
        return false;
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                } else {
                    // found a cycle
                    for (int i = w; i != v; i = edgeTo[i]) {
                        // push i on to a stack to get the cycle's path
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph Graph1 = new Graph(in);
        System.out.println(Graph1);
        ExcerFourThirty excerFourThirty = new ExcerFourThirty();
        System.out.println("Graph1 result: " + excerFourThirty.eulerian(Graph1));
    }
}
