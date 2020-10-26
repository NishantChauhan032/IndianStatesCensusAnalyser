package com.capg;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		return new StateCensusCSV().loadIndiaCensusData(STATE_CSV_DATA).size();
	}

	public int loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException {
		return new StatesCSV().loadIndianStateCode(STATE_CODE_DATA).size();
	}

	public String getStateWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new StateCensusCSV().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
								.sorted(Comparator.comparing(StateCensusData::getState))
								.collect(Collectors.toList());
		String sortedStateList = new Gson().toJson(sortedStateCensusList);
		return sortedStateList;
	}
}

