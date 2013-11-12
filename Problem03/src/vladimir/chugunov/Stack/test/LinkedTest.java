package vladimir.chugunov.Stack.test;

import vladimir.chugunov.Stack.LinkedStack;

/** User: Alpen Ditrix Date: 12.11.13 Time: 16:45 */
public class LinkedTest {

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


}
