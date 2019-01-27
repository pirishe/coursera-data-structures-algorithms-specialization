import java.util.PriorityQueue;
import java.util.Scanner;

public class Main3
{
    private final static boolean logOn = false;

    private static int EditDistance (String a, String b)
    {
        byte lena = (byte) a.length();
        byte lenb = (byte) b.length();
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
                c.add((byte) (d[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1)));

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
        return d[lena][lenb];
    }

    public static void main (String args[])
    {
        Scanner scan = new Scanner(System.in);

        String a = scan.next();
        String b = scan.next();

        System.out.println(EditDistance(a, b));
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.print(msg);
        }
    }
}
