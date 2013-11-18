import vladimir.chugunov.Stack.BoundedStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 16:44 */
public class BoundedTest {

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

        System.out
              .println(String.format("\nИзменение размера на %s. Успех: %s",
                                     bs.getBound() + 5, bs.checkAndSetNewCapacity(bs.getBound() + 5)));
        bs.push(0);
        System.out.println(String.format("push(%s), size() = %s", 0, bs.size()));
        bs.push(0);
        System.out.println(String.format("push(%s), size() = %s", 0, bs.size()));
        bs.push(0);
        System.out.println(String.format("push(%s), size() = %s", 0, bs.size()));
        bs.push(0);
        System.out.println(String.format("push(%s), size() = %s", 0, bs.size()));
        bs.push(0);
        System.out.println(String.format("push(%s), size() = %s", 0, bs.size()));

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

}
