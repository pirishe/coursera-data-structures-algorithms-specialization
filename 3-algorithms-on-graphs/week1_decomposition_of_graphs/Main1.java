import java.util.ArrayList;
import java.util.Scanner;

public class Main51
{
    private static int[] groups;

    private static int reach(ArrayList<Integer>[] adj, int x, int y)
    {
        groups = new int[adj.length + 1];
        int group = 1;
        for (int p = 1; p < adj.length; p++) {
            if (groups[p] == 0) {
                groups[p] = group;
                explore(adj, p, group);
                group++;
            }
        }
        return groups[x] == groups[y] ? 1 : 0;
    }

    private static void explore(ArrayList<Integer>[] adj, int p, int group)
    {
        for (int descendant : adj[p]) {
            if (groups[descendant] == 0) {
                groups[descendant] = group;
                explore(adj, descendant, group);
            }
        }
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
        for (int i = 1; i <= m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(reach(adj, x, y));
    }
}
/*
4 4
1 2
3 2
4 3
1 4
1 4
 */