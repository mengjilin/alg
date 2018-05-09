package alg.tree;

public class SuffixTree {
    public SuffixTree(String s) {
        // check null
        _str = s;
        _root = new Node();

        for (int i = 0; i < s.length(); i++) {
            Node n = _root;
            for (int j = i; j < s.length(); ) {
                int c = s.charAt(j) % 128;
                if (n.children[c] == null) {
                    n.children[c] = new Node(j, s.length()-1);
                    n.children[c].hasLeaf = true;
                    break;
                }

                Node parent = n;
                n = n.children[c];
                int cj = n.s;
                while (j < s.length() && cj <= n.t && s.charAt(j) == s.charAt(cj)) {
                    j++; cj++;
                }

                if (cj <= n.t) {
                    Node d = new Node(n.s, cj - 1);
                    d.hasLeaf = j == s.length();
                    parent.children[c] = d;
                    d.children[s.charAt(cj) % 128] = n;
                    n.s = cj;
                    n = d;
                } else if (j == s.length()) {
                    n.hasLeaf = true;
                }
            }
        }
    }

    public int find(String pattern) {
        Node n = _root;
        for (int j = 0; j < pattern.length(); ) {
            int c = pattern.charAt(j) % 128;
            if (n.children[c] == null) return -1;

            n = n.children[c];
            int cj = n.s;
            while (j < pattern.length() && cj <= n.t && pattern.charAt(j) == _str.charAt(cj)) {
                j++; cj++;
            }

            if (j == pattern.length()) return cj - pattern.length();
            if (cj <= n.t) return -1;
        }

        return -1;
    }

    private String _str;
    private Node _root;

    private static class Node {
        Node(){}
        Node(int s, int t) {this.s = s; this.t = t;}
        int s, t;
        boolean hasLeaf;
        Node[] children = new Node[128];
    }

    public  static void main(String[] args) {
        String s = "xabxac";
        String p = "ab";
        System.out.println(new SuffixTree(s).find(p) == 1);
    }
}
