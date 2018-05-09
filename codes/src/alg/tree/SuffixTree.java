package alg.tree;

public class SuffixTree {
    public SuffixTree(String s) {
        // check null
        _str = s;

        int[] end = new int[]{0};
        _root = new Node(-1, end);

        Node activeNode = _root;
        int activeEdge = -1;
        int activeLength = 0;

        // remainingSuffixCount tells how many suffixes yet to be added in tree
        int remainingSuffixCount = 0;

        for (int i = 0; i < s.length(); i++) {

            // Extension Rule 1, this takes care of extending all leaves created so far in tree
            end[0] = i;

            remainingSuffixCount++;
            Node lastNewNode = null;
            int c = s.charAt(i) % 128;

            while (remainingSuffixCount > 0) {
                if (activeLength == 0) {
                    activeEdge = i;
                }

                if (activeNode.children[c] == null) {
                    activeNode.children[c] = new Node(i, end);

                    // A new leaf edge is created in above line starting from an existing node (the current activeNode),
                    // and if there is any internal node waiting for it's suffix link get reset,
                    // point the suffix link from that last internal node to current activeNode.
                    // Then set lastNewNode to NULL indicating no more node waiting for suffix link reset.
                    if (lastNewNode != null) {
                        lastNewNode.link = activeNode;
                        lastNewNode = null;
                    }
                } else {
                    Node next = activeNode.children[c];

                    // walk down
                    if (activeLength >= next.length()) {
                        activeNode = next;
                        activeEdge += next.length();
                        activeLength -= next.length();
                        continue;
                    }

                    // Extension Rule 3 (current character being processed is already on the edge)
                    if (s.charAt(i) == s.charAt(next.s + activeLength)) {
                        // If a newly created node waiting for it's suffix link to be set,
                        // then set suffix link of that waiting node to current active node.
                        if (lastNewNode != null && activeNode != _root) {
                            lastNewNode.link = activeNode;
                            lastNewNode = null;
                        }

                        activeLength++;
                        break;
                    }

                    // Extension Rule 2, where a new leaf edge and a new internal node get created
                    Node n = new Node(next.s, new int[]{next.s + activeLength - 1});
                    activeNode.children[s.charAt(activeEdge) % 128] = n;
                    n.children[c] = new Node(i, end);
                    n.children[s.charAt(next.s) % 128] = next;
                    next.s += activeLength;

                    if (lastNewNode != null) {
                        lastNewNode.link = n;
                    }
                    lastNewNode = n;
                }

                // One suffix got added in tree, decrement the count of suffixes yet to be added.
                remainingSuffixCount--;
                if (activeNode == _root) {
                    if (activeLength > 0) {
                        activeLength--;
                        activeEdge = i - remainingSuffixCount + 1;
                    }
                } else {
                    activeNode = activeNode.link;
                }
            }
        }

        setSuffixIndex(_root, 0);
    }

    public int find(String pattern) {
        Node n = _root;
        for (int i = 0; i < pattern.length(); ) {
            int c = pattern.charAt(i) % 128;
            if (n.children[c] == null) return -1;

            n = n.children[c];
            int ci = n.s;
            while (i < pattern.length() && ci <= n.t[0] && pattern.charAt(i) == _str.charAt(ci)) {
                i++; ci++;
            }

            if (i == pattern.length()) return ci - pattern.length();
            if (ci <= n.t[0]) return -1;
        }

        return -1;
    }

    private void setSuffixIndex(Node n, int labelLength) {
        if (n == null) return;

        boolean isLeaf = true;
        for (int i = 0; i < 128; i++) {
            if (n.children[i] != null) {
                isLeaf = false;
                setSuffixIndex(n.children[i], labelLength + n.children[i].length());
            }
        }

        if (isLeaf) {
            n.index = _str.length() - labelLength;
        }
    }

    private String _str;
    private Node _root;

    private static class Node {
        int s;
        int[] t; // int[1] for reference
        int index = -1; // leaf node: index >= 0
        Node link;
        Node[] children = new Node[128];

        Node(int s, int[] t) {
            this.s = s;
            this.t = t;
        }

        int length() { return t[0] - s + 1; }
    }

    public  static void main(String[] args) {
        String s = "xabxac";
        String p = "ab";
        System.out.println(new SuffixTree(s).find(p) == 1);
    }
}
