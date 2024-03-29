package com.censusanalyser;

public class CensusAnalyserFactory {

    public static CensusAdapter getCountry(CensusAnalyser.Country country) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaCensusAdapter();
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter();
        else
            throw new CensusAnalyserException("Incorrect Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
    }
}
