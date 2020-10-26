package com.capg;

import com.opencsv.bean.CsvBindByName;

public class StateCodeData {

	@CsvBindByName(column = "stateName", required = true)
	public String stateName;;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;
}
