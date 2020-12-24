package com.AssignmentFinal.server;
import java.sql.Date;

public class Officer {
	private String OFFICER_ID; 
	private String END_DATE;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String START_DATE;
	private String TITLE;
	private int CUST_ID;
	public String getOFFICER_ID() {
		return OFFICER_ID;
	}
	public void setOFFICER_ID(String oFFICER_ID) {
		OFFICER_ID = oFFICER_ID;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eND_DATE) {
		END_DATE = eND_DATE;
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
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
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
		return "Officer [Officer ID=" + OFFICER_ID + ", End_date=" + END_DATE + ", FirstName=" + FIRST_NAME
				+ ", LastName=\" + LAST_NAME + \", Start Date=\" + START_DATE + \", Title=\" + TITLE + \", Customer id =" + CUST_ID + "]\n";
	}
}
