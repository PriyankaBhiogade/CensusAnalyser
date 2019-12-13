package com.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCode {

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    public USCensusCode() {
    }

    public USCensusCode(String state, String stateCode, int population, double populationDensity, double totalArea) {
        this.state = state;
        this.stateId = stateCode;
        this.population = population;
        this.totalArea = totalArea;
        this.populationDensity =populationDensity;
    }
}
