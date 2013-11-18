package vladimir.chugunov.Stack;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** User: Alpen Ditrix Date: 18.11.13 Time: 18:07 */
public class BoundedUnitTest {

    private final int               SIZE      = 155;
    @Rule
    public        ExpectedException exception = ExpectedException.none();
    private BoundedStack<Integer> stack;

    @Test
    public void negativeSize() {
        exception.expect(Throwable.class);
        System.out.println();
        new BoundedStack<>(-1);
    }

    @Test
    public void isEmptyTrueTest() {
        Assert.assertTrue("Новый стек должен являться пустым", new BoundedStack<>(0).isEmpty());
        Assert.assertTrue("Новый стек должен являться пустым", new BoundedStack<>(1).isEmpty());
    }

    @Test
    public void pushSomeValues() {
        try {
            stack = new BoundedStack<>(SIZE);
        } catch (Throwable throwable) {
            Assert.fail("Ошибка создания стека размера " + SIZE);
        }
        int i = 0;
        try {
            for (; i < SIZE; i++) {
                Assert.assertEquals("Метод push() должен возвращаеть ссылку на текущий стек", stack, stack.push(2 * i));
            }
        } catch (Throwable throwable) {
            Assert.fail(String.format("Ошибка наполнения стека на %s-м элементе из %s", i, SIZE));
        }
    }

    @Test
    public void overPush() {
        pushSomeValues();
        exception.expect(Throwable.class);
        stack.push(0);
    }

    @Test
    public void overPoll() {
        peekAndPopTest();
        exception.expect(Throwable.class);
        stack.pop();
    }

    @Test
    public void emptyPoll() {
        exception.expect(Throwable.class);
        new BoundedStack<>(SIZE).pop();
    }

    @Test
    public void additionalIsEmptyTest() {
        pushSomeValues();
        Assert.assertFalse("После добаления элемента стек не должен являться пустым", stack.isEmpty());
        Assert.assertFalse("После добаления элемента стек не должен являться пустым", new BoundedStack<>(1).push(0)
                                                                                                           .isEmpty());

        stack.clear();
        Assert.assertTrue("После очистки стек должен становиться пустым", stack.isEmpty());
    }

    @Test
    public void peekAndPopTest() {
        pushSomeValues();
        int i = SIZE - 1;
        try {
            for (; i >= 0; i--) {
                int peak = stack.peek();
                Assert.assertTrue("Метод peek() должен возвращать значение вершины стека", i * 2 == peak);
                Assert.assertTrue("Метод pop() должен возвращать значение вершины стека", i * 2 == stack.pop());
            }
        } catch (Throwable throwable) {
            Assert.fail("Ошибка при вытаскивании элементов из стека");
        }
    }

    @Test
    public void finalIsEmptyTest() {
        peekAndPopTest();
        Assert.assertTrue("После вытаскивания всех элементов стек должен быть пустым", stack.isEmpty());
    }

    @Test
    public void sizePushTest() {
        stack = new BoundedStack<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals("Размер должен соответствовать колчеству элементов в стеке", i, stack.size());
            stack.push(i);
        }
    }

    @Test
    public void sizePopPeekTest() {
        sizePushTest();
        for (int i = SIZE - 1; i >= 0; i--) {
            int size = stack.size();
            stack.peek();
            Assert.assertEquals("Размер стека не должен изменяться после вызова peek()", size, stack.size());
            stack.pop();
            Assert.assertEquals("Размер стека должен уменьшаться после вызовать pop()", size - 1, stack.size());
        }
    }
}
