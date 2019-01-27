import java.util.ArrayList;
import java.util.Scanner;

public class Main92
{
    private static int hasAnomalies(ArrayList<Integer>[] adj, ArrayList<Integer>[] w)
    {
        int[] dist = new int[adj.length];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE - 1001;
        }
        dist[1] = 0;
        int descendant;
        boolean verticeChanged;
        for (int i = 0; i < adj.length; i++) {
            verticeChanged = false;
            for (int vertice = 1; vertice < adj.length; vertice++) {
                for (int edge = 0; edge < adj[vertice].size(); edge++) {
                    descendant = adj[vertice].get(edge);
                    if (dist[descendant] > dist[vertice] + w[vertice].get(edge)) {
                        dist[descendant] = dist[vertice] + w[vertice].get(edge);
                        verticeChanged = true;
                    }
                }
            }
            if (!verticeChanged) {
                break;
            }
        }
        for (int vertice = 1; vertice < adj.length; vertice++) {
            for (int edge = 0; edge < adj[vertice].size(); edge++) {
                descendant = adj[vertice].get(edge);
                if (dist[descendant] > dist[vertice] + w[vertice].get(edge)
                ) {
                    dist[descendant] = dist[vertice] + w[vertice].get(edge);
                    return 1;
                }
            }
        }
        return 0;
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
        System.out.println(hasAnomalies(adj, w));
    }
}
/*

4 4
1 2 -5
4 1 2
2 3 2
3 1 1

4 4
1 2 1
4 1 2
2 3 2
3 1 -5

10 9
1 2 1
6 7 1
8 9 1
9 10 1
3 4 1
7 8 1
4 5 1
5 6 1
2 3 1

*/
