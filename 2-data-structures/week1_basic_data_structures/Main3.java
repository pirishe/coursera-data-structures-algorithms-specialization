import java.util.LinkedList;
import java.util.Scanner;

public class Main3
{
    private static final boolean logOn = false;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int S = scanner.nextInt();
        int n = scanner.nextInt();
        if (n == 0) {
            return;
        }
        int
                A = scanner.nextInt(),
                P = scanner.nextInt(),
                busyUntil = 0,
                qToProcessSize = 0,
                qTotalSize = 0,
                packetsRead = 0,
                p
//                i = 0
                        ;
        LinkedList<Integer> q = new LinkedList<>();
        /*
3 6
0 1
1 2
1 3
2 2
2 1
4 1
         */
        // S = 3
        // 0 1 -> 0, busyUntil = 1, q = []
        // 1 2 -> 1, busyUntil = 3, q = []
        // 1 3 -> busyUntil = 3, q = [[3 false]]
        // 2 2 -> busyUntil = 3, q = [[3 false], [2 false]]
        // 2 1 -> busyUntil = 3, q = [[3 false], [2 false], [1 false]]
        // 4 1 -> 3, busyUntil = 6, q = [[2 false], [1 false], [4 true]]
        // finishing...
        // -> 6, busyUntil = 8, q = [[1 false], [4 true]]
        // -> 8, busyUntil = 9, q = [[4 true]]
        // -> -1, busyUntil = 8, q = []
//        int[] output = new int[n];
        for (int time = 0;; time++) {
//            log("time = " + time);
            // remove finished packets
            while (qTotalSize > 0 && time >= busyUntil) {
                p = q.poll();
                qTotalSize--;
//                log("Remove packet: " + p);
                qToProcessSize--;
                System.out.println(time - p);
//                output[i] = time - p;
//                i++;
                while (qTotalSize > 0 && q.peek() == -1) {
//                    log("Remove packet: -1");
                    System.out.println("-1");
                    q.remove();
                    qTotalSize--;
                }
                if (qToProcessSize > 0) {
                    busyUntil = time + q.peek();
                }
            }
//            log("output = " + Arrays.toString(output));
            // A is a current time
            // nextPoll time is a time until processing of the next packet has been finished
            if (packetsRead < n) {
                while (A == time) {
                    if (qTotalSize == 0 && time >= busyUntil) {
                        busyUntil = time + P;
                    }
                    packetsRead++;
//                    log("Read packet: " + P + ", " + (qToProcessSize == S));
                    if (qToProcessSize == S) {
                        q.add(-1);
                        qTotalSize++;
                    } else if (qTotalSize == 0 && P == 0) {
                        System.out.println(time);
//                        output[i] = time;
//                        i++;
                    } else {
                        q.add(P);
                        qTotalSize++;
                        qToProcessSize++;
                    }
                    if (packetsRead == n) {
                        break;
                    }
                    A = scanner.nextInt();
                    P = scanner.nextInt();
                }
            } else if (qTotalSize == 0) {
                break;
            }
//            log("busyUntil = " + busyUntil);
//            log("packetsRead = " + packetsRead);
//            log("qTotalSize = " + qTotalSize);
//            log("qToProcessSize = " + qToProcessSize);
//            log("====================================");
        }
//        for (i = 0; i < n; i++) {
//            System.out.println(output[i]);
//        }
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
