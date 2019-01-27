import java.util.PriorityQueue;
import java.util.Scanner;

class Job
{
    int threadId;
    Long finishesAt;

    Job(int threadId, long finishesAt)
    {
        this.threadId = threadId;
        this.finishesAt = finishesAt;
    }
}

public class Main2
{
    private static final boolean logOn = true;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        PriorityQueue<Job> q = new PriorityQueue<>((Job a, Job b) -> a.finishesAt.compareTo(b.finishesAt));
        int currentInitN = 0;
        int t;
        Job next;
        for (int i = 0; i < m; i++) {
            t = scanner.nextInt();
            if (currentInitN < n) {
                q.add(new Job(currentInitN, t));
                System.out.print(currentInitN);
                System.out.println(" 0");
                currentInitN++;
                continue;
            }
            next = q.poll();
            q.add(new Job(next.threadId, next.finishesAt + t));
            System.out.print(next.threadId);
            System.out.print(' ');
            System.out.println(next.finishesAt);
        }
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
