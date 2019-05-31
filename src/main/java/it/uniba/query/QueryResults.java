package it.uniba.query;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The QueryResult class. This class creates a linked list that contains the
 * results of the query.
 * 
 * Entity
 */
public class QueryResults implements Iterable<String[]> {

	/**
	 * The list of results.
	 */
	private LinkedList<String[]> data;

	/**
	 * The list of columns' names.
	 */
	private List<String> columns;

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
	 * @param cols The columns' names.
	 */
	public void setColumns(final List<String> cols) {
		this.columns = cols;
	}

	/**
	 * returns columns.
	 *
	 * @return tag
	 */
	public List<String> getColumns() {
		return columns;
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
		StringBuffer result = new StringBuffer("");
		for (String[] row : data) {
			for (String cell : row) {
				result.append(cell + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}

	/**
	 * compares two QueryResults.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		QueryResults other;

		try {
			other = (QueryResults) obj;
		} catch (ClassCastException e) {
			return false;
		}

		for (int i = 0; i < data.size(); i++) {
			if (!Arrays.equals(data.get(i), other.data.get(i))) {
				return false;
			}
		}
		return columns.equals(other.columns);
	}

	/**
	 * hashCode not designed. DEPRECATED.
	 */
	@Override
	public int hashCode() {
		assert false : "hashCode not designed";
		return Integer.MAX_VALUE; // any arbitrary constant will do
	}

}
