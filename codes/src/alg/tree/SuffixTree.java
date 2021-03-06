package alg.tree;

/*
 * build Ukkonen: Time(n), Space(n)
 * build naive: Time(n^2), Space(n)
 * search pattern: Time(m), Space(1)
 * longest repeated substring: Time(n), Space(n)
 */
public class SuffixTree {
    public SuffixTree(String s) {
        // check null
        _str = s;
        _root = new Node(-1, new int[]{-1}, null);

        //buildNaive();
        buildUkkonen();
        setSuffixIndexDfs(_root, 0);
    }

    private void buildUkkonen() {
        int[] end = new int[]{0};

        Node activeNode = _root;
        int activeEdgePos = 0;
        int activeLength = 0;

        // remainingSuffixCount tells how many suffixes yet to be added in tree
        int remainingSuffixCount = 0;

        for (int i = 0; i < _str.length(); i++) {

            // Extension Rule 1, this takes care of extending all leaves created so far in tree
            end[0] = i;

            remainingSuffixCount++;
            Node lastNewNode = null; // used to follow suffix link

            while (remainingSuffixCount > 0) {
                if (activeLength == 0) {
                    activeEdgePos = i;
                }

                if (activeNode.children[sub(activeEdgePos)] == null) {
                    activeNode.children[sub(activeEdgePos)] = new Node(i, end, _root);

                    if (lastNewNode != null) {
                        lastNewNode.link = activeNode;
                        lastNewNode = null;
                    }
                } else {
                    Node next = activeNode.children[sub(activeEdgePos)];

                    // walk down
                    if (activeLength >= next.length()) {
                        activeNode = next;
                        activeEdgePos += next.length();
                        activeLength -= next.length();
                        continue;
                    }

                    // Extension Rule 3 (current character being processed is already on the edge)
                    if (_str.charAt(i) == _str.charAt(next.s + activeLength)) {
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
                    Node split = new Node(next.s, new int[]{next.s + activeLength - 1}, _root);
                    activeNode.children[sub(activeEdgePos)] = split;
                    split.children[sub(i)] = new Node(i, end, _root);
                    split.children[sub(next.s + activeLength)] = next;
                    next.s += activeLength;

                    if (lastNewNode != null) {
                        lastNewNode.link = split;
                    }
                    lastNewNode = split;
                }

                // One suffix got added in tree, decrement the count of suffixes yet to be added.
                remainingSuffixCount--;
                if (activeNode == _root) {
                    if (activeLength > 0) {
                        activeLength--;
                        activeEdgePos = i - remainingSuffixCount + 1;
                    }
                } else {
                    activeNode = activeNode.link;
                }
            }
        }
    }

    private void buildNaive() {
        for (int i = 0; i < _str.length(); i++) {
            Node n = _root;
            for (int j = i; j < _str.length(); ) {
                int c = _str.charAt(j) % 128;
                if (n.children[c] == null) {
                    n.children[c] = new Node(j, new int[]{_str.length()-1}, _root);
                    break;
                }

                Node parent = n;
                n = n.children[c];
                for (int cj = n.s; j < _str.length() && cj <= n.t[0]; j++, cj++) {
                    if (_str.charAt(j) != _str.charAt(cj)) {
                        Node d = new Node(n.s, new int[]{cj - 1}, _root);
                        parent.children[c] = d;
                        d.children[_str.charAt(cj) % 128] = n;
                        n.s = cj;
                        n = d;
                        break;
                    }
                }
            }
        }
    }

    public boolean search(String pattern) {
        Node n = _root;
        for (int i = 0; i < pattern.length(); ) {
            int subi = pattern.charAt(i) % 128;
            if (n.children[subi] == null) return false;

            n = n.children[subi];
            int ci = n.s;
            while (i < pattern.length() && ci <= n.t[0] && pattern.charAt(i) == _str.charAt(ci)) {
                i++; ci++;
            }

            if (i == pattern.length()) return true;
            if (ci <= n.t[0]) return false;
        }

        return false;
    }

    public String lrs() {
        return longestRepeatedSubstringDfs(_root);
    }

    private String longestRepeatedSubstringDfs(Node n) {
        if (n == null) return "";

        int children = 0;
        String max = "";
        for (int i = 0; i < 128; i++) {
            if (n.children[i] != null) {
                children++;
                String m = longestRepeatedSubstringDfs(n.children[i]);
                if (max.length() < m.length()) max = m;
            }
        }

        if (children >= 2) {
            return (n == _root ? "" : _str.substring(n.s, n.t[0] + 1)) + max;
        } else {
            return "";
        }
    }

    private void setSuffixIndexDfs(Node n, int labelLength) {
        if (n == null) return;

        boolean isLeaf = true;
        for (int i = 0; i < 128; i++) {
            if (n.children[i] != null) {
                isLeaf = false;
                setSuffixIndexDfs(n.children[i], labelLength + n.children[i].length());
            }
        }

        if (isLeaf) {
            n.index = _str.length() - labelLength;
        }
    }

    private int sub(int i) {
        return _str.charAt(i) % 128;
    }

    private String _str;
    private Node _root;

    private class Node {
        int s;
        int[] t; // int[1] for reference
        Node link; // suffix link
        int index = -1; // leaf node: index >= 0
        Node[] children = new Node[128];

        Node(int s, int[] t, Node link) {
            this.s = s;
            this.t = t;
            this.link = link;
        }

        int length() { return t[0] - s + 1; }
    }

    public  static void main(String[] args) {
        String s = "aa$";
        String p = "abx";
        System.out.println(new SuffixTree(s).search(p) == false);

        s = "GEEKSFORGEEKS$";
        System.out.println("GEEKS".equals(new SuffixTree(s).lrs()));

        s = "AAAAAAAAAA$";
        System.out.println("AAAAAAAAA".equals(new SuffixTree(s).lrs()));

        s = "ABCDEFG$";
        System.out.println("".equals(new SuffixTree(s).lrs()));

        s = "ABABABA$";
        System.out.println("ABABA".equals(new SuffixTree(s).lrs()));

        s = "ATCGATCGA$";
        System.out.println("ATCGA".equals(new SuffixTree(s).lrs()));

        s = "banana$";
        System.out.println("ana".equals(new SuffixTree(s).lrs()));
    }
}
