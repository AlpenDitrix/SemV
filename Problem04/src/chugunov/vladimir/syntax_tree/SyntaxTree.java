package chugunov.vladimir.syntax_tree;

public class SyntaxTree {
    private class Node {
        Object data;
        Node left;
        Node right;

        public Node(Object a, Node b, Node c) {
            data = a;
            left = b;
            right = c;
        }

        public String toString() {
            return data.toString();
        }
    }

    public SyntaxTree(String infix) {
        root = doSubtree(infix);
    }

    private Node doSubtree(String infix) {
        int index = infix.indexOf(sum);
        if (index < 0) {
            index = infix.indexOf(minus);
            if (index < 0) {
                index = infix.indexOf(mult);
                if (index < 0) {
                    index = infix.indexOf(div);
                    if (index < 0) {
                        return new Node(infix, null, null);
                    }
                }
            }
        }
        return new Node(infix.charAt(index), doSubtree(infix.substring(0, index)), doSubtree(infix.substring(
                index + 1, infix.length())));
    }

    private Node root = null;
    char mult = '*';
    char div = '/';
    char sum = '+';
    char minus = '-';

    public static void main(String[] args) {
        SyntaxTree t = new SyntaxTree("8+1*3-2+5/2");
    }
}
