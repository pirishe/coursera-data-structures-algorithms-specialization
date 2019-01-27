import java.util.*;

public class Main3
{
    private static final boolean logOn = false;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int len = input.length();
        int digitsCount = ((len - 1) / 2) + 1;
        long[][] M = new long[digitsCount + 1][digitsCount + 1];
        long[][] m = new long[digitsCount + 1][digitsCount + 1];
        for (int i = 1; i <= digitsCount; i++) {
            M[i][i] = Long.valueOf(input.substring(2 * (i - 1), 2 * (i - 1) + 1));
            m[i][i] = Long.valueOf(input.substring(2 * (i - 1), 2 * (i - 1) + 1));
        }

        int s, i, j;
        // s = 1..3
        // i = 1..{3..1}
        // 1 2, 2 3, 3 4
        // 1 3, 2 4
        // 1 4
        for (s = 1; s < digitsCount; s++) {
            for (i = 1; i <= digitsCount - s; i++) {
                j = i + s;
                MaxAndMin(input, M, m, i, j);
            }
        }
        log(Arrays.deepToString(M));
        log(Arrays.deepToString(m));

        System.out.println(M[1][digitsCount]);
    }

    private static void MaxAndMin(String input, long[][] M, long[][] m, int i, int j)
    {
        long
                max = Integer.MIN_VALUE,
                min = Integer.MAX_VALUE,
                val
                        ;
        int k;
        char op;
        // i k, k + 1, j
        // i = 1, j = 4
        for (k = i; k < j; k++) {
            op = input.charAt(2 * k - 1);
            // M[i][k] op M[k + 1][j]
            // M[i][k] op m[k + 1][j]
            // m[i][k] op M[k + 1][j]
            // m[i][k] op m[k + 1][j]
            val = makeOperation(M[i][k], M[k + 1][j], op);
            max = Math.max(max, val);
            min = Math.min(min, val);
            val = makeOperation(M[i][k], m[k + 1][j], op);
            max = Math.max(max, val);
            min = Math.min(min, val);
            val = makeOperation(m[i][k], M[k + 1][j], op);
            max = Math.max(max, val);
            min = Math.min(min, val);
            val = makeOperation(m[i][k], m[k + 1][j], op);
            max = Math.max(max, val);
            min = Math.min(min, val);
        }
        M[i][j] = max;
        m[i][j] = min;
    }

    private static long makeOperation(long a, long b, char op)
    {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
