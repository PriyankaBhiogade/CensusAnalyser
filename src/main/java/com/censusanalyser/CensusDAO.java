package com.censusanalyser;

public class CensusDAO {
    public String state;
    public String stateCode;
    public int population;
    public double populationDensity;
    public double totalArea;


    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(USCensusCode usCensusCSv) {
        state = usCensusCSv.state;
        stateCode = usCensusCSv.stateId;
        totalArea = usCensusCSv.totalArea;
        populationDensity = usCensusCSv.populationDensity;
        population = usCensusCSv.population;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if(country.equals(CensusAnalyser.Country.US))
            return new USCensusCode(state,stateCode,population,populationDensity,totalArea);
        return new IndiaCensusCSV(state,population,(int)populationDensity,(int)totalArea);
    }
}
