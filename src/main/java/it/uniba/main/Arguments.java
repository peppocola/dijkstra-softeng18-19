
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
				type = values[1];
				break;
			case "limit":
				limit = Integer.parseInt(values[1]);
				break;
			default:
				throw new ParseException("unknown pattern: " + values[0]);
			}
		}
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







