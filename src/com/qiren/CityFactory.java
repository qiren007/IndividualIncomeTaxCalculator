package com.qiren;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-10-2012 13:30:49
 */
public class CityFactory {
	
	public CityFactory(){
		
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param city
	 */
	public FiveOneInsuranceFund createCity(String city){
		if ("广州".equals(city))
			return new Guangzhou();
		if ("深圳".equals(city))
			return new Shenzhen();
		if ("北京".equals(city))
			return new Beijing();
		if ("上海".equals(city))
			return new Shanghai();
		else
			return null;
	}

}