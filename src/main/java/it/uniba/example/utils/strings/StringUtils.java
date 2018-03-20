package it.uniba.example.utils.strings;

/**
 * This class and package are for demo purpose only and must be deleted.
 */
public class StringUtils {

	public static Double convertToDouble(String str) {
		if (str == null) {
			return null;
		}
		return Double.valueOf(str);
	}

	public static boolean isNullOrBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String concat(String... strings) {
		String concatStr = null;

		if (strings != null && strings.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : strings) {
				if (str != null) {
					sb.append(str);
				}
			}
			concatStr = sb.toString();
		}
		return concatStr;
	}

}