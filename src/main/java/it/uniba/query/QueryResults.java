package it.uniba.query;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The QueryResult class.
 */
public class QueryResults implements Iterable<String[]> {

	/**
	 * The list of results.
	 */
	private LinkedList<String[]> data;

	/**
	 * The default QueryResult constructor.
	 */
	public QueryResults() {
		data = new LinkedList<String[]>();
	}

	/**
	 * @param tuple The result tuple.
	 */
	public void addTuple(final String[] tuple) {
		data.add(tuple);
	}

	/**
	 * The iterator for iterating on the elements of the list (arrays of strings).
	 */
	@Override
	public Iterator<String[]> iterator() {
		return data.iterator();
	}

	/**
	 * converts QueryResult to string.
	 */
	@Override
	public String toString() {
		String result = "";
		for (String[] row : data) {
			for (String cell : row) {
				result += cell + " ";
			}
			result += "\n";
		}
		return result;
	}
}
