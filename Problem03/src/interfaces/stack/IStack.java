package interfaces.stack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 14:08 */
public interface IStack<E> {

    /**
     * Кладет новый элемент на вершину стека
     * @param element что кладем
     */
    public void push(E element);

    /**
     * Убирает их стека верхний элемент
     * @return убранный элемент
     */
    public E pop();

    /**
     * Просто смотрим на вершину стека
     * @return вершина стека
     */
    public E peek();

    /**
     * Убираем из стека все элементы
     */
    public void clear();

    /**
     * @return true, если в стеке есть что-нибудь
     */
    public boolean isEmpty();

    /**
     * @return количество элементов в стеке
     */
    public int size();
}
