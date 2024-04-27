
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;;

public class ExcerFourThirty {
    boolean[] marked;
    int[] edgeTo;
    boolean[] onStack;
    Stack<Integer> cycle = new Stack<Integer>();
    boolean eulerian = true;
    boolean hamiltonian;

    public boolean eulerian(Graph G) {
        // Find the cycle, save it on a stack then check to see if all the vertices have
        // even degrees or not. Try BFS. DFS does not seem to see all the cycles
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
        return eulerian;
    }

    private void dfs(Graph G, int v, int u) {
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                onStack[w] = true;
                marked[w] = true;
                dfs(G, w, u);
                // w != edgeTo[v] && v != u
            } else if (onStack[w] && w != edgeTo[v]) {

                cycle = new Stack<Integer>();
                for (int x = v; x != w && x != -1; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                onStack[v] = false;
                for (int i : cycle) {
                    if (G.degree(i) % 2 == 1) {
                        eulerian = false;
                    } else
                        eulerian = true;
                }
                if (eulerian)
                    return;
                System.out.println("Here is the current cycle: " + cycle.toString());
            }
        }
    }

    // Hamiltonian cycles are detected by doing a dfs of
    public boolean eulerianBFS(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        Arrays.fill(edgeTo, -1);
        hamiltonian = false;
        bfs(G, 0);
        return eulerian;
    }

    private void bfs(Graph G, int i) {
        marked[i] = true;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(i);
        onStack = new boolean[G.V()];
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    onStack[w] = true;
                    queue.enqueue(w);
                } else if (onStack[w] && w != edgeTo[v]) {
                    // if it is marked, we have a cycle < -- Does not work and it is not enough
                    cycle = new Stack<>();
                    eulerian = true;
                    for (int x = v; x != w && x != -1; x = edgeTo[x]) {
                        if (G.degree(x) % 2 == 1) {
                            eulerian = false;
                        }
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                    System.out.println("Here is the next cycle: " + cycle);
                    if (G.degree(i) % 2 == 1) {
                        eulerian = false;
                    }
                    onStack[v] = false;
                }
            }
        }
    }

    public boolean getHamiltonian() {
        return hamiltonian;
    }

    public static void main(String[] args) {
        In in;
        Graph currentGraph;
        ExcerFourThirty excerFourThirty = new ExcerFourThirty();
        for (String fileName : args) {
            in = new In(fileName);
            currentGraph = new Graph(in);
            System.out.println("Testing Graph: " + fileName + " using depth first search.");
            System.out.printf("Graph %s has eularian cycle: %b\n", fileName, excerFourThirty.eulerian(currentGraph));
        }
        for (String fileName : args) {
            in = new In(fileName);
            currentGraph = new Graph(in);
            System.out.println("Testing Graph: " + fileName + " using breadth first search.");
            System.out.printf("Graph %s has hamiltonian cycle: %b\n", fileName,
                    excerFourThirty.eulerianBFS(currentGraph));
        }
    }
}
