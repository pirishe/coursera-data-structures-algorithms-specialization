import java.util.PriorityQueue;
import java.util.Scanner;

class Segment
{
    short a;
    short b;
    Float l;

    Segment(short a, short b, float l)
    {
        this.a = a;
        this.b = b;
        this.l = l;
    }
}

public class Main102
{
    private static double minimumDistance(short[] x, short[] y, PriorityQueue<Segment> q, short n, short k)
    {
        short setsCount = n;
        short[] parent = new short[n + 1];
        short[] rank = new short[n + 1];
        short[] count = new short[n + 1]; // elements count in disjoint set
        for (int i = 1; i <= n; i++) {
            count[i] = 1;
        }

        Segment s;
        short rootA, rootB;
        while (setsCount > k) {
            s = q.poll();
            rootA = djsFind(s.a, x, y, parent, count);
            rootB = djsFind(s.b, x, y, parent, count);
            if (rootA == rootB) {
                continue;
            }
            djsUnion(rootA, rootB, x, y, parent, rank, count);
            setsCount--;
        }
        double result = Double.MAX_VALUE;
        double d;
        while (!q.isEmpty()) {
            s = q.poll();
            rootA = djsFind(s.a, x, y, parent, count);
            rootB = djsFind(s.b, x, y, parent, count);
            if (rootA == rootB) {
                continue;
            }
            d = Math.sqrt((x[s.a] - x[s.b])*(x[s.a] - x[s.b]) + (y[s.a] - y[s.b])*(y[s.a] - y[s.b]));
            result = Math.min(result, d);
        }
        return result;
    }

    private static void djsUnion(short rootA, short rootB, short[] x, short[] y, short[] parent, short[] rank, short[] count)
    {
        if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
            count[rootA] += count[rootB];
            return;
        }
        parent[rootA] = rootB;
        count[rootB] += count[rootA];
        if (rank[rootA] == rank[rootB]) {
            rank[rootB]++;
        }
        return;
    }

    private static short djsFind(short p, short[] x, short[] y, short[] parent, short[] count)
    {
        if (parent[p] > 0) {
            short oldParent = parent[p];
            parent[p] = djsFind(parent[p], x, y, parent, count);
            if (parent[p] != oldParent) {
                count[oldParent] -= count[p];
            }
            return parent[p];
        }
        return p;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        short n = scanner.nextShort();
        short[] x = new short[n + 1];
        short[] y = new short[n + 1];
        PriorityQueue<Segment> q = new PriorityQueue<>(
                (Segment a, Segment b) -> a.l.compareTo(b.l)
        );
        for (short i = 1; i <= n; i++) {
            x[i] = scanner.nextShort();
            y[i] = scanner.nextShort();
            for (short j = 1; j < i; j++) {
                q.add(new Segment(
                        i,
                        j,
                        (float) Math.sqrt((x[i] - x[j])*(x[i] - x[j]) + (y[i] - y[j])*(y[i] - y[j]))
                ));
            }
        }
        short k = scanner.nextShort();
        System.out.println(String.format("%.9f", minimumDistance(x, y, q, n, k)));
    }
}
/*
12
7 6
4 3
5 1
1 7
2 7
5 7
3 3
7 8
2 8
4 4
6 7
2 6
3
2.828427124746
2.828427125

3
0 0
1 0
0 1
3
 */
