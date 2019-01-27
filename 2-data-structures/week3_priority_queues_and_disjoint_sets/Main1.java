import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main1
{
    private static final boolean logOn = false;
    private static StringBuilder resString = new StringBuilder();
    private static int resCounter = 0;
//    private static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int N = findClosestBase2(n);
//        log("n = " + n);
//        log("N = " + N);
//        log("i is iterated from 0 to " + (N/2 - 1) + " exclusive");
        for (int i = N/2 - 2; i >= 0; i--) {
//            log("Sift down start for " + i);
            siftDown(a, i, n);
//            log("Sift down result for " + i + " is " + Arrays.toString(a));
//            log("=========================================================");
        }
//        int len = result.size();
//        System.out.println(len / 2);
//        for (int i = 0; i < len; i += 2) {
//            System.out.println(result.get(i) + " " + (result.get(i + 1)));
//        }
        System.out.println(resCounter);
        System.out.print(resString);
    }

    private static void siftDown(int[] a, int i, int n)
    {
        int key = i, left, right;
        for (;;) {
            left = 2 * key + 1;
            right = 2 * key + 2;
//            log("key = " + key + ", left = " + left + ", right = " + right);
            if (left < n && right < n) {
                if (a[key] < a[left] && a[key] < a[right]) {
//                    log("All ok, break");
                    break;
                }
                if (a[left] < a[right]) {
//                    log("Swap key & left");
                    swap (a, key, left);
                    key = left;
                } else {
//                    log("Swap key & right");
                    swap (a, key, right);
                    key = right;
                }
//                log("Result after swap " + Arrays.toString(a));
                continue;
            } else if (left < n && a[key] > a[left]) {
//                log("Right out of bounds, swap key & left");
                swap(a, key, left);
            }
//            log("Left or right out of bounds, break");
            break;
        };
    }

    private static void swap(int[] a, int i, int j)
    {
        // swap i & j
//        result.add(i);
//        result.add(j);
        resString.append(i);
        resString.append(" ");
        resString.append(j);
        resString.append("\n");
        resCounter++;

        // a[i] = 3
        // a[j] = 4
        // a[i] = a[i] + a[j] = 3 + 4 = 7
        // a[j] = a[i] - a[j] = 7 - 4 = 3
        // a[i] = a[i] - a[j] = 7 - 3 = 4
        a[i] = a[i] + a[j];
        a[j] = a[i] - a[j];
        a[i] = a[i] - a[j];
    }

    private static int findClosestBase2(int n)
    {
        double powed;
        for (int i = 0;; i++) {
            powed = Math.pow(2, i);
            if (powed > n) {
                return (int) Math.round(powed);
            }
        }
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
