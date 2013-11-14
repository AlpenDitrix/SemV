package vladimir.chugunov.hash_map_linking;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/** User: Alpen Ditrix Date: 14.11.13 Time: 20:53 */
public class IdTab {

    private Entry[] header;

    public IdTab() {
        header = new Entry[256];
    }

    private static class Entry {
        String id;
        Entry  next;

        Entry(String id, Entry next) {
            this.id = id;
            this.next = next;
        }

        String getId() {
            return id;
        }

        Entry getNext() {
            return next;
        }

        int getLength() {
            return id.length();
        }

        String hitch(String s, boolean add) {
            if (getLength() == s.length() && id.equals(s)) {
                return id;
            }

            if (next == null) {
                if (add) {
                    next = new Entry(s, null);
                }
                return null;
            } else {
                return next.hitch(s, add);
            }
        }

        @Override
        public String toString() {
            return next != null ? id.concat(", ").concat(next.toString()) : id;
        }
    }

    private static String getRandomString() {
        Random r = new Random();
        byte[] bytes = new byte[10];
        r.nextBytes(bytes);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private String searchOrAdd(String s, boolean add) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        int hash = hash(s);
        if (header[hash] == null) {
            if (add) {
                header[hash] = new Entry(s, null);
            }
            return null;
        } else {
            return header[hash].hitch(s, add);
        }
    }

    public String add(String s) {
        return searchOrAdd(s, true);
    }

    public boolean searchFor(String s) {
        if (s != null) {
            return searchOrAdd(s, false) != null;
        } else {
            return false;
        }
    }

    public int hash(String s) {
        byte[] bb = s.getBytes();
        int hash = 0;
        for (byte b : bb) {
            hash += Math.abs(b);
        }
        return hash % 255;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (Entry e : header) {
            if (e != null) {
                sb.append("[");
                sb.append(e.toString());
                sb.append("], ");
            }
        }
        sb.replace(sb.length() - 2, sb.length() - 1, ")");
        return sb.toString();
    }
}
