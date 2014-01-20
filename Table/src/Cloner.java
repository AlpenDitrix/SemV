import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Cloner {

    public static Object clone(Object a){
        Class c = a.getClass();
        Object b = null;
        Constructor[] ctors = c.getConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }

        try {
            ctor.setAccessible(true);
            b = ctor.newInstance();
            // production code should handle these exceptions more gracefully
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }

        Field[] fields = c.getFields();
        for (Field field: fields){
            field.setAccessible(true);
            try {
                field.set(b, field.get(a));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return b;
    }


    public static void main(String[] args) {
        Integer a = new Integer("");
        Integer b = (Integer) clone(a);
        System.out.println(b);
    }

}
