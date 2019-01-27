import java.util.PriorityQueue;
import java.util.Scanner;

public class Main4
{
    private final static boolean logOn = false;

    private static byte[][] getMatrix(int[] a, int[] b, byte lena, byte lenb)
    {
        byte d[][] = new byte[lena + 1][lenb + 1];
        for (byte i = 0; i <= lena; i++) {
            d[i][0] = i;
        }
        for (byte i = 0; i <= lenb; i++) {
            d[0][i] = i;
        }
        PriorityQueue<Byte> c = new PriorityQueue<>();
        for (byte i = 1; i <= lena; i++) {
            for (byte j = 1; j <= lenb; j++) {
                // insertion
                c.add((byte) (d[i - 1][j] + 1));
                // deletion
                c.add((byte) (d[i][j - 1] + 1));
                // match or mismatch
                c.add((byte) (d[i - 1][j - 1] + (a[i - 1] == b[j - 1] ? 0 : 1)));

                d[i][j] = c.peek();
                c.clear();
            }
        }
        if (logOn) {
            for (byte i = 0; i <= lena; i++) {
                for (byte j = 0; j <= lenb; j++) {
                    log(d[i][j] + " ");
                }
                log("\n");
            }
        }
        return d;
    }

    private static byte getLongestCommonSubsequenceLength(int[] a, int[] b, byte[][] d, byte i, byte j)
    {
        boolean isMatch = i > 0 && j > 0 && a[i - 1] == b[j - 1];
        PriorityQueue<Byte> c = new PriorityQueue<>((x, y) -> y - x);
        // is a backtrack of vertical edge
        if (i > 0 && d[i][j] == d[i - 1][j] + 1) {
            c.add(getLongestCommonSubsequenceLength(a, b, d, (byte)(i - 1), j));
        }
        // is a backtrack of horizontal edge
        if (j > 0 && d[i][j] == d[i][j - 1] + 1) {
            c.add(getLongestCommonSubsequenceLength(a, b, d, i, (byte)(j - 1)));
        }
        // is a backtrack of diagonal edge
        if (i > 0 && j > 0 && d[i][j] == (d[i - 1][j - 1] + (isMatch ? 0 : 1))) {
            c.add((byte)(getLongestCommonSubsequenceLength(a, b, d, (byte)(i - 1), (byte)(j - 1)) + (isMatch ? 1 : 0)));
        }
        return c.size() > 0 ? c.peek() : 0;
    }

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        byte n = scan.nextByte();
        int[] a = new int[n];
        for (byte i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        byte m = scan.nextByte();
        int[] b = new int[m];
        for (byte i = 0; i < m; i++) {
            b[i] = scan.nextInt();
        }

        byte[][] d = getMatrix(a, b, n, m);
        System.out.println(getLongestCommonSubsequenceLength(a, b, d, n, m));
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.print(msg);
        }
    }
}
