import java.util.*;

class Node
{
    HashSet<Integer> children;
    int parent;
    int height;

    Node()
    {
        children = new HashSet<>();
    }
}

public class Main2
{
    private static final boolean logOn = true;

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] nodes = new Node[n];
        int parent, rootNodeKey = 0;
        for (int i = 0; i < n; i++) {
            parent = scanner.nextInt();
            if (nodes[i] == null) {
                nodes[i] = new Node();
            }
            nodes[i].parent = parent;
            if (parent == -1) {
                rootNodeKey = i;
            } else {
                if (nodes[parent] == null) {
                    nodes[parent] = new Node();
                }
                nodes[parent].children.add(i);
            }
        }
        System.out.println("" + getHeight(nodes, rootNodeKey));
    }

    private static int getHeight(Node[] nodes, int rootNodeKey)
    {
        nodes[rootNodeKey].height = 1;
        Queue<Integer> q = new PriorityQueue<>(nodes[rootNodeKey].children);
        int
                i,
                max = nodes[rootNodeKey].height
                        ;
        while(!q.isEmpty()) {
            i = q.poll();
            nodes[i].height = nodes[nodes[i].parent].height + 1;
            max = Math.max(max, nodes[i].height);
            q.addAll(nodes[i].children);
        }
        return max;
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
