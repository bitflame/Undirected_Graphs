
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;;

public class ExcerFourThirty {
    boolean[] marked;
    Queue<Integer> queue = new Queue<>();
    int[] edgeTo;
    Stack<Integer> cycle = new Stack<Integer>();

    public boolean eulerian(Graph G) {
        // Find the cycle, save it on a stack then check to see if all the vertices have
        // even degrees or not
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        Arrays.fill(edgeTo, -1);
        edgeTo[0] = 0;
        for (int s = 0; s < G.V(); s++) {
            dfs(G, s, s, false);
        }
        return false;
    }

    private void dfs(Graph G, int v, int u, boolean eularian) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, u, eularian);
            } else if (w != u) {
                // we have a cycle
                for (int i = w; i != u; i = edgeTo[i]) {
                    if (G.degree(i) % 2 == 0) {
                        eularian = true;
                    } else {
                        // if you find one odd degree there is not Eularian cycle; return
                        eularian = false;
                        break;
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
