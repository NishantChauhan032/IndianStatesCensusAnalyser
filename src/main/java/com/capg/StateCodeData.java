package com.capg;

import com.opencsv.bean.CsvBindByName;

public class StateCodeData {

	@CsvBindByName(column = "stateName", required = true)
	public String stateName;;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
}
