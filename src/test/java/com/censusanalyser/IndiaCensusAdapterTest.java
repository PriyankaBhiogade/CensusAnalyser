package com.censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class IndiaCensusAdapterTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData123.csv";
    private static final String DELIMITER_FILE_PATH = "./src/test/resources/IndiaDelimiterWrong.csv";
    private static final String HEADER_FILE_PATH = "./src/test/resources/IndiaHeaderMissing.csv";
    private static final String US_CENSUS_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";

    @Test
    public void givenIndianCensusData_ShouldReturnMapOfCorrectRecord() throws CensusAnalyserException {
        IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
        Map<String, CensusDAO> censusDAOMap = indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, censusDAOMap.size());
    }

    @Test
    public void givenIndianCensusDataAndStateCodeData_WhenCorrectFile_ShouldReturnMapOfCorrectRecord() throws CensusAnalyserException {
        IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
        Map<String, CensusDAO> censusDAOMap = indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(29, censusDAOMap.size());
    }

    @Test
    public void givenIndianCensusDataAndStateCodeData_WhenSequenceWrong_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(INDIA_STATE_CODE_CSV_FILE_PATH, INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenIndianCensusDataAndUSCensusData_WhenSecondFileIsWrong_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, US_CENSUS_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
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
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(US_CENSUS_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(DELIMITER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithIncorrectHeader_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(HEADER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenFileEmpty_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(EMPTY_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }
}
