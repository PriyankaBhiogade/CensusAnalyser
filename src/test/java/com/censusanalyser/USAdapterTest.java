package com.censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class USAdapterTest {
    private static final String US_CENSUS_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData123.csv";
    private static final String DELIMITER_FILE_PATH = "./src/test/resources/DelimiterWrong.csv";
    private static final String HEADER_FILE_PATH = "./src/test/resources/HeaderMissing.csv";


    @Test
    public void givenUSCensusData_WhenCorrect_ShouldReturnMapOfRecordCount() throws CensusAnalyserException {
        USCensusAdapter usCensusAdapter = new USCensusAdapter();
        Map<String, CensusDAO> stringCensusDAOMap = usCensusAdapter.loadCensusData(US_CENSUS_FILE_PATH);
        Assert.assertEquals(51,stringCensusDAOMap.size());
    }

    @Test
    public void givenUSCensusData_WithWrongFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(DELIMITER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }
}
