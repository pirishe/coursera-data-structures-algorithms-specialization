import java.util.Arrays;
import java.util.Scanner;

public class Main1
{
    private final static boolean logOn = false;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int W = scanner.nextInt();
        int n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        Arrays.sort(w);
        if (w[0] > W) {
            log("w[0] > W");
            System.out.println(0);
            System.exit(0);
        }
        // 10
        // 3 5 7 11 13
        // found: 3 (7)
        int left = 0;
        int right = n - 1;
        int mid = -1, midVal = -1;
        while (left <= right) {
            // left = 0
            // right = 4
            // mid = 2
            // midVal = 7
            // W = 10
            /*
10 5
11 13 16 20
             */
            mid = (left + right) / 2;
            midVal = w[mid];
            if (midVal > W) {
                if (mid > 0 && w[mid - 1] < W) {
                    mid--;
                    break;
                }
                right = mid - 1;
            } else if (midVal < W) {
                if (mid < n - 1 && w[mid + 1] > W) {
                    break;
                }
                left = mid + 1;
            } else {
                break;
            }
        }
        // gold bars 0..mid inclusive
        // W - knapsack size
        // w[i] - weight of a gold bar
        int[][] map = new int[n + 1][W + 1];
        int val;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (j < w[0]) {
                    map[i][j] = 0;
                    continue;
                }
                // map[i][j] = max(map[i - 1, j], map[i - 1, j - w[i - 1]]);
                if (j - w[i - 1] < 0) {
                    val = 0;
                } else {
                    val = map[i - 1][j - w[i - 1]] + w[i - 1];
                    if (val > W) {
                        val -= w[i - 1];
                    }
                }
                map[i][j] = Math.max(map[i - 1][j], val);
            }
        }
//        System.out.println(Arrays.deepToString(map));
        System.out.println("" + map[n][W]);
        /*int sum = 0;
        for(int i = mid; i >= 0; i--) {
            W -= w[i];
            if (W < 0) {
                W += w[i];
            } else {
                sum += w[i];
            }
        }
        System.out.println(sum);*/
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.print(msg);
        }
    }
}
