package vladimir.chugunov.Stack;

import interfaces.stack.IStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 15:06 */
public class ImmortalStack<E> implements IStack<E> {

    /** "Хранилище" стека */
    private Object[] elementData;
    /** Индекс вершины */
    private int      elementCount;
    /**
     * Коэффициент расширения массива. Когда текущий массив будет заполнен, в методе tryGrow() будет создан новый массив
     * со старыми данными, но размера, вычисленного по формуле {@code текущийРазмер * (1 + capacityIncrement}
     *
     * @see vladimir.chugunov.Stack.ImmortalStack#tryGrow()
     */
    private float    capacityIncrement;


    public ImmortalStack(int initialCapacity, float additionalCapacityIncrement) {
        elementData = new Object[initialCapacity];
        elementCount = -1;
        this.capacityIncrement = 1 + additionalCapacityIncrement;
    }

    public ImmortalStack(int initialCapacity) throws NegativeArraySizeException {
        this(initialCapacity, 0);
    }

    public ImmortalStack() {
        this(10);
    }

    public static void main(String[] args) {
        ImmortalStack<Integer> bs;
        try {
            bs = new ImmortalStack<>(-1);
        } catch (NegativeArraySizeException e) {
            System.out.println("Не могу создать стек отрицательного размера");
        }
        bs = new ImmortalStack<>();
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("Пихаем");
        for (int i = 0; i < 150; i++) {
            bs.push(i * 2);
            System.out
                  .println(String.format("push(%s), size() = %s, array.length = %s",
                                         i * 2, bs.size(), bs.elementData.length));
        }
//        try {
//            bs.push(0);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Не могу пихнуть в заполненный BoundedStack");
//        }
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
        for (int i = 0; i < 150; i++) {
            bs.push(i * 2);
            System.out
                  .println(String.format("push(%s), size() = %s, array.length = %s",
                                         i * 2, bs.size(), bs.elementData.length));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("\nДостаем");
        for (int i = 0; i < 150; i++) {
            System.out
                  .println(String.format("size() = %s, peek() = %s, pop() = %s, array.length = %s", bs.size(),
                                         bs.peek(), bs
                          .pop(), bs.elementData.length));
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

    /**
     * Проверяет не заполнился ли массив и расширяет его, если надо.
     *
     * @see ImmortalStack#capacityIncrement
     */
    private void tryGrow() {
        if (elementData.length != elementCount + 1) {
            return;
        } else {
            Object[] newArray = new Object[(int) ((elementCount + 1) * (1 + capacityIncrement))];
            System.arraycopy(elementData, 0, newArray, 0, elementCount + 1);
            elementData = newArray;
        }
    }

    /**
     * Проверяет не стало ли число элементов массива слишком мало, по сравнению с размером массива и уменьшает
     * "хранилище", если надо. Однако алгоритм никогда не создаст массив с длинной меньше 10
     *
     * Необходимым "магическим" условием для уменьшения "хранилища" считается "элементы занимают меньше четверти хранилища"
     */
    private void tryShrink() {
        if (elementData.length / 4 <= elementCount) {
            return;
        } else {
            if (elementData.length > 10) {
                Object[] newArray = new Object[Math.max(elementData.length / 2,10)];
                System.arraycopy(elementData, 0, newArray, 0, elementCount + 1);
                elementData = newArray;
            }
        }
    }

    @Override
    public void push(E element) throws ArrayIndexOutOfBoundsException {
        tryGrow();
        elementData[++elementCount] = element;
    }

    @Override
    public E pop() throws ArrayIndexOutOfBoundsException {
        E retVal = (E) elementData[elementCount];
        elementData[elementCount--] = null;
        tryShrink();
        return retVal;
    }

    @Override

    public E peek() throws ArrayStoreException {
        return (E) elementData[elementCount];
    }

    @Override
    public void clear() {
        elementData = new Object[10];
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

}
