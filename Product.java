package com.AssignmentFinal.server;
import java.sql.Date;
public class Product {
	private String PRODUCT_CD; 
	private String DATE_OFFERED;
	private String DATE_RETIRED;
	private String NAME;
	private String PRODUCT_TYPE_CD;
	
	
	public String getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(String pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}
	public String getDATE_OFFERED() {
		return DATE_OFFERED;
	}
	public void setDATE_OFFERED(String dATE_OFFERED) {
		DATE_OFFERED = dATE_OFFERED;
	}
	public String getDATE_RETIRED() {
		return DATE_RETIRED;
	}
	public void setDATE_RETIRED(String dATE_RETIRED) {
		DATE_RETIRED = dATE_RETIRED;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPRODUCT_TYPE_CD() {
		return PRODUCT_TYPE_CD;
	}
	public void setPRODUCT_TYPE_CD(String pRODUCT_TYPE_CD) {
		PRODUCT_TYPE_CD = pRODUCT_TYPE_CD;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Product [Product CD Type=" + PRODUCT_CD + ", Date Offered=" + DATE_OFFERED + ", Date Retired=" + DATE_RETIRED
				+ ", Name =" + NAME + ",Product type CD=\" + PRODUCT_TYPE_CD + \"]\n";
	}
}
