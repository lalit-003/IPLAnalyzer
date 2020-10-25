package com.bridegelabz.iplanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TestingIPLAnalyzer {

	public static String filePath = "D:\\BridgeLabz-Fellowship\\IPL\\IPLLeagueAnalysis\\resources\\MostRuns.csv";
	public static String filePathBowler = "D:\\BridgeLabz-Fellowship\\IPL\\IPLLeagueAnalysis\\resources\\MostWickets.csv";
	private IPLAnalyzer iplAnalyzer;

	@Before
	public void initialize() {
		iplAnalyzer = new IPLAnalyzer();
	}

	// UC1
	// test to check the batsman with top batting average
	@Test
	public void givenCSVFilePath_ShouldReturnTopBattingAverage() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnAverage();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("MS Dhoni", mostRunsCSV[0].playerName);
		Assert.assertEquals("83.2", mostRunsCSV[0].average);
	}

	// UC2
	// test to check the batsman with top strike rate
	@Test
	public void givenCSVFilePath_ShouldReturnTopStrikeRate() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnStrikeRate();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Ishant Sharma", mostRunsCSV[0].playerName);
		Assert.assertEquals("333.33", mostRunsCSV[0].strikeRate);
	}

	// UC3
	// test to check the batsman with most boundaries
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithMostSixesNFours() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnBoundaries();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Andre Russell", mostRunsCSV[0].playerName);
		Assert.assertEquals(83, mostRunsCSV[0].foursCollected + mostRunsCSV[0].sixesCollected);
	}

	// UC4
	// test to check the batsman with Strike rate and then most boundaries
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithBestStrikeAndBoundaries() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnBoundariesThenStrikeRate();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Andre Russell", mostRunsCSV[0].playerName);
	}

	// UC5
	// test to check the batsman with Strike rate and then best average
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithBestAverageWithStrikeRate() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnStrikeRateThenAverage();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("Ishant Sharma", mostRunsCSV[0].playerName);
	}

	// UC6
	// test to check the batsman with best Average and then Maximum Runs
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithMaximumRunsWithBestAverages() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		String sortedData = iplAnalyzer.sortBatsmanDataOnAverageThenMaximumRuns();
		MostRunsCSV[] mostRunsCSV = new Gson().fromJson(sortedData, MostRunsCSV[].class);
		Assert.assertEquals("MS Dhoni", mostRunsCSV[0].playerName);
	}
	
	// UC7
		// test to check the bowler with top Average 
		@Test
		public void givenCSVFilePath_ShouldReturnPalyersWithTopBowlingAverages() throws IplAnalyzerException {
			iplAnalyzer.loadIplDataBowler(filePathBowler);
			String sortedData = iplAnalyzer.sortBowlerDataOnAverage();
			MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
			Assert.assertEquals("Krishnappa Gowtham", mostWicketsCSV[0].playerName);
			Assert.assertEquals("166", mostWicketsCSV[0].average);

		}
       
		// UC8
				// test to check the bowler with top bowling Striking rates
				@Test
				public void givenCSVFilePath_ShouldReturnPalyersWithTopStrikingRates() throws IplAnalyzerException {
					iplAnalyzer.loadIplDataBowler(filePathBowler);
					String sortedData = iplAnalyzer.sortBowlerDataOnTopStrikingRates();
					MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
					Assert.assertEquals("Krishnappa Gowtham", mostWicketsCSV[0].playerName);
					Assert.assertEquals("120", mostWicketsCSV[0].strikeRate);
				}

				// UC9
				// test to check the bowler with best economy
				@Test
				public void givenCSVFilePath_ShouldReturnPalyersWithBestEconomy() throws IplAnalyzerException {
					iplAnalyzer.loadIplDataBowler(filePathBowler);
					String sortedData = iplAnalyzer.sortBowlerDataOnEconomy();
					MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
					Assert.assertEquals("Shivam Dube", mostWicketsCSV[0].playerName);
					Assert.assertEquals("4.8", mostWicketsCSV[0].economy);
				}

				
				// UC10
				// test to check the bowler with five+four wicket hauls then Strike Rate
				@Test
				public void givenCSVFilePath_ShouldReturnPalyersWithBestStrikeWithFiveAndfourwicket() throws IplAnalyzerException {
					iplAnalyzer.loadIplDataBowler(filePathBowler);
					String sortedData = iplAnalyzer.sortBowlerDataBestStrikeRateWithFiveAndFourWicketHauls();
					MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
					Assert.assertEquals("Lasith Malinga", mostWicketsCSV[0].playerName);
					Assert.assertEquals("16.81", mostWicketsCSV[0].strikeRate);
				}




}
