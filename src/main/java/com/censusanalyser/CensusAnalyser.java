package com.censusanalyser;

import com.google.gson.Gson;
import opencsvbuilder.CSVBuilderException;
import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.ICSVBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    enum Country{
        India,US
    }
    Map<String, CensusDAO> censusStateMap = null;

    public CensusAnalyser() {
    }
    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        censusStateMap = new CensusLoader().loadCensusData(country,csvFilePath);
        return censusStateMap.size();
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        this.checkValue();
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        return this.getSort(censusComparator);
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        this.checkValue();
        Comparator<CensusDAO> sortedPopulationCensusJson = Comparator.comparing(census -> census.population, Comparator.reverseOrder());
        return this.getSort(sortedPopulationCensusJson);
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        this.checkValue();
        Comparator<CensusDAO> sortedPopulationCensusJson = Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder());
        return this.getSort(sortedPopulationCensusJson);
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        this.checkValue();
        Comparator<CensusDAO> sortedPopulationCensusJson = Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder());
        return this.getSort(sortedPopulationCensusJson);
    }

    private void checkValue() throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    private String getSort(Comparator<CensusDAO> censusComparator) {
        List<CensusDAO> censusDAOS = censusStateMap.values().
                stream().collect(Collectors.toList());
        this.sort(censusDAOS, censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusDAOS);
        return sortedStateCensusJson;
    }

    private void sort(List<CensusDAO> censusDAOS, Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - 1; j++) {
                CensusDAO census1 = censusDAOS.get(j);
                CensusDAO census2 = censusDAOS.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusDAOS.set(j, census2);
                    censusDAOS.set(j + 1, census1);
                }
            }
        }
    }
}
