package vladimir.chugunov.binary_tree;

/** User: Alpen Ditrix Date: 14.11.13 Time: 11:23 */
public interface IBinaryTree {

    public void add(Integer value);

    public boolean contains(Integer value);

    public boolean remove(Integer value);

    public int size();

    public int depth();

    public static interface IVisitor {

        public void visit(Integer that);

    }

    public void depthFirstTraversal(IVisitor visitor);

    public void breadthFirstTraversal(IVisitor visitor);

}