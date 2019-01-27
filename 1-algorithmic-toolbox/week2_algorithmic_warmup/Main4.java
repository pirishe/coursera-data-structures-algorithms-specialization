import java.util.Scanner;

public class Main4
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();

        System.out.println(least_multiple(a, b));
    }

    private static long least_multiple(long a, long b)
    {
        // x / 6
        // x / 8
        // x = 6 * k1
        // x = 8 * k2
        // k1/k2 = 8/6
        // find gcd, divide by it and find x

        // x / 6
        // x / 9
        // x = 6 * k1
        // x = 9 * k2
        // k1/k2 = 9/6 = 3/2
        // x = 18
        long gcd = gcd(a, b);
        return (a / gcd) * b;
    }

    private static long gcd(long a, long b)
    {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}