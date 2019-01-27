import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*class Vertex implements Comparable<Vertex>
{
    int value;
    int w;

    Vertex(int value, int w)
    {
        this.value = value;
        this.w = w;
    }

    public int compareTo(Vertex o)
    {
        if (this.w == o.w) {
            return 0;
        }
        return this.w > o.w ? -1 : 1;
    }
}*/

public class Main91
{
    private static int getMinFlightCost(ArrayList<Integer>[] adj, ArrayList<Integer>[] w, int u, int v)
    {
        int[] dist = new int[adj.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[u] = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((Integer x, Integer y) -> y - x);
        q.add(u);
        int p, descendant;
        while (!q.isEmpty()) {
            p = q.poll();
            for (int i = 0; i < adj[p].size(); i++) {
                descendant = adj[p].get(i);
                if (dist[descendant] > dist[p] + w[p].get(i)) {
                    dist[descendant] = dist[p] + w[p].get(i);
                    q.add(descendant);
                }
            }
        }
        return dist[v] == Integer.MAX_VALUE ? -1 : dist[v];
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        ArrayList<Integer>[] w = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>(n + 1);
            w[i] = new ArrayList<>(n + 1);
        }
        int x, y, z;
        for (int i = 1; i <= m; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            z = scanner.nextInt();
            adj[x].add(y);
            w[x].add(z);
        }
        System.out.println(getMinFlightCost(adj, w, scanner.nextInt(), scanner.nextInt()));
    }
}
/*

4 4
1 2 1
4 1 2
2 3 2
1 3 5
1 3

5 9
1 2 4
1 3 2
2 3 2
3 2 1
2 4 2
3 5 4
5 4 1
2 5 3
3 4 4
1 5

3 3
1 2 7
1 3 5
2 3 2
3 2
*/
