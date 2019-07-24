package edu.cmu.eberly.filters.cell;

import org.apache.commons.cli.CommandLine;

/**
 * @author vvelsen
 */
public interface CellFilterInterface {
	
	/**
	 * 
	 * @param aValue
	 */
	public void setUseDebugging (Boolean aValue);
	
	/**
	 * @param aName
	 */
	public void setName (String aName);
	
	/**ß
	 * @return
	 */
	public String getName ();
	
	/**
	 * @param raw
	 * @return
	 */
  public String transform (String raw);

  /**
   * @param cmd
   */
	public void parseArgs(CommandLine cmd);
}
