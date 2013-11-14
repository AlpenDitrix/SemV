package vladimir.chugunov.binary_tree;

import java.util.ConcurrentModificationException;

/**
 * Concurrency-nonsafe binary tree
 *
 * User: Alpen Ditrix Date: 14.11.13 Time: 11:22 */
public class BinaryTree implements IBinaryTree {

    private int size = 0;

    /**
     * Basic storage element of tree
     *
     * @author Alpen Ditrix
     */
    private static class Node {
        Integer value;
        Node    left;
        Node    right;

        Node(Node l, Node r, Integer v) {
            value = v;
            left = l;
            right = r;
        }

        /**
         * Adds a child to this node with {@link #value} {@code == v}. If there was another child it
         * will be replaced
         *
         * @param v value should of creating child
         */
        void createChild(Integer v) {
            if (v == null || v < value) {
                left = new Node(null, null, v);
            } else {
                right = new Node(null, null, v);
            }
        }

        /**
         * {@inheritDoc}
         */
        public String toString() {
            return value == null ? "null" : value.toString();
        }

        /**
         * Removes leaf node which is child of this node
         *
         * @param node
         *            which to remove
         * @return value of removed node
         * @throws IllegalStateException
         *             if node intented to remove actually is not a leaf
         * @throws IllegalArgumentException
         *             if node intented to remove is not a child of this node
         *
         */
        Integer removeLeaf(Node node) {
            if (left != null && left.equals(node)) {

                if (left.left != null || left.right != null) {
                    throw new IllegalStateException("Wrong specified leaf");
                }

                Integer retVal = left.value;
                left = null;
                return retVal;
            } else if (right != null && right.equals(node)) {

                if (right.left != null || right.right != null) {
                    throw new IllegalStateException("Wrong specified leaf");
                }

                Integer retVal = right.value;
                right = null;
                return retVal;
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * Chached depth value
     */
    private int		depth;

    /**
     * Root of the node. There's no no node which has link on it
     */
    private Node	root;

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Integer value) {
        invalidateDepthCache();
        if (root == null) {
            root = new Node(null, null, value);
        } else {
            getNodeToAdd(value).createChild(value);
        }
        size++;
    }

    /**
     * Content of tree changed, hence previosly computed value is not valid
     */
    private void invalidateDepthCache() {
        depth = -1;
    }

    /**
     * @param value values which should be added
     * @return {@link Node}, which may be parent of new {@link Node} with {@link Node#value}
     *         {@code == value} (this param)
     */
    private Node getNodeToAdd(Integer value) {
        Node p1 = root;
        Node p2 = null;
        while (p1 != null) {
            p2 = p1;
            if (value == null || value < p1.value) {
                p1 = p1.left;
            } else {
                p1 = p1.right;
            }
        }
        return p2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Integer value) {
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            } else {
                node = (value == null || value < node.value) ? node.left : node.right;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Integer value) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                removeNode(node, parent);
                invalidateDepthCache();
                size--;
                return true;
            } else {
                parent = node;
                node = (value == null || value < node.value) ? node.left : node.right;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Some extraordinary magic
     *
     * @param node current node of search
     * @param parent parent of {@code node}
     * @return removed value
     */
    private Integer removeNode(Node node, Node parent) {
        if (node.left != null) {
            Node n = node.left;
            Node nParent = node;
            while (n.right != null) {
                nParent = n;
                n = n.right;
            }
            Integer retVal = node.value;
            node.value = removeNode(n, nParent);
            return retVal;
        } else if (node.right != null) {
            Node n = node.right;
            Node nParent = node;
            while (n.left != null) {
                nParent = n;
                n = n.left;
            }
            Integer retVal = node.value;
            node.value = removeNode(n, nParent);
            return retVal;
        } else if (parent == null) {
            if (node != root) {
                throw new IllegalArgumentException();
            }
            Integer retVal = root.value;
            root = null;
            return retVal;
        } else {
            return parent.removeLeaf(node);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int depth() {
        if (depth < 0) {
            depth = getDepthFrom(root);
        }
        return depth;
    }

    /**
     * @param n
     *            root of current subtree
     * @return depth of current subtree
     */
    public int getDepthFrom(Node n) {
        if (n == null) {
            return 0;
        }

        int l = getDepthFrom(n.left);
        int r = getDepthFrom(n.right);
        return Math.max(l, r) + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void depthFirstTraversal(IVisitor visitor) {
        visiting(root, visitor, 0);
    }

    @Override
    public void widthFirstTraversal(IVisitor visitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Visits every node in this tree int the natural order (low value first, high value last) and
     * acts on it.
     */
    public void sortedVisiting(IVisitor visitor) {
        visiting(root, visitor, 1);
    }

    /**
     * Visits every node in this tree (using DFS algorithm) and acts on it.
     *
     * @param n root of current subtree
     * @param when when to execute {@link IVisitor}
     */
    private void visiting(Node n, IVisitor visitor, int when) {
        if (when == 0) {
            visitor.visit(n);
        }
        if (n.left != null) {
            visiting(n.left, visitor, when);
        }
        if (when == 1) {
            visitor.visit(n);
        }
        if (n.right != null) {
            visiting(n.right, visitor, when);
        }
        if (when == 2) {
            visitor.visit(n);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        depth();// cache depth value
        printer(sb, root, 0);
        return sb.toString();
    }

    /**
     * Creates string which represents subtree of node {@code n} as {@link String}
     *
     * @param sb
     *            where to append element
     * @param n
     *            root of current subtree
     * @param currDepth current depth in tree
     */
    private void printer(StringBuilder sb, Node n, int currDepth) {
        if (depth == -1) {
            throw new ConcurrentModificationException();
        }

        if (n.left != null) {
            printer(sb, n.left, currDepth + 1);
        }

        for (int i = 0; i < 2 * (depth - currDepth - 1); i++) {
            sb.append(' ');
        }
        sb.append(n.value);
        sb.append("——\n");

        if (n.right != null) {
            printer(sb, n.right, currDepth + 1);
        }
    }

    public static void main(String[] args) {
    }

    public static final class Printer implements IVisitor {

        @Override
        public void visit(Object obj) {
            System.out.println(obj);
        }

    }

}
