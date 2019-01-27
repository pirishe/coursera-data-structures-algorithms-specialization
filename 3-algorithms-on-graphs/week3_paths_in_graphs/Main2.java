import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main82
{
    private static boolean isBipartite(ArrayList<Integer>[] adj)
    {
        byte[] color = new byte[adj.length];
        int p;
        for (p = 0; p < adj.length; p++) {
            color[p] = -1;
        }
        p = 1;
        color[p] = 1;
        LinkedList<Integer> q = new LinkedList<>();
        q.add(p);
        while (!q.isEmpty()) {
            p = q.pop();
            for (int descendant : adj[p]) {
                if (color[descendant] == -1) {
                    q.add(descendant);
                    color[descendant] = (byte) (color[p] == 1 ? 2 : 1);
                } else if (color[descendant] == color[p]) {
                    return false;
                }
            }
        }
        return true;
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
        System.out.println(isBipartite(adj) ? 1 : 0);
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
