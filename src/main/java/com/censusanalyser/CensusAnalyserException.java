package com.censusanalyser;

public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, NO_CENSUS_DATA, SOME_FILE_ISSUE, INVALID_COUNTRY, ERROR_FROM_CSV_BUILDER, SECOND_FILE_PATH_MISSING;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
