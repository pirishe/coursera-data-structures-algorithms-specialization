import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

public class Main5 {

    private final static boolean logOn = false;
    private static int S_LENGTH;

    //    BufferedReader testCaseBr;
    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        char key;
        int size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.size = 1 + (v.left != null ? v.left.size : 0) + (v.right != null ? v.right.size : 0);
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

    Vertex find(Vertex root, int key) {
        if (key < 1 || key > S_LENGTH) {
            return null;
        }
        int s;
        for (int i = 0; i < S_LENGTH; i++) {
            s = (root.left == null ? 0 : root.left.size) + 1;
            if (s == key) {
                root = splay(root);
                return root;
            }
            if (s > key) {
                root = root.left;
            } else {
                root = root.right;
                key = key - s;
            }
        }
        return null;
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        if (key < 1) {
            result.right = root;
            return result;
        }
        if (key > root.size) {
            result.left = root;
            return result;
        }
        Vertex found = find(root, key);
        if (found == null) {
            throw new InvalidParameterException(key + " not found in vertex " + root.key);
        }
        result.right = splay(found);
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

    void solve() throws IOException {
        String s = nextString();
        S_LENGTH = s.length();
        // create empty tree
        Vertex[] tree = new Vertex[S_LENGTH + 1];
        int size;
        for (int i = S_LENGTH; i >= 1; i--) {
            size = 1;
            tree[i] = new Vertex(
                    2 * i <= S_LENGTH ? tree[2 * i] : null,
                    2 * i + 1 <= S_LENGTH ? tree[2 * i + 1] : null
            );
            if (2 * i <= S_LENGTH) {
                tree[2 * i].parent = tree[i];
                size += tree[2 * i].size;
            }
            if (2 * i + 1 <= S_LENGTH) {
                tree[2 * i + 1].parent = tree[i];
                size += tree[2 * i + 1].size;
            }
            tree[i].size = size;
        }
        // do in-order traversal and init chars from string
        root = tree[1];
        Stack<Vertex> stack = new Stack<>();
        int strPos = 0;
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root.key = s.charAt(strPos);
                strPos++;
                root = root.right;
            }
        }
//        for (int i = 1; i <= S_LENGTH; i++) {
//            log(tree[i].key + " " + tree[i].size);
//        }
//        Vertex found;
//        for (int i = 1; i <= S_LENGTH; i++) {
//            found = find(tree[1], i);
//            log("found: " + (found != null ? found.key : "null"));
//            log("found.left: " + (found != null && found.left != null ? found.left.key : "null"));
//            log("found.right: " + (found != null && found.right != null ? found.right.key : "null"));
//        }

        // process queries
/*
abcdef
3
0 0 5
4 4 5
5 5 0
 */
        root = tree[1];
        int q = nextInt();
        int i, j, k;
        VertexPair middleRight, leftMiddle, parts;
        for (int z = 0; z < q; z++) {
            i = nextInt() + 1;
            j = nextInt() + 1;
            k = nextInt() + 1;
            if (j - i + 1 == S_LENGTH) {
                continue;
            }
//            log("root.key = " + root.key);
//            log("j + 1 = " + (j + 1));
            middleRight = split(root, j + 1);
//            log("middleRight.left.key = " + (middleRight.left == null ? "null" : middleRight.left.key));
//            log("middleRight.left.size = " + (middleRight.left == null ? "null" : middleRight.left.size));
//            log("middleRight.right.key = " + (middleRight.right == null ? "null" : middleRight.right.key));
//            log("middleRight.right.size = " + (middleRight.right == null ? "null" : middleRight.right.size));
//            log("i = " + i);
            leftMiddle = split(middleRight.left, i);
//            log("leftMiddle.left.key = " + (leftMiddle.left == null ? "null" : leftMiddle.left.key));
//            log("leftMiddle.left.size = " + (leftMiddle.left == null ? "null" : leftMiddle.left.size));
//            log("leftMiddle.right.key = " + (leftMiddle.right == null ? "null" : leftMiddle.right.key));
//            log("leftMiddle.right.size = " + (leftMiddle.right == null ? "null" : leftMiddle.right.size));
            root = merge(leftMiddle.left, middleRight.right);
            parts = split(root, k);
            root = merge(merge(parts.left, leftMiddle.right), parts.right);
        }
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.key);
                root = root.right;
            }
        }
    }

    Main45() throws IOException {
//        testCaseBr = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/dpiramidin/Downloads/_8d00aadacb6828b0c5ca515d2f4bd036_Programming-Assignment-4/set_range_sum/tests/36.a")));
        // new FileInputStream("/Users/dpiramidin/Downloads/_8d00aadacb6828b0c5ca515d2f4bd036_Programming-Assignment-4/set_range_sum/tests/36")
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main45();
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
    String nextString() throws IOException {
        return nextToken();
    }

    private static void log(String msg)
    {
        if (logOn) {
            System.out.println(msg);
        }
    }
}
/*
hlelowrold
 */