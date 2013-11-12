package vladimir.chugunov.Stack;

import interfaces.stack.IStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 14:10 */
public class BoundedStack<E> implements IStack<E> {

    /** "Хранилище" стека */
    private Object[] elementData;
    /** Индекс вершины стека */
    private int      elementCount;


    public BoundedStack(int initialCapacity) throws NegativeArraySizeException {
        elementData = new Object[initialCapacity];
        elementCount = -1;
    }

    public static void main(String[] args) {
        BoundedStack<Integer> bs;
        try {
            bs = new BoundedStack<>(-1);
        } catch (NegativeArraySizeException e) {
            System.out.println("Не могу создать стек отрицательного размера");
        }
        bs = new BoundedStack<>(15);
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("Пихаем");
        for (int i = 0; i < bs.getBound(); i++) {
            bs.push(i * 2);
            System.out.println(String.format("push(%s), size() = %s", i * 2, bs.size()));
        }
        try {
            bs.push(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не могу пихнуть в заполненный BoundedStack");
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));


        System.out.println("\nЧистим");
        bs.clear();
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));
        bs.clear();//no exception while clearing already cleared
        try {
            bs.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не могу доставать из пустого стека");
        }
        try {
            bs.peek();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не могу заглядывать в пустой стек");
        }
        System.out.println(String.format("size() = %s", bs.size()));

        System.out.println("\nПихаем");
        for (int i = 0; i < bs.getBound(); i++) {
            bs.push(i * 2);
            System.out.println(String.format("push(%s), size() = %s", i * 2, bs.size()));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("\nДостаем");
        for (int i = 0; i < bs.getBound(); i++) {
            System.out.println(String.format("size() = %s, peek() = %s, pop() = %s", bs.size(), bs.peek(), bs.pop()));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));
        try {
            bs.pop();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не могу доставать из пустого стека");
        }
        try {
            bs.peek();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Не могу заглядывать в пустой стек");
        }

    }

    @Override
    public void push(E element) throws ArrayIndexOutOfBoundsException {
        elementData[++elementCount] = element;
    }

    @Override
    public E pop() throws ArrayIndexOutOfBoundsException {
        E retVal = (E) elementData[elementCount];
        elementData[elementCount--] = null;
        return retVal;
    }

    @Override
    public E peek() throws ArrayStoreException {
        return (E) elementData[elementCount];
    }

    @Override
    public void clear() {
        //helps GC
        for (int i = 0; i < elementCount; i++) {
            elementData[i] = null;
        }

        elementCount = -1;
    }

    @Override
    public boolean isEmpty() {
        return elementCount == -1;
    }

    @Override
    public int size() {
        return elementCount + 1;
    }

    public int getBound() {
        return elementData.length;
    }
}
