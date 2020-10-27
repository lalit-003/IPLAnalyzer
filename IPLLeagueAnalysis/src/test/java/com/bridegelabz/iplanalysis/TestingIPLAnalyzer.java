package com.bridegelabz.iplanalysis;

import java.util.HashMap;
import java.util.prefs.BackingStoreException;

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

	// UC11
	// test to check the bowler with top Strike Rate and then top bowling averages
	@Test
	public void givenCSVFilePath_ShouldReturnPalyersWithGreatBowlingAveragesWithBestStrikeRate()
			throws IplAnalyzerException {
		iplAnalyzer.loadIplDataBowler(filePathBowler);
		String sortedData = iplAnalyzer.sortBowlerDataBestAveragesWithStrikeRate();
		MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
		Assert.assertEquals("Krishnappa Gowtham", mostWicketsCSV[0].playerName);
		Assert.assertEquals("120", mostWicketsCSV[0].strikeRate);
	}

	// UC12
	// test to check the bowler with top average and then maximum wickets
	@Test
	public void givenCSVFilePath_ShouldReturnPalyersWithMaximumWicketsWithBestAverage() throws IplAnalyzerException {
		iplAnalyzer.loadIplDataBowler(filePathBowler);
		String sortedData = iplAnalyzer.sortBowlerWithMaximumWicketsWithBestAverage();
		MostWicketsCSV[] mostWicketsCSV = new Gson().fromJson(sortedData, MostWicketsCSV[].class);
		Assert.assertEquals("Krishnappa Gowtham", mostWicketsCSV[0].playerName);
		Assert.assertEquals("1", mostWicketsCSV[0].wicketsTaken);
	}

	// UC13
	// test to check for player with best batting and bowling average
	@Test
	public void givenCSVFilePath_ShouldReturnPlayerWithBestBattingAndBowlingAverage() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		iplAnalyzer.loadIplDataBowler(filePathBowler);
		HashMap<String, Double> bestAverage = iplAnalyzer.getAveragePoints();
		Double max = 0.0;
		String bestAllRounder = null;
		// checking for maximum average points
		for (String i : bestAverage.keySet()) {
			if (max < bestAverage.get(i))
				max = bestAverage.get(i);
		}
		// printing best all rounder name on basis of average points
		for (String j : bestAverage.keySet()) {
			if (max == bestAverage.get(j)) {
				System.out.println(
						"best player with average is : " + j + " with batting+bowling average : " + bestAverage.get(j));
				bestAllRounder = j;
			}
		}
		Assert.assertEquals("Krishnappa Gowtham", bestAllRounder);
	}

	// UC14
	// test to check for best allrounder
	@Test
	public void givenCSVFilePath_ShouldReturnBestAllRounder() throws IplAnalyzerException {
		iplAnalyzer.loadIplData(filePath);
		iplAnalyzer.loadIplDataBowler(filePathBowler);
		HashMap<String, Double> allRounderPoints = iplAnalyzer.getAllRounderPoints();
		Double max = 0.0;
		String bestAllRounder = null;
		// checking for maximum points
		for (String i : allRounderPoints.keySet()) {
			if (max < allRounderPoints.get(i))
				max = allRounderPoints.get(i);
		}
		// printing best all rounder name on basis of points
		for (String j : allRounderPoints.keySet()) {
			if (max == allRounderPoints.get(j)) {
				System.out.println("best allrounder is : " + j + " with points " + allRounderPoints.get(j));
				bestAllRounder = j;
			}
		}
		Assert.assertEquals("Andre Russell", bestAllRounder);
	}
	
	// UC15
		// test to check for player with  hundreds and best average
		@Test
		public void givenCSVFilePath_ShouldReturnPlayerWitnMostHundredsAndBestAverage() throws IplAnalyzerException {
			iplAnalyzer.loadIplData(filePath);
			iplAnalyzer.getBatsmanNameWhoScoredHundreds();
			HashMap<String, Double> batsmanWithHundreds = iplAnalyzer.getBatsmanWithHundredsAndBestAverage();
			Double max = 0.0;
     		String batsmanWithHundredNBestAverage = null;
			// checking for maximum average
			for (String i : batsmanWithHundreds.keySet()) {
				if (max < batsmanWithHundreds.get(i))
					max = batsmanWithHundreds.get(i);
			}
			// printing batsman with hundred and best average
			for (String j : batsmanWithHundreds.keySet()) {
				if (max == batsmanWithHundreds.get(j)) {
					System.out.println("batsman with hundred is : " + j + " with average "+ batsmanWithHundreds.get(j));
					batsmanWithHundredNBestAverage = j;
				}
			}
			Assert.assertEquals("David Warner ", batsmanWithHundredNBestAverage);
		}

		// UC16
				// test to check for player with zero hundreds&fifties and best average
				@Test
				public void givenCSVFilePath_ShouldReturnPlayerWithZeroHundredsAndFifties_ButBestAverage() throws IplAnalyzerException {
					iplAnalyzer.loadIplData(filePath);
					HashMap<String, Double> batsmanWithNoHundredsNFifty = iplAnalyzer.getBatsmanZeroHundredsAndFiftiesButBestAverage();
					Double max = 0.0;
		     		String batsmanWithNoHundredNFiftyButBestAverage = null;
					// checking for maximum average
					for (String i : batsmanWithNoHundredsNFifty.keySet()) {
						if (max < batsmanWithNoHundredsNFifty.get(i))
							max = batsmanWithNoHundredsNFifty.get(i);
					}
					// printing batsman with zero hundred and zero fifty but best average
					for (String j : batsmanWithNoHundredsNFifty.keySet()) {
						if (max == batsmanWithNoHundredsNFifty.get(j)) {
							System.out.println("batsman with no hundreds &  no fifites is : " + j + " with best average "+ batsmanWithNoHundredsNFifty.get(j));
							batsmanWithNoHundredNFiftyButBestAverage = j;
						}
					}
					Assert.assertEquals("Marcus Stoinis", batsmanWithNoHundredNFiftyButBestAverage);
				}

}
