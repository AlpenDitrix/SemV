import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alpen Ditrix Date: 14.01.14 Time: 10:48
 */
public class Table {

    private List<Entry> entries;

    private class Entry {
        private String key;
        private int    value;

        private Entry(String k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    public Table() {
        entries = new ArrayList<>();
    }

    public boolean add(String key, int value) {
        if (search(key) == null) {
            // add if not found
            entries.add(new Entry(key, value));
            return true;
        } else {
            // don't add if found that key
            // must use set(key, newValue)
            return false;
        }
    }

    public Integer search(String key) {
        for (Entry e : entries) {
            if (e.key.equals(key)) {
                return e.value; // found
            }
        }
        return null; //not found
    }

    public boolean set(String key, int newValue) {
        for(Entry e : entries) {
            //similar to search(key)
            if (e.key.equals((key))) {
                // does not return value, but chenges it and returns "success"
                e.value = newValue;
                return true;
            }
        }
        return false; // specified key not found
    }

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int a = 5;
        System.out.println(10000000+(Integer)Integer.class.getMethod("valueOf", String.class).invoke(null, "12312"));

    }

}
