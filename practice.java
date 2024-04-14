import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;

public class practice {
    private static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }

    private static int maximumDegree(Graph G) {
        int maxDegree = 0;
        for (int v = 0; v < G.V(); v++) {
            if (maxDegree < degree(G, v))
                maxDegree = degree(G, v);
        }
        return maxDegree;
    }

    public static int avgDegree(Graph G) {
        return 2 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        Bag<Integer>[] adj;

        adj = (Bag<Integer>[]) new Bag[100];

        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w)
                    count++;
            }
        }

        return count / 2;

    }

    protected class DepthFirstPath {
        private static boolean[] marked = new boolean[20];
        private static int[] edgeTo;
        private static final int s = 0;

        private DepthFirstPath(Graph G, int s) {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];

            dfs(G, s);
        }

        private static void dfs(Graph G, int s) {
            marked[s] = true;
            for (int w : G.adj(s)) {
                edgeTo[w] = s;
                if (!marked[w])
                    dfs(G, w);
            }
        }

        private static boolean hasPathTo(int v) {
            return marked[v];
        }

        private static Iterable<Integer> pathTo(int v) {
            if (!hasPathTo(v))
                return null;
            Stack<Integer> path = new Stack<Integer>();
            for (int x = v; x != s; x = edgeTo[x]) {
                path.push(x);
            }
            path.push(s);
            return path;
        }
    }

    private class BreadthFirstPaths {
        private boolean[] marked;
        private int[] edgeTo;
        private final int s = 0;

        public BreadthFirstPaths(Graph G, int s) {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            // this.s = s;
            bfs(G, s);
        }

        private void bfs(Graph G, int source) {
            marked[source] = true;
            Queue<Integer> queue = new Queue();
            queue.enqueue(source);
            while (!queue.isEmpty()) {
                int v = queue.dequeue();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        marked[w] = true;
                        queue.enqueue(w);
                    }
                }
            }
        }
    }

    private class SymbolGraph {
        private ST<String, Integer> st;
        private String[] keys;
        private Graph G;

        public SymbolGraph(String stream, String sp) {
            st = new ST<String, Integer>();
            In in = new In(stream);
            while (in.hasNextLine()) {
                String[] a = in.readLine().split(sp);
                for (int i = 0; i < a.length; i++) {
                    if (!st.contains(a[i]))
                        st.put(a[i], st.size());
                }
            }
            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
            }
            G = new Graph(st.size());
            in = new In(stream);
            while (in.hasNextLine()) {
                String[] a = in.readLine().split(sp);
                int v = st.get(a[0]);
                for (int i = 1; i < a.length; i++) {
                    G.addEdge(v, st.get(a[i]));
                }
            }
        }

        public boolean contains(String s) {
            return st.contains(s);
        }

        public int index(String s) {
            return st.get(s);
        }

        public String name(int v) {
            return keys[v];
        }

        public Graph G() {
            return G;
        }
    }

    private class CC {
        private boolean[] marked;
        private int[] id;
        private int count;

        public CC(Graph G) {
            id = new int[G.V()];
            for (int s = 0; s < G.V(); s++) {
                if (!marked[s]) {
                    dfs(G, s);
                    count++;
                }
            }
        }

        private void dfs(Graph G, int s) {
            marked[s] = true;
            id[s] = count;
            for (int w : G.adj(s)) {
                if (!marked[w])
                    dfs(G, w);
            }
        }
    }

    private class Cycle {
        private boolean[] marked;
        private boolean hasCycle;

        private Cycle(Graph G) {
            marked = new boolean[G.V()];
            for (int s = 0; s < G.V(); s++) {
                if (!marked[s])
                    dfs(G, s, s);
            }
        }

        private void dfs(Graph G, int v, int u) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w])
                    dfs(G, w, v);
                else if (w != u)
                    hasCycle = true;
            }
        }

        public boolean hasCycle() {
            return hasCycle;
        }

    }

    private class TwoColor {
        private boolean[] marked;
        private boolean[] color;
        private boolean isTwoColorable = true;

        public TwoColor(Graph G) {
            marked = new boolean[G.V()];
            color = new boolean[G.V()];
            for (int s = 0; s < G.V(); s++) {
                if (!marked[s])
                    dfs(G, s);
            }
        }

        private void dfs(Graph G, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    color[w] = !color[v];
                    dfs(G, w);
                } else if (color[w] == color[v])
                    isTwoColorable = false;
            }
        }

        public boolean isBipartite() {
            return isTwoColorable;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hi");
        StdOut.print("Hi princeton");
    }
}