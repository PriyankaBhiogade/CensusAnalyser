package com.censusanalyser;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class CensusAnalyser {
    enum Country {
        INDIA, US, UK
    }

    enum CountryFields {
        STATE, POPULATION, TOTAL_AREA, POPULATION_DENSITY,POPULATION_THEN_DENSITY
    }

    private Country country;
    Map<CountryFields, Comparator<CensusDAO>> sortBy = null;
    Map<String, CensusDAO> censusStateMap = null;

    public CensusAnalyser() {
        this.sortBy = new HashMap<>();
        this.sortBy.put(CountryFields.STATE, Comparator.comparing(census -> census.state));
        this.sortBy.put(CountryFields.POPULATION, Comparator.comparing(census -> census.population, Comparator.reverseOrder()));
        this.sortBy.put(CountryFields.TOTAL_AREA, Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder()));
        this.sortBy.put(CountryFields.POPULATION_DENSITY, Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder()));
        Comparator<CensusDAO> comp = Comparator.comparing(censusDAO -> censusDAO.population,Comparator.reverseOrder());
        this.sortBy.put(CountryFields.POPULATION_THEN_DENSITY, comp.thenComparing(censusDAO -> censusDAO.populationDensity,Comparator.reverseOrder()));
    }

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        this.country = country;
        CensusAdapter censusAdapter = CensusAnalyserFactory.getCountry(country);
        censusStateMap = censusAdapter.loadCensusData(csvFilePath);
        return censusStateMap.size();
    }

    public String getSortData(CountryFields field) throws CensusAnalyserException {
        if (censusStateMap == null || censusStateMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data",
                    CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
            ArrayList censusDTOS = censusStateMap.values().stream()
                            .sorted(sortBy.get(field))
                            .map(censusDAO -> censusDAO.getCensusDTO(country))
                            .collect(toCollection(ArrayList::new));
            String sortedStateCensusJson = new Gson().toJson(censusDTOS);
            return sortedStateCensusJson;
        }
}
