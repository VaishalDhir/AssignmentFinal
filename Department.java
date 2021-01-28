package com.AssignmentFinall.server;

public class Department {

	private int DEPT_ID;
	private String NAME;
	public int getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(int dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Department [Department ID=" + DEPT_ID + ", Name=" + NAME + "]\n";
	}

}
