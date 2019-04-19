
package it.uniba.main;

public class Arguments {
	
	private int year;
	
	private int month;
	
	private int day;
	
	private String type;
	
	private String taglike;
	
	private long limit;
	
	private static final String REGEX = "="; 
	
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
		}
		else {
			throw new ArgumentException("invalid argument "+type);
		}
		/*
		SELECT owner_user_id
		FROM `bigquery-public-data.stackoverflow.posts_questions`
		WHERE EXTRACT(YEAR FROM creation_date)=2016 and EXTRACT(MONTH FROM creation_date)=02 and EXTRACT(DAY FROM creation_date)=11 and post_type_id=1 and owner_user_id is not null
		order by owner_user_id
		LIMIT 100 
		*/
		return query;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public String getType() {
		return type;
	}
	
	public String getTaglike() {
		return taglike;
	}
	
	public long getLimit() {
		return limit;
	}
	
	public String toString() {
		String str = "";
		
		str = "yyyy=" + year + " mm=" + month + " dd=" + day +
				" type=" + type + " taglike=" + taglike + " limit=" + limit;
		
		return str;
	}
}







