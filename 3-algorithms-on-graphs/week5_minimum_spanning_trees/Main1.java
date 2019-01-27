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

public class Main101
{
    private static double minimumDistance(short[] x, short[] y, PriorityQueue<Segment> q, short n)
    {
        if (n == 1) {
            return 0;
        }
        short[] parent = new short[n + 1];
        short[] rank = new short[n + 1];
        short[] count = new short[n + 1]; // elements count in disjoint set
        for (int i = 1; i <= n; i++) {
            count[i] = 1;
        }

        double result = 0.;
        Segment s;
        short rootA, rootB, mergedRoot;
        for (;;) {
            s = q.poll();
//            System.out.println("segment (a: " + s.a + ", b: " + s.b + ", l: " + s.l + ")");
            rootA = djsFind(s.a, x, y, parent, count);
//            System.out.println("rootA = " + rootA);
            rootB = djsFind(s.b, x, y, parent, count);
//            System.out.println("rootB = " + rootB);
            if (rootA == rootB) {
                continue;
            }
            mergedRoot = djsUnion(rootA, rootB, x, y, parent, rank, count);
//            System.out.println("mergedRoot = " + mergedRoot);
//            System.out.println("count[mergedRoot] = " + count[mergedRoot]);
            result += Math.sqrt((x[s.a] - x[s.b])*(x[s.a] - x[s.b]) + (y[s.a] - y[s.b])*(y[s.a] - y[s.b]));
//            System.out.println("result = " + result);
//            System.out.println("===============");
            if (count[mergedRoot] == n) {
                return result;
            }
        }
    }

    private static short djsUnion(short rootA, short rootB, short[] x, short[] y, short[] parent, short[] rank, short[] count)
    {
        if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
            count[rootA] += count[rootB];
            return rootA;
        }
        parent[rootA] = rootB;
        count[rootB] += count[rootA];
        if (rank[rootA] == rank[rootB]) {
            rank[rootB]++;
        }
        return rootB;
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
        System.out.println(String.format("%.9f", minimumDistance(x, y, q, n)));
//        float a = (float) Math.sqrt(2);
//        a += (float) Math.sqrt(2);
//        a += (float) Math.sqrt(5);
//        a += (float) 2;
//        System.out.println(String.format("%.9f", a));
    }
}
/*
4
0 0
0 1
1 0
1 1

5
0 0
0 2
1 1
3 0
3 2
7.064495102
7.064495087
 */
