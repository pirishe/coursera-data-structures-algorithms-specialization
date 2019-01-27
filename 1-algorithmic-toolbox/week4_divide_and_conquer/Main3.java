import java.io.*;
import java.util.*;

public class Main3
{
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r)
    {
        // partition2([2, 2, 3, 3, 3, 7, 5, 4], 5, 7)
        // i = 6..7
        // l = 5
        // r = 7
        // j = 5
        // m = 5
        // x = 7
        int x = a[l];
        int j = l;
        int m = l;
        int tmp;
        for (int i = j + 1; i <= r; i++) {
//            System.out.println("iteration before: " + i + " " + m + " " + j + " " + Arrays.toString(a));
            if (a[i] <= x) {
                // swap i & j
                j++;
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
                // swap j & m
                if (a[j] == x) {
                    m++;
                    tmp = a[j];
                    a[j] = a[m];
                    a[m] = tmp;
                }
                // i = 6, 5<=7 - yes, [2, 2, 3, 3, 3, 7, 5, 4], j = 6, m = 5
                // i = 7, 4<=7 - yes, [2, 2, 3, 3, 3, 7, 5, 4], j = 7, m = 5
//                System.out.println("iteration after: " + i + " " + m + " " + j + " " + Arrays.toString(a));
            }
        }
        // swap l & j ?????????????
        // l ... m ... j
        // if l == m then just swap l & j
        // if l = 3, m = 5
        for (int i = 0; i <= m - l; i++) {
            tmp = a[l + i];
            a[l + i] = a[j - i];
            a[j - i] = tmp;
        }
        // i = 5..1
        // swap 0 & 4 AND 1 & 3
        // 2 2 3 3 3 4 5 7
        // return {m, j} = {2, 4}
        return new int[] {j - (m - l), j}; // границы отрезка m включительно
    }

    private static int partition2(int[] a, int l, int r)
    {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r)
    {
        if (l >= r) {
            return;
        }



        // test
//        l = 2;
//        r = 7;
//        int k = 7;

        // @TODO k is random!!!!!!!!!!!!!!!
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;






//        System.out.println("x = " + a[l]);
//        System.out.println("l = " + l);
//        System.out.println("r = " + r);
        int[] m = partition3(a, l, r);
//        System.out.println("k = " + k);
//        System.out.println("m[0] = " + m[0]);
//        System.out.println("m[1] = " + m[1]);
//        System.out.println(Arrays.toString(a));
//        System.out.println("==========================================================");
//        System.out.println("next l & r: " + l + " " + (m[0] - 1));
        randomizedQuickSort(a, l, m[0] - 1);
//        System.out.println("next l & r: " + (m[1] + 1) + " " + r);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

