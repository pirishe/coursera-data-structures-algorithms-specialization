import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main81
{
    private static int getShortestPath(ArrayList<Integer>[] adj, int u, int v)
    {
        int[] dist = new int[adj.length];
        int p;
        for (p = 0; p < adj.length; p++) {
            dist[p] = -1;
        }
        dist[u] = 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.add(u);
        while (!q.isEmpty()) {
            p = q.pop();
            for (int descendant : adj[p]) {
                if (dist[descendant] == -1) {
                    q.add(descendant);
                    dist[descendant] = dist[p] + 1;
                }
            }
        }
        return dist[v];
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int x, y;
        for (int i = 1; i <= m; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        System.out.println(getShortestPath(adj, scanner.nextInt(), scanner.nextInt()));
    }
}
/*

4 4
1 2
4 1
2 3
3 1
2 4

response: 5
*/
