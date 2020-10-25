package com.capg;


import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

	@CsvBindByName(column = "State")
	private String state;

	@CsvBindByName(column = "Population", required = true )
	private int population;

	@CsvBindByName(column = "AreaInSqKm")
	private int areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm")
	private int densityPerSqKm;
}
