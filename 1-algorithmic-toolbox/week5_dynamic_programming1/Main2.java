import java.util.*;

class Item
{
    Integer key;
    int value;

    Item(int key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

public class Main2
{
    private final static boolean logOn = true;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] lowestOperations = new int[n + 1];
        lowestOperations[0] = 0;
        lowestOperations[1] = 0;
        PriorityQueue<Integer> cInt = new PriorityQueue<>();
        for (int i = 2; i < n + 1; i++) {
            // i = 2
            // toCompare = [2 1]
            // lowestOperations = [0 1]
            cInt.add(lowestOperations[i - 1]);
            if (i % 2 == 0) {
                cInt.add(lowestOperations[i / 2]);
            }
            if (i % 3 == 0) {
                cInt.add(lowestOperations[i / 3]);
            }
            lowestOperations[i] = cInt.peek() + 1;
            cInt.clear();
        }
        System.out.println(lowestOperations[n]);
        int[] seq = new int[lowestOperations[n] + 1];
        seq[0] = 1;
        int i = n;
        PriorityQueue<Item> cItem = new PriorityQueue<>((Item c1, Item c2) -> {
            return c1.key.compareTo(c2.key);
        });
        while (i > 1) {
            seq[lowestOperations[i]] = i;
            cItem.add(new Item(lowestOperations[i - 1], i - 1));
            if (i % 2 == 0) {
                cItem.add(new Item(lowestOperations[i / 2], i / 2));
            }
            if (i % 3 == 0) {
                cItem.add(new Item(lowestOperations[i / 3], i / 3));
            }
            i = cItem.peek().value;
            cItem.clear();
        }
        for (int x : seq) {
            System.out.print(x + " ");
        }
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
