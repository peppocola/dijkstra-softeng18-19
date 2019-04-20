
package it.uniba.main;

/**
 * The input parameters class.
 */
public class Arguments {
	
	/**
	 * The year.
	 */
	private int year;
	
	/**
	 * The month.
	 */
	private int month;
	
	/**
	 * The day.
	 */
	private int day;
	
	/**
	 * The type (can be 'question', 'post' and 'answer').
	 */
	private String type;
	
	/**
	 * The tag string.
	 */
	private String taglike;
	
	/**
	 * The limit number to the query output.
	 */
	private long limit;
	
	/**
	 * The regular expression used to parse the arguments.
	 */
	private static final String REGEX = "="; 
	
	/**
	 * The Arguments constructor.
	 * @param args An array of argument strings
	 * @throws ParseException A parse exception
	 */
	public Arguments(String[] args) 
		throws ParseException
	{
		for (String str : args) {
			String[] values = str.split(REGEX);

			if(values.length != 2) {
				throw new ParseException("invalid assignment");
			}
			
			switch(values[0]) {
			case "yyyy":
				year = Integer.parseInt(values[1]);
				break;
			case "mm":
				month = Integer.parseInt(values[1]);
				break;
			case "dd":
				day = Integer.parseInt(values[1]);
				break;
			case "type":
				type = values[1];
				break;
			case "taglike":
				taglike = values[1];
				break;
			case "limit":
				limit = Integer.parseInt(values[1]);
				break;
			default:
				throw new ParseException("unknown pattern: " + values[0]);
			}
		}
	}
	
	/**
	 * Generates the query string.
	 * @return The query string
	 * @throws ArgumentException An invalid argument exception
	 */
	public String getQuery()
			throws ArgumentException
	{
		String query = "";
		
		if(type.equals("question") && taglike==null) {
			query="SELECT owner_user_id\r\n" + 
					"		FROM `bigquery-public-data.stackoverflow.posts_questions`\r\n" + 
					"		WHERE EXTRACT(YEAR FROM creation_date)="+year+" and EXTRACT(MONTH FROM creation_date)="+month+
					" 		and EXTRACT(DAY FROM creation_date)="+day+" and post_type_id=1 and owner_user_id is not null\r\n" + 
					"		order by owner_user_id\r\n" + 
					"		LIMIT "+limit+" ";
		} else {
			throw new ArgumentException("invalid argument "+type);
		}

		return query;
	}

	/**
	 * Get the year.
	 * @return The year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Get the month.
	 * @return The month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Get the day.
	 * @return The day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * Get the type.
	 * @return The type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Get the tag.
	 * @return The tag
	 */
	public String getTaglike() {
		return taglike;
	}
	
	/**
	 * Get the limit.
	 * @return The limit
	 */
	public long getLimit() {
		return limit;
	}
	
	/**
	 * Converts the arguments to a String.
	 * @return The string that rappresents the arguments
	 */
	public String toString() {
		String str = "";
		
		str = "yyyy=" + year + " mm=" + month + " dd=" + day +
				" type=" + type + " taglike=" + taglike + " limit=" + limit;
		
		return str;
	}
}







