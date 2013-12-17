import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import vladimir.chugunov.Stack.BoundedStack;

/** User: Alpen Ditrix Date: 18.11.13 Time: 18:07 */
public class BoundedUnitTest {

    public static final String PUSH_MUST_RETURN_SELFLINK = "Метод push() должен возвращаеть ссылку на текущий стек";
    public static final String PUSH_ERROR_AT             = "Ошибка наполнения стека на %s-м элементе из %s";
    public static final String STACK_MUST_NOT_BE_EMPTY   = "После добаления элемента стек не должен являться пустым";
    public static final String PEEK_MUST_RETURN_PEAK     = "Метод peek() должен возвращать значение вершины стека";
    public static final String POP_MUST_RETURN_PEAK      = "Метод pop() должен возвращать значение вершины стека";
    public static final String POLL_ERROR_AT             = "Ошибка при вытаскивании из стека %s-го элемента из %s";
    public static final String STACK_SHOULD_BECOME_EMPTY = "После вытаскивания всех элементов стек должен быть пустым";
    public static final String SIZE_MUST_BE_PROPER       = "Размер должен соответствовать колчеству элементов в стеке";
    public static final String PEEK_DO_NOT_CHANGE_SIZE   = "Размер стека не должен изменяться после вызова peek()";
    public static final String STACK_SHOULD_DECREASE_SIZE = "Размер стека должен уменьшаться после вызовать pop()";
    private final int               SIZE      = 155;
    @Rule
    public        ExpectedException exception = ExpectedException.none();
    private BoundedStack<Integer> stack;

    public static final String MUST_BE_EMPTY               = "Новый стек должен являться пустым";
    public static final String CREATION_NEW_INSTANCE_ERROR = "Ошибка создания стека размера ";


    @Test
    public void negativeSize() {
        exception.expect(Throwable.class);
        System.out.println();
        new BoundedStack<>(-1);
    }

    @Test
    public void isEmptyTrueTest() {

        Assert.assertTrue(MUST_BE_EMPTY, new BoundedStack<>(0).isEmpty());
        Assert.assertTrue(MUST_BE_EMPTY, new BoundedStack<>(1).isEmpty());
    }

    @Test
    public void pushSomeValues() {
        try {
            stack = new BoundedStack<>(SIZE);
        } catch (Throwable throwable) {

            Assert.fail(CREATION_NEW_INSTANCE_ERROR + SIZE);
        }
        int i = 0;
        try {
            for (; i < SIZE; i++) {
                Assert.assertEquals(PUSH_MUST_RETURN_SELFLINK, stack, stack.push(2 * i));
            }
        } catch (Throwable throwable) {
            Assert.fail(String.format(PUSH_ERROR_AT, i, SIZE));
        }
    }

    @Test
    public void additionalIsEmptyTest() {
        pushSomeValues();
        Assert.assertFalse(STACK_MUST_NOT_BE_EMPTY, stack.isEmpty());
        Assert.assertFalse(STACK_MUST_NOT_BE_EMPTY, new BoundedStack<>(1).push(0)
                                                                                                           .isEmpty());

        stack.clear();
        Assert.assertTrue(STACK_SHOULD_BECOME_EMPTY, stack.isEmpty());
    }

    @Test
    public void peekAndPopTest() {
        pushSomeValues();
        int i = SIZE - 1;
        try {
            for (; i >= 0; i--) {
                int peak = stack.peek();
                Assert.assertTrue(PEEK_MUST_RETURN_PEAK, i * 2 == peak);
                Assert.assertTrue(POP_MUST_RETURN_PEAK, i * 2 == stack.pop());
            }
        } catch (Throwable throwable) {

            Assert.fail(String.format(POLL_ERROR_AT,i,SIZE));
        }
    }

    @Test
    public void finalIsEmptyTest() {
        peekAndPopTest();
        Assert.assertTrue(STACK_SHOULD_BECOME_EMPTY, stack.isEmpty());
    }

    @Test
    public void sizePushTest() {
        stack = new BoundedStack<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            Assert.assertEquals(SIZE_MUST_BE_PROPER, i, stack.size());
            stack.push(i);
        }
    }

    @Test
    public void sizePopPeekTest() {
        sizePushTest();
        for (int i = SIZE - 1; i >= 0; i--) {
            int size = stack.size();
            stack.peek();
            Assert.assertEquals(PEEK_DO_NOT_CHANGE_SIZE, size, stack.size());
            stack.pop();
            Assert.assertEquals(STACK_SHOULD_DECREASE_SIZE, size - 1, stack.size());
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
}
