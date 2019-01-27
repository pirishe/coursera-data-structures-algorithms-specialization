import java.util.Scanner;

public class Main1
{
    private final static boolean logOn = false;

    private static final int C1 = 1;
    private static final int C3 = 3;
    private static final int C4 = 4;

    private static int[] cache;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        cache = new int[money];
        System.out.println(getLowestCoinsCount(money) + " ");
    }

    private static int getLowestCoinsCount(int m)
    {
        if (m == 0) {
            return 0;
        }
        if (m < 0) {
            return Integer.MAX_VALUE - 1;
        }
        if (cache[m - 1] == 0) {
            /*
              getLowestCoinsCount(5)
                -> getLowestCoinsCount(4)
                  -> getLowestCoinsCount(3)
                    -> getLowestCoinsCount(2)
                      -> getLowestCoinsCount(1)
                        -> getLowestCoinsCount(0)
                        -> getLowestCoinsCount(-2)
                        -> getLowestCoinsCount(-3)
                      -> getLowestCoinsCount(-1)
                      -> getLowestCoinsCount(-2)
                    -> getLowestCoinsCount(0)
                    -> getLowestCoinsCount(-1)
                  -> getLowestCoinsCount(1)
                  -> getLowestCoinsCount(0)
                -> getLowestCoinsCount(2)
                  -> getLowestCoinsCount(1)
                  -> getLowestCoinsCount(-1)
                  -> getLowestCoinsCount(-2)
                -> getLowestCoinsCount(1)
                  -> getLowestCoinsCount(0)
                  -> getLowestCoinsCount(-2)
                  -> getLowestCoinsCount(-3)
              getLowestCoinsCount(3)
              getLowestCoinsCount(2)
             */
            int count1 = getLowestCoinsCount(m - C1);
            log((m - C1) + " " + count1);
            int count2 = getLowestCoinsCount(m - C3);
            log((m - C3) + " " + count2);
            int count3 = getLowestCoinsCount(m - C4);
            log((m - C4) + " " + count3);
            log("=======================");
            cache[m - 1] = Math.min(
                    Math.min(count1, count2),
                    Math.min(count2, count3)
            ) + 1;
        }
        return cache[m - 1];
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
