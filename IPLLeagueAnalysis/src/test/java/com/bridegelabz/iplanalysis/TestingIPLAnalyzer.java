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

	// test to check the batsman with top batting average
	@Test
	public void givenCSVFilePath_ShouldReturnTopBattingAverage() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnAverage();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("MS Dhoni", mostRunsCSV[0].playerName);
		Assert.assertEquals("83.2", mostRunsCSV[0].average);
	}

	// test to check the batsman with top strike rate
	@Test
	public void givenCSVFilePath_ShouldReturnTopStrikeRate() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnStrikeRate();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Ishant Sharma", mostRunsCSV[0].playerName);
		Assert.assertEquals("333.33", mostRunsCSV[0].strikeRate);
	}

	// test to check the batsman with most boundaries
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithMostSixesNFours() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnBoundaries();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Andre Russell", mostRunsCSV[0].playerName);
		Assert.assertEquals(83, mostRunsCSV[0].foursCollected + mostRunsCSV[0].sixesCollected);
	}
	
	// test to check the batsman with  Strike rate and then most boundaries
		@Test
		public void givenCSVFilePath_ShouldReturnPlayerWithBestStrikeAndBoundaries() throws IplAnalyzerException {
			iplAnalyzer.loadIplData(filePath);
			String sortedData = iplAnalyzer.sortBatsmanDataOnBoundariesThenStrikeRate();
			MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
			Assert.assertEquals("Andre Russell", mostRunsCSV[0].playerName);
		}

		// test to check the batsman with  Strike rate and then best average
				@Test
				public void givenCSVFilePath_ShouldReturnPlayerWithBestAverageWithStrikeRate() throws IplAnalyzerException {
					iplAnalyzer.loadIplData(filePath);
					String sortedData = iplAnalyzer.sortBatsmanDataOnStrikeRateThenAverage();
					MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
					Assert.assertEquals("Ishant Sharma", mostRunsCSV[0].playerName);
				}


}
