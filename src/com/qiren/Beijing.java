package com.qiren;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-10-2012 13:30:47
 */
public class Beijing implements FiveOneInsuranceFund {

	private double endowmentInsurance;
	private double housingFund;
	private double medicalInsurance;
	private double unemploymentInsurance;
	private double ceil;

	public Beijing(){
		this.endowmentInsurance = 0.08;
		this.medicalInsurance = 0.02;
		this.unemploymentInsurance = 0.002;
		this.housingFund = 0.12;
		this.ceil = 12603;
	}

	public void finalize() throws Throwable {

	}

	public double calculate(double salary){
		double total = 0;
		if (salary < ceil)
			total = salary * (endowmentInsurance + medicalInsurance + housingFund + unemploymentInsurance);
		else 
			total = ceil * (endowmentInsurance + medicalInsurance + housingFund + unemploymentInsurance);
		return total;

	}

}