
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;;

public class ExcerFourThirty {
    boolean[] marked;
    int[] edgeTo;
    boolean[] onStack;
    Stack<Integer> cycle = new Stack<Integer>();

    public boolean eulerian(Graph G) {
        // Find the cycle, save it on a stack then check to see if all the vertices have
        // even degrees or not
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        Arrays.fill(edgeTo, -1);
        onStack = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                onStack[s] = true;
                marked[s] = true;
                dfs(G, s, s);
            }
        }
        return false;
    }

    private void dfs(Graph G, int v, int u) {
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                onStack[w] = true;
                marked[w] = true;
                dfs(G, w, u);
            } else if (w != edgeTo[v] && v != u) {
                System.out.println("Here is the previous cycle: " + cycle.toString());
                cycle = new Stack<Integer>();
                for (int x = v; x != w && x != -1; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                onStack[w] = false;
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
