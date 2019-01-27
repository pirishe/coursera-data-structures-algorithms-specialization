import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main71
{
  private static final boolean logOn = false;

  // 0 - sink надо искать
  // -1 - sink больше нет (цикл)
  private static int nextSink = 0;
  private static HashSet<Integer> removedVertices = new HashSet<>();

  private static boolean isDAG(ArrayList<Integer>[] adj, int n)
  {
    int verticesLeft = n;
    do {
      int next = nextSink(adj);
      log("verticesLeft = " + verticesLeft);
      log("nextSink = " + next);
      if (next == -1) {
        return false;
      }
      removeSink(adj, next);
      verticesLeft--;
    } while (verticesLeft > 0);
    return true;
  }

  private static int nextSink(ArrayList<Integer>[] adj)
  {
    if (nextSink > 0) {
      if (!removedVertices.contains(nextSink)) {
        int ret = nextSink;
        nextSink = 0;
        return ret;
      }
      nextSink = 0;
    }
    for (int p = 1; p < adj.length; p++) {
      if (adj[p].size() == 0 && !removedVertices.contains(p)) {
        return p;
      }
    }
    return -1;
  }

  private static void removeSink(ArrayList<Integer>[] adj, Integer sink)
  {
    nextSink = 0;
    boolean removed;
    for (int p = 1; p < adj.length; p++) {
      removed = adj[p].remove(sink);
      if (removed && nextSink == 0 && adj[p].size() == 0) {
        nextSink = p;
      }
    }
    removedVertices.add(sink);
  }

  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    ArrayList<Integer>[] adj = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 1; i <= m; i++) {
      adj[scanner.nextInt()].add(scanner.nextInt());
    }
    System.out.println(isDAG(adj, n) ? 0 : 1);
  }

  private static void log(String msg)
  {
    if (logOn) {
      System.out.println(msg);
    }
  }
}
/*
5 5
1 2
2 3
3 4
1 5
5 4
*/