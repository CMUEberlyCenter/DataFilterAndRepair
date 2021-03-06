package edu.cmu.eberly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.*;

/**
 * 
 */
public class RangeParser {

	/**
	 * @param text
	 * @return
	 */
	public static Boolean isValidIntRangeInput(String text) {
		Pattern re_valid = Pattern.compile("# Validate comma separated integers/integer ranges.\n"
		    + "^             # Anchor to start of string.         \n"
		    + "[0-9]+        # Integer of 1st value (required).   \n"
		    + "(?:           # Range for 1st value (optional).    \n"
		    + "  -           # Dash separates range integer.      \n"
		    + "  [0-9]+      # Range integer of 1st value.        \n"
		    + ")?            # Range for 1st value (optional).    \n"
		    + "(?:           # Zero or more additional values.    \n"
		    + "  ,           # Comma separates additional values. \n"
		    + "  [0-9]+      # Integer of extra value (required). \n"
		    + "  (?:         # Range for extra value (optional).  \n"
		    + "    -         # Dash separates range integer.      \n"
		    + "    [0-9]+    # Range integer of extra value.      \n"
		    + "  )?          # Range for extra value (optional).  \n"
		    + ")*            # Zero or more additional values.    \n"
		    + "$             # Anchor to end of string.           ", Pattern.COMMENTS);
		
		Matcher m = re_valid.matcher(text);
		
		if (m.matches())
			return true;
		else
			return false;
	}

	/**
	 * @param text
	 */
	public static void printIntRanges(String text) {
		Pattern re_next_val = Pattern.compile("# extract next integers/integer range value.    \n"
		    + "([0-9]+)      # $1: 1st integer (Base).         \n" 
				+ "(?:           # Range for value (optional).     \n"
		    + "  -           # Dash separates range integer.   \n" 
				+ "  ([0-9]+)    # $2: 2nd integer (Range)         \n"
		    + ")?            # Range for value (optional).     \n"
				+ "(?:,|$)       # End on comma or string end.",
		    Pattern.COMMENTS);
		Matcher m = re_next_val.matcher(text);
		String msg;
		int i = 0;
		while (m.find()) {
			msg = "  value[" + ++i + "] ibase=" + m.group(1);
			
			if (m.group(2) != null) {
				msg += " range=" + m.group(2);
			}
			
			System.out.println(msg);
		}
	}
	
	/**
	 * @param text
	 */
	public static ArrayList <Integer> parseIntRanges(String text) {
		//System.out.println(text);
		
		ArrayList <Integer> ranges=new ArrayList <Integer>();
		
		Pattern re_next_val = Pattern.compile("# extract next integers/integer range value.    \n"
		    + "([0-9]+)      # $1: 1st integer (Base).         \n" 
				+ "(?:           # Range for value (optional).     \n"
		    + "  -           # Dash separates range integer.   \n" 
				+ "  ([0-9]+)    # $2: 2nd integer (Range)         \n"
		    + ")?            # Range for value (optional).     \n"
				+ "(?:,|$)       # End on comma or string end.",
		    Pattern.COMMENTS);
		Matcher m = re_next_val.matcher(text);

		while (m.find()) {			
			if (m.group(2) != null) {
				Integer from=Integer.parseInt(m.group(1));
				Integer to=Integer.parseInt(m.group(2));
				
				//System.out.println("Generating numer list from " + from + " to " + to);
				
				for (int j=from;j<(to+1);j++) {
					if (ranges.contains(j)==false) {
					  ranges.add(j);
					}
				}
			} else {
				Integer anIndex=Integer.parseInt(m.group(1));
				if (ranges.contains(anIndex)==false) {
				  ranges.add(anIndex);
				}
			}			
		}
		
		Collections.sort (ranges);
		
		return(ranges);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = new String[] { 
		  // Valid inputs:
		  "1",
		  "1,2,3",
		  "1-9",
		  "1-9,10-19,20-199",
		  "1-8,9,10-18,19,20-199",
		  // Invalid inputs:
		  "A",
		  "1,2,",
		  "1 - 9",
		  " ",
		  "" 
		};

		// Loop through all test input strings:
		int i = 0;
		for (String s : arr) {
			String msg = "String[" + ++i + "] = \"" + s + "\" is ";
			
			if (isValidIntRangeInput(s)) {
				// Valid input line
				System.out.println(msg + "valid input. Parsing...");
				printIntRanges(s);
			} else {
				// Match attempt failed
				System.out.println(msg + "NOT valid input.");
			}
		}
	}
}