package vladimir.chugunov.Tree;

public class BinaryTree<K extends Comparable<K>, V > {

	private static final class Node<K, V> {

		Entry<K, V> entry;
		Node<K, V> left;
		Node<K, V> right;

		public Node(Entry<K, V> e, Node<K, V> l, Node<K, V> r) {
			entry = e;
			left = l;
			right = r;
		}

		private void putDeeper(K key, V value) {
			int res = compare(key,entry.key);
			if(res<0) {
				if(left == null) {
					left = new Node(new Entry(key,value), null, null);
				} else {
					left.putDeeper(key, value);
				}
			} else if(res>0){
				if(right == null) {
					right = new Node(new Entry(key,value), null, null);
				} else {
					right.putDeeper(key, value);
				}
			} else {
				entry.value = value;
			}
		}

		private int compare(K value1, K value2) {
			return ((Comparable<K>)value1).compareTo(value2);
		}

	}

	private static final class Entry<K, V> {
		K key;
		V value;

		public Entry(K k, V v) {
			k = key;
			v = value;
		}
	}

	private Node<K, V> root;

	public BinaryTree() {
	}

	public void put(K key, V value) {
		Entry<K, V> e = new Entry<>(key, value);
		if (root == null) {
			root = new Node<>(e, null, null);
			return;
		}

		root.putDeeper(key, value);

	}

}
