import java.util.*;

public class Main2
{
    private static boolean logOn = false;

    private static final int maxV = 30;
    private static final int maxN = 20;
    private static final int parts = 3;
    private static final int sumsMaxCount = maxN * maxV / parts;

    private static boolean keyIsAlreadyUsed(int i, int[][] sum, int k)
    {
        for (int z = 0; z < k; z++) {
            for (int x : sum[z]) {
                if (x == 0) {
                    break;
                }
                if (x == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int partsMaxCount = n * (n - 1) * 100 / 2;
        int[] v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = scanner.nextInt();
        }
        if (n < 3) {
            System.out.println("0");
            System.exit(0);
        }
        int[][][] sums = new int[sumsMaxCount + 1][partsMaxCount][n];
        int prevSum;
        int k = 0;
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<String> s2 = new HashSet<>();
        ArrayList<Integer> merged = new ArrayList<>();
        String stringForHashing;
        for (int sum = 1; sum <= sumsMaxCount; sum++) {
            s1.clear();
            for (int i = 1; i <= n; i++) {
                prevSum = sum - v[i];
                if (prevSum < 0) {
                    continue;
                }
                // check i does not exist in prev "k" variants
                if (prevSum == 0) {
                    if (k == 0 || !keyIsAlreadyUsed(i, sums[sum], k)) {
                        sums[sum][k][0] = i;
                        k++;
                    }
                    /*if (sum == 12 && v[i] == 12) {
                     *//*
                        [
                          [1, 2, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [1, 2, 4, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [1, 3, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [1, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [1, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [2, 3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [2, 3, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [2, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [2, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [3, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [3, 4, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [4, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [5, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [5, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [6, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                          [6, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        ]
                         *//*
                        System.out.println("k = " + k);
                        System.out.println("sums[sum] = " + Arrays.deepToString(sums[sum]));
                        System.out.println("yo" + (k == 0 || !keyIsAlreadyUsed(i, sums[sum], k)));
                    }*/
                    log("sum = " + sum);
                    log("cond1");
                    log("sums[sum][k] = " + Arrays.toString(sums[sum][k - 1]));
                    log("=================================");
                    continue;
                }
                /*
                  1. Check that there are variants for prevSum. If no, continue.
                  2. Compose sums[v[i]] * sums[prevSum], filter duplicates, write each such variant to sums[sum][k][...]
                 */
                if (sums[prevSum][0][0] == 0) {
                    continue;
                }

                boolean hasCommonElements;
                for (int j = 0; j < partsMaxCount; j++) {
                    if (sums[v[i]][j][0] == 0) {
                        break;
                    }
                    for (int z = 0; z < partsMaxCount; z++) {
                        if (sums[prevSum][z][0] == 0) {
                            break;
                        }
                        hasCommonElements = false;
                        // merge:
                        // sums[v[i]][j]
                        // sums[prevSum][z]
                        for (int x : sums[v[i]][j]) {
                            if (x == 0) {
                                break;
                            }
                            s1.add(x);
                        }
                        for (int x : sums[prevSum][z]) {
                            if (x == 0) {
                                break;
                            }
                            if (s1.contains(x)) {
                                hasCommonElements = true;
                                break;
                            }
                        }
                        s1.clear();

                        log("sum = " + sum);
                        log("prevSum = " + prevSum);
                        log("hasCommonElements = " + hasCommonElements);
//                        if (sum == 29) {
//                            System.out.println("v[i] = " + v[i]);
//                            System.out.println("prevSum = " + prevSum);
//                            System.out.println("test1 = " + Arrays.toString(sums[v[i]][j]));
//                            System.out.println("test56 = " + Arrays.toString(sums[prevSum][z]));
//                            System.out.println("hasCommonElements = " + hasCommonElements);
//                        }

                        if (!hasCommonElements) {
                            for (int x : sums[v[i]][j]) {
                                if (x == 0) {
                                    break;
                                }
                                merged.add(x);
                            }
                            for (int x : sums[prevSum][z]) {
                                if (x == 0) {
                                    break;
                                }
                                merged.add(x);
                            }
                            Collections.sort(merged);
                            stringForHashing = merged.toString();
//                            if (sum == 29) {
//                                System.out.println("prevSum = " + prevSum);
//                                System.out.println(Arrays.toString(sums[v[i]][j]));
//                                System.out.println(Arrays.toString(sums[prevSum][z]));
//                                System.out.println(stringForHashing);
//                                System.out.println("k = " + k);
//                            }
                            if (!s2.contains(stringForHashing)) {
                                int pos = 0;
                                for (int x : merged) {
                                    sums[sum][k][pos] = x;
                                    pos++;
                                }
                                s2.add(stringForHashing);
                                log("result " + Arrays.toString(sums[sum][k]));
                                k++;
                            } else {
//                                log("result " + Arrays.toString(sums[sum][k]));
                            }
                            merged.clear();
                        }
                        log("=================================");
                    }
                }
            }
            // we've got all possible combinations for the "sum"
            // now we pick "parts" combinations so that all digits from "v" are taken

            // 6
            // 4 6 3 7 1 9
            // sums[sum]:
            // [
            //   [1, 2, 0, 0, 0, 0],
            //   [2, 3, 5, 0, 0, 0],
            //   [3, 4, 0, 0, 0, 0],
            //   [5, 6, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0]
            // ]
            //
            // 6
            // 2 2 1 2 1 1
            // sums[sum]:
            // [
            //   [1, 3, 0, 0, 0, 0], X
            //   [1, 5, 0, 0, 0, 0],
            //   [1, 6, 0, 0, 0, 0],
            //   [2, 3, 0, 0, 0, 0],
            //   [2, 5, 0, 0, 0, 0], X
            //   [2, 6, 0, 0, 0, 0],
            //   [3, 5, 6, 0, 0, 0],
            //   [3, 4, 0, 0, 0, 0],
            //   [4, 5, 0, 0, 0, 0],
            //   [4, 6, 0, 0, 0, 0], X
            //   [0, 0, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0],
            //   [0, 0, 0, 0, 0, 0]
            // ]
            int start = (1 << n - 1) | (-1 >>> (32 - (n - 1)));
            int check;
            boolean hasCommonElementsXY, hasCommonElementsYZ;
            // x = 0, y = 2, z = 3
            for (int x = 0; x < k; x++) {
                for (int y = x + 1; y < k; y++) {
                    hasCommonElementsXY = false;
                    for (int z = y + 1; z < k; z++) {
                        s1.clear();
                        check = start;
                        hasCommonElementsYZ = false;
                        for (int digit : sums[sum][x]) {
                            if (digit == 0) {
                                break;
                            }
                            s1.add(digit);
                            check ^= 1 << digit - 1;
                        }
                        for (int digit : sums[sum][y]) {
                            if (digit == 0) {
                                break;
                            }
                            if (s1.contains(digit)) {
                                hasCommonElementsXY = true;
                                break;
                            }
                            s1.add(digit);
                            check ^= 1 << digit - 1;
                        }
                        if (hasCommonElementsXY) {
                            break;
                        }
                        for (int digit : sums[sum][z]) {
                            if (digit == 0) {
                                break;
                            }
                            if (s1.contains(digit)) {
                                hasCommonElementsYZ = true;
                                break;
                            }
                            check ^= 1 << digit - 1;
                        }
                        log("sum = " + sum);
                        log("s1 = " + s1.toString());
                        log("hasCommonElementsYZ = " + hasCommonElementsYZ);
                        log("check = " + check);
                        log("x, y, z = " + x + ", " + y + ", " + z);
                        if (!hasCommonElementsYZ && check == 0) {
                            System.out.println("1");
                            log("one = " + Arrays.toString(sums[sum][x]));
                            log("two = " + Arrays.toString(sums[sum][y]));
                            log("three = " + Arrays.toString(sums[sum][z]));
                            log("x = " + x);
                            log("y = " + y);
                            log("z = " + z);
                            log("sums[sum] = " + Arrays.deepToString(sums[sum]));
                            System.exit(0);
                        }
                    }
                }
            }
            k = 0;
            s2.clear();
        }
        System.out.println("0");
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}