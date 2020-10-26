package com.capg;

public class StateCensusAnalyser 
{

	public StateCensusAnalyser() {}

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		return new StateCensusCSV().loadIndiaCensusData(STATE_CSV_DATA);
	}
	public int loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException{
		return new StatesCSV().loadIndianStateCode(STATE_CODE_DATA);
	}
	public static void main(String[] args) {
		System.out.println("Welcome to Indian States Census Analyser Program");
	}
}
