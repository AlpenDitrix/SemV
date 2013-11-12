package interfaces.stack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 14:08 */
public interface IStack<E> {

    public void push(E element);

    public E pop();

    public E peek();

    public void clear();

    public boolean isEmpty();

    public int size();
}
