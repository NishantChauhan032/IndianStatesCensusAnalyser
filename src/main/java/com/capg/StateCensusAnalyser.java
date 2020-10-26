package com.capg;

import java.util.Arrays;
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

	public String getStateCodeWiseSortedData(String STATE_CODE_DATA) throws CensusAnalyserException {
		List<StateCodeData> stateCodeList = new StatesCSV().loadIndianStateCode(STATE_CODE_DATA);
		List<StateCodeData> sortedStateList = stateCodeList.stream()
								.sorted(Comparator.comparing(StateCodeData::getStateCode))
								.collect(Collectors.toList());
		String sortedStateCodeList = new Gson().toJson(sortedStateList);
		return sortedStateCodeList;
	}
	
	public String getPopulationWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new StateCensusCSV().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
								.sorted(Comparator.comparing(StateCensusData::getPopulation))
								.collect(Collectors.toList());
		int index = stateCensusList.size();
		StateCensusData[] stateCensusData = new StateCensusData[index];
		for(StateCensusData s : sortedStateCensusList)
			stateCensusData[--index] = s;
		String sortedStateList = new Gson().toJson(Arrays.asList(stateCensusData));
		return sortedStateList;
	}
	public String getPopulationDensityWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new StateCensusCSV().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
								.sorted(Comparator.comparing(StateCensusData::getDensityPerSqKm).reversed())
								.collect(Collectors.toList());
		String sortedStateList = new Gson().toJson(sortedStateCensusList);
		return sortedStateList;
	}

	public String getAreaWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new StateCensusCSV().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
				.sorted(Comparator.comparing(StateCensusData::getAreaInSqKm).reversed()).collect(Collectors.toList());
		return toJson(sortedStateCensusList);
	}

	public List<StateCensusData> loadIndianCensuaData(String PATH) throws CensusAnalyserException {
		return new StateCensusCSV().loadIndiaCensusData(PATH);
	}

	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}
}

