package com.AssignmentFinal.server;

import java.sql.Date;

public class Individual {
	
	private String BIRTH_DATE; 
	private String FIRST_NAME;
	private String LAST_NAME;
	private int CUST_ID;
	
	public String getBIRTH_DATE() {
		return BIRTH_DATE;
	}
	public void setBIRTH_DATE(String bIRTH_DATE) {
		BIRTH_DATE = bIRTH_DATE;
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getLAST_NAME() {
		return LAST_NAME;
	}
	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}
	public int getCUST_ID() {
		return CUST_ID;
	}
	public void setCUST_ID(int cUST_ID) {
		CUST_ID = cUST_ID;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Individual [Birth Date=" + BIRTH_DATE + ", FirstName=" + FIRST_NAME + ", LastName=" + LAST_NAME
				+ ", Customer id =" + CUST_ID + "]\n";
	}
		
}
