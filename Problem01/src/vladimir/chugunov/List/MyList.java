package vladimir.chugunov.List;



public class MyList<V> {

	private int size;

	/**
	 * An item of the list
	 * 
	 * @author Alpen Ditrix
	 * 
	 */
	private static class ListItem<V> {
		V value;
		ListItem<V> next;

		private void setAll(V value, ListItem<V> next) {
			this.value = value;
			this.next = next;
		}

		public ListItem(V value) {
			setAll(value, null);
		}

		public ListItem(V value, ListItem<V> next) {
			setAll(value, next);
		}

		public ListItem() {
			setAll((V) null, null);
		}

		public String toString() {
			return value.toString();
		}
	}

	/**
	 * Item which only stores link to the first item
	 */
	private ListItem<V> headItem;

	/**
	 * That's the last item of list. His {@link ListItem#next} is always equals
	 * {@code null}
	 */
	private ListItem<V> lastItem;

	/**
	 * @return first item of list
	 */
	public ListItem<V> getFirst() {
		return headItem.next;
	}

	/**
	 * @return {@link #lastItem}
	 */
	public ListItem<V> getLast() {
		return lastItem;
	}

	public void addItemsToEnd(Object... values) {
		if(values.length == 0) {
			return;
		}
		for (Object v : values) {
			ListItem<V> newItem = new ListItem<V>((V) v);
			lastItem.next = newItem;
			lastItem = newItem;
			size++;
		}
	}

	public void addItemsToHead(Object... values) {
		for (int i = values.length - 1; i >= 0; i--) {
			// set before first
			headItem.next = new ListItem<V>((V) values[i], getFirst());
			size++;
		}
	}

	public V removeFirst() {
		V tmp = getFirst().value;
		headItem.next = getFirst().next;
		size--;
		return tmp;
	}

	public V removeLast() {
		if (getFirst().next == null) {
			return removeFirst();
		}

		ListItem<V> runnerFirst = getFirst().next;
		ListItem<V> runnerSecond = getFirst();

		while (runnerFirst.next != null) {
			runnerFirst = runnerFirst.next;
			runnerSecond = runnerSecond.next;
		}
		runnerSecond.next = null;
		size--;
		return runnerFirst.value;
	}

	public MyList() {
		headItem = new ListItem<V>();
		lastItem = headItem;
	}

	public static class Counter implements Visitor {
		public int count = 0;

		public void visit(Object item) {
			count++;
		}

		public int getAmount() {
			return count;
		}
	}

	public void iterator(Visitor visitor) {
		for (ListItem<V> cur = getFirst(); cur != null; cur = cur.next) {
			visitor.visit(cur.value);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListItem<V> item = getFirst();
		sb.append("(");
		while (item.next != null) {
			sb.append(item.toString());
			sb.append(", ");
			item = item.next;
		}
		sb.append(item.toString());
		sb.append(")");
		return sb.toString();
	}

	public static void main(String[] args) {
		MyList<Integer> list = new MyList<Integer>();
		System.out.println(list.size());
		list.addItemsToEnd(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println(list);
		System.out.println(list.size());
		list.removeFirst();
		System.out.println(list);
		System.out.println(list.size());
		list.removeLast();
		System.out.println(list);
		System.out.println(list.size());
		list.addItemsToHead(9, 8, 7, 6, 5, 4, 3, 2, 1);
		System.out.println(list);
		System.out.println(list.size());
		list.removeFirst();
		System.out.println(list);
		System.out.println(list.size());
		list.removeLast();
		System.out.println(list);
		System.out.println(list.size());

		MyList<Integer> list2 = new MyList<>();
		System.out.println(list2.size());
		list2.addItemsToEnd(1, 2, 3);
		System.out.println(list);
		System.out.println(list2);
		System.out.println(list.size());
		System.out.println(list2.size());

	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return headItem.next == null;
	}

	public boolean contains(Object o) {
		return indexOf(o) > -1;
	}

	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (ListItem<V> x = getFirst(); x != null; x = x.next) {
			result[i++] = x.value;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass()
					.getComponentType(), size);
		int i = 0;
		Object[] result = a;
		for (ListItem<V> x = getFirst(); x != null; x = x.next)
			result[i++] = x.value;

		if (a.length > size)
			a[size] = null;
		return a;
	}

	public void clear() {
		headItem.next = null;
		size = 0;
	}

	public V set(int index, V element) {
		checkIndex(index);
		ListItem<V> item = getFirst();
		for (int i = 0; i < index; i++) {
			item = item.next;
		}
		V old = item.value;
		item.value = element;
		return old;
	}

	private void checkIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException(String.format(
					"Index: %s Size: %s", index, size));
		}
	}

	public V remove(int index) {
		checkIndex(index);
		if (index == size - 1) {
			return removeLast();
		} else {
			ListItem<V> item = getFirst();
			for (int i = 0; i < index; i++) {
				item = item.next;
			}
			V old = item.value;
			item.next = item.next.next;
			return old;
		}
	}

	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
			for (ListItem<V> x = getFirst(); x.next != null; x = x.next) {
				if (x.value == null) {
					return index;
				} else {
					index++;
				}
			}
			return -1;
		} else {
			for (ListItem<V> x = getFirst(); x.next != null; x = x.next) {
				if (o.equals(x.value)) {
					return index;
				} else {
					index++;
				}
			}
			return -1;

		}
	}
}
