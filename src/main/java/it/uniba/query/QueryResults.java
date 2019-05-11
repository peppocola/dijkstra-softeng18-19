package it.uniba.query;

import java.util.Iterator;
import java.util.LinkedList;

public class QueryResults implements Iterable<String[]> {

	private LinkedList<String[]> data;

	public QueryResults() {
		data = new LinkedList<String[]>();
	}

	public void addTuple(final String[] tuple) {
		data.add(tuple);
	}

	@Override
	public Iterator<String[]> iterator() {
		return data.iterator();
	}

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
