package vladimir.chugunov.Stack;

import interfaces.stack.IStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 15:01 */
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

    public static void main(String[] args) {
        LinkedStack<Integer> bs;
        bs = new LinkedStack<>();

        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("Пихаем");
        for (int i = 0; i < 150; i++) {
            bs.push(i * 2);
            System.out.println(String.format("push(%s), size() = %s", i * 2, bs.size()));
        }

        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));


        System.out.println("\nЧистим");
        bs.clear();
        System.out.println(String.format("isEmpty() = %s, size() = %s", bs.isEmpty(), bs.size()));
        bs.clear();//no exception while clearing already cleared
        try {
            bs.pop();
        } catch (NullPointerException e) {
            System.out.println("Не могу доставать из пустого стека");
        }
        try {
            bs.peek();
        } catch (NullPointerException e) {
            System.out.println("Не могу заглядывать в пустой стек");
        }
        System.out.println(String.format("size() = %s", bs.size()));

        System.out.println("\nПихаем");
        for (int i = 0; i < 150; i++) {
            bs.push(i * 2);
            System.out.println(String.format("push(%s), size() = %s", i * 2, bs.size()));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("\nДостаем");
        for (int i = 0; i < 150; i++) {
            System.out.println(String.format("size() = %s, peek() = %s, pop() = %s", bs.size(), bs.peek(), bs.pop()));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));
        try {
            bs.pop();
        } catch (NullPointerException e) {
            System.out.println("Не могу доставать из пустого стека");
        }
        try {
            bs.peek();
        } catch (NullPointerException e) {
            System.out.println("Не могу заглядывать в пустой стек");
        }

    }

    @Override
    public void push(E element) {
        peak = new Node(element, peak);
        size++;
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
