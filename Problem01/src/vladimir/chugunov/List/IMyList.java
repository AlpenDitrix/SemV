package vladimir.chugunov.List;

public interface IMyList<V extends Object> {

	/**
	 * Adds all values passed to the method to the list preserving the order
	 * @param values some objects to store
	 */
	public abstract void addItemsToEnd(Object... values);

	public abstract void addItemsToHead(Object... values);

	public abstract Object removeFirst();

	public abstract Object removeLast();
	
	public void iterator(Visitor visitor);

}