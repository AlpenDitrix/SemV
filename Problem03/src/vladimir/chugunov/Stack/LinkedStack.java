package vladimir.chugunov.Stack;

import interfaces.stack.IStack;

/**
 * Безразмерный стек. Не имеет локализованного хранилища.
 * <p/>
 * User: Alpen Ditrix Date: 12.11.13 Time: 15:01
 */
public class LinkedStack<E> implements IStack<E> {

    /** Вершина стека */
    private Node peak;
    /** Количество добавленных элементов */
    private int size = 0;

    public LinkedStack() {}

    /**
     * Представление элемента стека.
     * <p/>
     * Каждый элемент помнит что в нем хранится и что лежит в стеке "под ним"
     */
    private class Node {
        E    data;
        Node prev;

        /**
         * Создает новый элемент с определенными данными и лежащий на другом определенном элементе. Если prev == null,
         * считается, что под этим элементом ничего не лежит (это будет дном стека)
         *
         * @param data хранимые данные
         * @param prev над чем лежит этот элемент
         */
        private Node(E data, Node prev) {
            this.data = data;
            this.prev = prev;
        }
    }

    @Override
    public IStack<E> push(E element) {
        peak = new Node(element, peak);
        size++;
        return this;
    }

    @Override
    public E pop() {
        E retVal = peak.data;
        peak = peak.prev;
        size--;
        return retVal;
    }

    @Override
    public E peek() {
        return peak.data;
    }

    @Override
    public void clear() {
        peak = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return peak == null;
    }

    @Override
    public int size() {
        return size;
    }

}
