package com.qiren;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-10-2012 13:30:54
 */
public class IndividualIncomeTax {

	private double fiveOneInsuranceFund;
	private double[] level = {0, 1500, 4500, 9000, 35000, 55000, 80000};
	private double[] quickDeduction = {0, 105, 555, 1005, 2755, 5505, 13505};
	private double salaryAfterTax;
	private double tax;
	private double[] taxRate = {0.03, 0.1, 0.2, 0.25, 0.3, 0.35, 0.45};
	private double threshold;
	private Map<String, ArrayList<Double>> eachTax;

	public IndividualIncomeTax(){
		this.fiveOneInsuranceFund = 0;
		this.salaryAfterTax = 0;
		this.tax = 0;
		this.threshold = 3500;
		eachTax = new HashMap<String, ArrayList<Double>>();
		eachTax.clear();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param salary
	 * @param city
	 */
	public void calculate(double salary, String city){
		double salaryCal = salary;
		CityFactory concreteCity = new CityFactory();
		this.fiveOneInsuranceFund = concreteCity.createCity(city).calculate(salary);
		salaryCal = salaryCal - this.threshold - this.fiveOneInsuranceFund;
		for (int i = taxRate.length - 1; i >= 0; i--) {
			if (salaryCal > this.level[i]) {
				this.tax = salaryCal * this.taxRate[i] - this.quickDeduction[i];
				break;
			}
		}
		this.salaryAfterTax = salary - this.fiveOneInsuranceFund - this.tax;

	}
	
	

	public void calculateEachIncome(String date, double salary){
		ArrayList<Double> tmp = new ArrayList<Double>();
		double salaryCal = salary;
		salaryCal = salaryCal - this.threshold;
		if (!eachTax.containsKey(date)) eachTax.put(date, tmp);
		if (salaryCal <= 0) {
			eachTax.get(date).add(salary); 
			eachTax.get(date).add(0.0);
		}
		
		for (int i = taxRate.length - 1; i >= 0; i--) {
			if (salaryCal > this.level[i]) {
				if (eachTax.get(date).isEmpty()) {
					eachTax.get(date).add(salary);
					eachTax.get(date).add(salaryCal * this.taxRate[i] - this.quickDeduction[i]);
				} else {
					double sum = 0;
					
					for (int j = 1; j < eachTax.get(date).size(); j++) {
						sum += eachTax.get(date).get(j);
					}
					eachTax.get(date).set(0, salary);
					eachTax.get(date).add(salaryCal * this.taxRate[i] - this.quickDeduction[i] - sum);
				}
				break;
			}
		}
	}
	

	public Map<String, ArrayList<Double>> getEachTax() {
		return eachTax;
	}

	public void clearEachTax() {
		this.eachTax.clear();
	}

	public double getFiveOneInsuranceFund(){
		return this.fiveOneInsuranceFund;
	}

	public double getSalaryAfterTax(){
		return this.salaryAfterTax;
	}

	public double getTax(){
		return this.tax;
	}

}