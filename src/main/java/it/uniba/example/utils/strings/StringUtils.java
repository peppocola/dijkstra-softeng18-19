package it.uniba.example.utils.strings;

/**
 * This class is demo purpose only and must be deleted.
 */
public class StringUtils {

	public static Double convertToDouble(final String str) {
		if (str == null) {
			return null;
		}
		return Double.valueOf(str);
	}

	public static boolean isNullOrBlank(final String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String concat(final String... strings) {
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