package com.AssignmentFinall.server;

public class Bussiness {

	private String INCORP_DATE; 
	private String NAME;
	private String STATE_ID;
	private int CUST_ID;
	public String getINCORP_DATE() {
		return INCORP_DATE;
	}
	public void setINCORP_DATE(String iNCORP_DATE) {
		INCORP_DATE = iNCORP_DATE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSTATE_ID() {
		return STATE_ID;
	}
	public void setSTATE_ID(String sTATE_ID) {
		STATE_ID = sTATE_ID;
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
		return "Bussiness [Incorp Date=" + INCORP_DATE + ", Name=" + NAME + ", State ID=" + STATE_ID
				+ ", Customer id =" + CUST_ID + "]\n";
	}


}
