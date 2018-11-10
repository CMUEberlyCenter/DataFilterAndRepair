package edu.cmu.eberly.filters;

/**
 * @author vvelsen
 */
public class FilterBase implements DataFilterInterface {

	private String filterName="base";
	
	protected boolean useDebugging = false;

	/**
	 * @param aMessage
	 */
	protected void debug(String aMessage) {
		if (useDebugging == true) {
			System.out.println(aMessage);
		}
	}

	/**
	 * @param aMessage
	 */
	protected void warn(String aMessage) {
		System.out.println("Warning: " + aMessage);
	}
	
	/**
	 * 
	 * @param aValue
	 */
	public void setUseDebugging (Boolean aValue) {
		useDebugging=aValue;
	}
	
	/**
	 * 
	 */
	public void setName(String aName) {
		filterName=aName;
	}

	/**
	 * 
	 */
	public String getName() {
		return (filterName);
	}

	/**
	 * 
	 */
	public String transform(String raw) {
		return raw;
	}
}
