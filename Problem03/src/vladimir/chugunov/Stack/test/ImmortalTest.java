package vladimir.chugunov.Stack.test;

import vladimir.chugunov.Stack.ImmortalStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 16:44 */
public class ImmortalTest {

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
                  .println(String.format("push(%s), size() = %s",
                                         i * 2, bs.size()));
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
                  .println(String.format("push(%s), size() = %s",
                                         i * 2, bs.size()));
        }
        System.out.println(String.format("isEmpty() = %s", bs.isEmpty()));

        System.out.println("\nДостаем");
        for (int i = 0; i < 150; i++) {
            System.out
                  .println(String.format("size() = %s, peek() = %s, pop() = %s", bs.size(),
                                         bs.peek(), bs
                          .pop()));
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
