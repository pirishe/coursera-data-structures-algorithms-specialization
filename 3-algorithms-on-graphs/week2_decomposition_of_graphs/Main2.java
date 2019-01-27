import java.util.*;

public class Main72
{
  private static final boolean logOn = true;

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

    Stack<Integer> s = new Stack<>();
    LinkedHashSet<Integer> removedSinks = new LinkedHashSet<>();
    int i = 1;
    int to;
    Integer p;
    while (i <= n || !s.isEmpty()) {
      p = !s.isEmpty() ? s.pop() : i++;
      if (adj[p].isEmpty()) {
        removedSinks.add(p);
        continue;
      }
      // go to next
      s.push(p);
      do {
        to = adj[p].remove(adj[p].size() - 1);
      } while (removedSinks.contains(to) && adj[p].size() > 0);
      s.push(to);
    }

    LinkedList<Integer> list = new LinkedList<>(removedSinks);
    Iterator<Integer> itr = list.descendingIterator();
    while(itr.hasNext()) {
      System.out.print(itr.next());
      System.out.print(' ');
    }
  }

  private static void log(String msg)
  {
    if (logOn) {
      System.out.println(msg);
    }
  }
}
/*
5 7
2 1
3 2
3 1
4 3
4 1
5 2
5 3
*/