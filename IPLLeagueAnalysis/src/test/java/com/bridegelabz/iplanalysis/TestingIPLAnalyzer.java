package com.bridegelabz.iplanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TestingIPLAnalyzer {

	public static String filePath = "D:\\BridgeLabz-Fellowship\\IPL\\IPLLeagueAnalysis\\resources\\MostRuns.csv";
    private IPLAnalyzer iplAnalyzer;

    @Before
    public void initialize() {
        iplAnalyzer = new IPLAnalyzer();
    }
    
	@Test
	public void givenCSVFilePath_ShouldReturnTopBattingAverage() throws IplAnalyzerException {
        iplAnalyzer.loadIplData(filePath);
        String sortedData = iplAnalyzer.sortBatsmanDataOnAverage();
        MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
        Assert.assertEquals("MS Dhoni", mostRunsCSV[0].playerName);
	}
	
	
}
