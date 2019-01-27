import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Vertex implements Comparable<Vertex>
{
    int p;
    int postorder;

    Vertex(int p, int postorder)
    {
        this.p = p;
        this.postorder = postorder;
    }

    public int compareTo(Vertex y)
    {
        if (this.postorder == y.postorder) {
            return 0;
        }
        return this.postorder > y.postorder ? -1 : 1;
    }
}

public class Main73
{
    private static int counter = 1;
    private static int[] preorder;
    private static PriorityQueue<Vertex> postorder = new PriorityQueue<>();

    private static int getSCC(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjr)
    {
        preorder = new int[adj.length];
        for (int p = 1; p < adj.length; p++) {
            explore(adjr, p);
        }
        /*
          1. get max postorder
          2. find all vertices reachable from that vertex (using adj)
          3. remove those vertices from postorder (by settings preorder of a removed vertex to 0)
          4. repeat 1 until postorder is empty
         */
        int scc = 0;
        Vertex x;
        while (!postorder.isEmpty()) {
            do {
                x = postorder.poll();
            } while (preorder[x.p] == 0 && !postorder.isEmpty());
            // find all vertices in adj, remove them from preorder and that is it.
            scc += exploreSCC(adj, x.p);
        }

        return scc;
    }

    private static int exploreSCC(ArrayList<Integer>[] adj, int p)
    {
        if (preorder[p] > 0) {
            preorder[p] = 0;
            for (int descendant : adj[p]) {
                exploreSCC(adj, descendant);
            }
            return 1;
        }
        return 0;
    }

    private static void explore(ArrayList<Integer>[] adjr, int p)
    {
        if (preorder[p] == 0) {
            preorder[p] = counter++;
            for (int descendant : adjr[p]) {
                explore(adjr, descendant);
            }
            postorder.add(new Vertex(p, counter++));
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        ArrayList<Integer>[] adjr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
        }
        int x, y;
        for (int i = 1; i <= m; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
            adjr[y].add(x);
        }
        System.out.println(getSCC(adj, adjr));
    }
}
/*

2 2
1 2
2 1

9 13
1 2
2 3
3 4
5 4
5 6
6 5
7 6
1 7
8 2
8 2
9 6
9 8
3 9

response: 5
*/
