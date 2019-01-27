import java.io.*;
import java.util.*;

public class Main4 {

    private final static boolean logOn = false;

    BufferedReader testCaseBr;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {
        }
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(int x) {
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        Vertex left = leftRight.left;
        Vertex right = leftRight.right;
//        log("leftRight.left.key = " + (leftRight.left == null ? "null" : leftRight.left.key));
//        log("leftRight.right.key = " + (leftRight.right == null ? "null" : leftRight.right.key));
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
//        log("root.key = " + (root == null ? "null" : root.key));
//        log("root.left.key = " + (root.left == null ? "null" : root.left.key));
//        log("root.right.key = " + (root.right == null ? "null" : root.right.key));
//        log("new_vertex.key = " + (new_vertex == null ? "null" : new_vertex.key));
//        log("new_vertex.left.key = " + (new_vertex == null || new_vertex.left == null ? "null" : new_vertex.left.key));
//        log("new_vertex.right.key = " + (new_vertex == null || new_vertex.right == null ? "null" : new_vertex.right.key));
    }

    void erase(int x) {
        VertexPair leftRight1 = split(root, x);
        VertexPair leftRight2 = split(leftRight1.right, x + 1);
        root = merge(leftRight1.left, leftRight2.right);
//        log("root.key = " + (root == null ? "null" : root.key));
//        log("root.left.key = " + (root.left == null ? "null" : root.left.key));
//        log("root.right.key = " + (root.right == null ? "null" : root.right.key));
    }

    boolean find(int x) {
        VertexPair closest = find(root, x);
        Vertex result = closest.left;
        root = closest.right;
        log("root.key = " + (root == null ? "null" : root.key));
        log("root.sum = " + (root == null ? "null" : root.sum));
        log("root.left.key = " + (root == null || root.left == null ? "null" : root.left.key));
        log("root.left.sum = " + (root == null || root.left == null ? "null" : root.left.sum));
        log("root.right.key = " + (root == null || root.right == null ? "null" : root.right.key));
        log("root.right.sum = " + (root == null || root.right == null ? "null" : root.right.sum));
        log("closest.left.key (next big value) = " + (closest.left == null ? "null" : closest.left.key));
        log("closest.right.key (new root) = " + (closest.right == null ? "null" : closest.right.key));
        return result != null && result.key == x;
    }

    long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        VertexPair middleRight = split(leftMiddle.right, to + 1);
        if (middleRight.left == null) {
            root = merge(leftMiddle.left, leftMiddle.right);
            return 0;
        }
        long sum = middleRight.left.sum;
        root = merge(leftMiddle.left, merge(middleRight.left, middleRight.right));
//        log("root.key = " + (root == null ? "null" : root.key));
//        log("root.sum = " + (root == null ? "null" : root.sum));
//        log("root.left.key = " + (root.left == null ? "null" : root.left.key));
//        log("root.left.sum = " + (root.left == null ? "null" : root.left.sum));
//        log("root.right.key = " + (root.right == null ? "null" : root.right.key));
//        log("root.right.sum = " + (root.right == null ? "null" : root.right.sum));
        return sum;
    }


    public static final int MODULO = 1_000_000_001;

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        String response;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+' : {
                    int x = nextInt();
                    insert((x + last_sum_result) % MODULO);
                } break;
                case '-' : {
                    int x = nextInt();
                    erase((x + last_sum_result) % MODULO);
                } break;
                case '?' : {
                    int x = nextInt();
                    response = find((x + last_sum_result) % MODULO) ? "Found" : "Not found";
                    out.println(response);
                } break;
                case 's' : {
                    int l = nextInt();
                    int r = nextInt();
                    Long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (int)(res % MODULO);
                }
            }
        }
    }

    Main44() throws IOException {
//        testCaseBr = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/dpiramidin/Downloads/_8d00aadacb6828b0c5ca515d2f4bd036_Programming-Assignment-4/set_range_sum/tests/36.a")));
        // new FileInputStream("/Users/dpiramidin/Downloads/_8d00aadacb6828b0c5ca515d2f4bd036_Programming-Assignment-4/set_range_sum/tests/36")
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main44();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
/*
15 
? 1
 + 1
 ? 1
 + 2
 s 1 2
 + 1000000000
 ? 1000000000
 - 1000000000
 ? 1000000000
 s 999999999 1000000000
- 2
? 2
- 0
+ 9
s 0 9
 */