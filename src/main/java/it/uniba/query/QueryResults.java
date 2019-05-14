package it.uniba.query;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The QueryResult class.
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
	 * @param columns The columns' names.
	 */
	public void setColumns(final List<String> columns) {
		this.columns = columns;
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
