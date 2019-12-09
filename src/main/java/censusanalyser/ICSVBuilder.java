package censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
    public <E> Iterator<E> getCsvFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
    }
