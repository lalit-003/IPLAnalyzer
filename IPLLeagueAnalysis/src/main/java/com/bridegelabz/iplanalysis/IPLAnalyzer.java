package com.bridegelabz.iplanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bridegelabz.iplanalysis.IplAnalyzerException.ExceptionType;
import com.google.gson.Gson;

public class IPLAnalyzer {

	List<MostRunsCSV> list;
	List<MostWicketsCSV> listBowler;
	Map<SortType, Comparator<MostRunsCSV>> sortedMap;
	ICSVBuilder csvBuilder = BuilderFactoryCSV.generateBuilder();

	public void loadIplData(String filePath) throws IplAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			list = csvBuilder.getList(reader, MostRunsCSV.class);
		} catch (IOException e) {
			throw new IplAnalyzerException("provided invalid file path", ExceptionType.INVALID_FILE_PATH);
		}
	}

	public void loadIplDataBowler(String filePath) throws IplAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			listBowler = csvBuilder.getList(reader, MostWicketsCSV.class);
		} catch (IOException e) {
			throw new IplAnalyzerException("provided invalid file path", ExceptionType.INVALID_FILE_PATH);
		}
	}

	public String sortBatsmanDataOnAverage() throws IplAnalyzerException {
		if (list == null || list.size() == 0) {
			throw new IplAnalyzerException("No Data Found in list", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<MostRunsCSV> csvComparator = Comparator.comparing(player -> Double.parseDouble(player.average));
		this.sortMostRunsCSV(csvComparator);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnStrikeRate() throws IplAnalyzerException {
		if (list == null || list.size() == 0) {
			throw new IplAnalyzerException("No Data Found in list", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<MostRunsCSV> csvComparator = Comparator.comparing(player -> Double.parseDouble(player.strikeRate));
		this.sortMostRunsCSV(csvComparator);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnBoundaries() throws IplAnalyzerException {
		if (list == null || list.size() == 0) {
			throw new IplAnalyzerException("No Data Found in list", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<MostRunsCSV> csvComparator = Comparator
				.comparing(player -> player.foursCollected + player.sixesCollected);
		this.sortMostRunsCSV(csvComparator);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnBoundariesThenStrikeRate() {
		Comparator<MostRunsCSV> csvComparator1 = Comparator
				.comparing(player -> player.foursCollected + player.sixesCollected);
		Comparator<MostRunsCSV> csvComparator2 = csvComparator1
				.thenComparing(player -> Double.parseDouble(player.strikeRate));
		this.sortMostRunsCSV(csvComparator2);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnStrikeRateThenAverage() {
		Comparator<MostRunsCSV> csvComparator1 = Comparator.comparing(player -> Double.parseDouble(player.strikeRate));
		Comparator<MostRunsCSV> csvComparator2 = csvComparator1
				.thenComparing(player -> Double.parseDouble(player.average));
		this.sortMostRunsCSV(csvComparator2);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;

	}

	public String sortBatsmanDataOnAverageThenMaximumRuns() {
		Comparator<MostRunsCSV> csvComparator1 = Comparator.comparing(player -> Double.parseDouble(player.average));
		Comparator<MostRunsCSV> csvComparator2 = csvComparator1
				.thenComparing(player -> Double.parseDouble(player.runsScored));
		this.sortMostRunsCSV(csvComparator2);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortDataAccordingToSortType(SortType sortType) throws IplAnalyzerException {
		if (list == null || list.size() == 0) {
			throw new IplAnalyzerException("No Data Found", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		this.sortMostRunsCSV(sortedMap.get(sortType).reversed());
		String sortedStateCensus = new Gson().toJson(list);
		return sortedStateCensus;
	}

	private void sortMostRunsCSV(Comparator<MostRunsCSV> runsComparator) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				MostRunsCSV mostRuns1 = list.get(j);
				MostRunsCSV mostRuns2 = list.get(j + 1);
				if (runsComparator.compare(mostRuns1, mostRuns2) < 0) {
					list.set(j, mostRuns2);
					list.set(j + 1, mostRuns1);
				}
			}
		}
		// to print list after sorting
		for (int m = 0; m < list.size(); m++) {
			System.out.println(list.get(m));
		}
	}

	private void sortMostWicketsCSV(Comparator<MostWicketsCSV> wicketsComparator) {
		for (int i = 0; i < listBowler.size() - 1; i++) {
			for (int j = 0; j < listBowler.size() - 1 - i; j++) {
				MostWicketsCSV mostRuns1 = listBowler.get(j);
				MostWicketsCSV mostRuns2 = listBowler.get(j + 1);
				if (wicketsComparator.compare(mostRuns1, mostRuns2) < 0) {
					listBowler.set(j, mostRuns2);
					listBowler.set(j + 1, mostRuns1);
				}
			}
		}
		// to print list after sorting
		for (int m = 0; m < listBowler.size(); m++) {
			System.out.println(listBowler.get(m));
		}
	}

	public String sortBowlerDataOnAverage() throws IplAnalyzerException {
		if (listBowler == null || listBowler.size() == 0) {
			throw new IplAnalyzerException("No Data Found in list", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<MostWicketsCSV> csvComparator = Comparator.comparing(player -> Double.parseDouble(player.average));
		this.sortMostWicketsCSV(csvComparator);
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;
	}

	public String sortBowlerDataOnTopStrikingRates() throws IplAnalyzerException {
		if (listBowler == null || listBowler.size() == 0) {
			throw new IplAnalyzerException("No Data Found in list", IplAnalyzerException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<MostWicketsCSV> csvComparator = Comparator
				.comparing(player -> Double.parseDouble(player.strikeRate));
		this.sortMostWicketsCSV(csvComparator);
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;
	}

	public String sortBowlerDataOnEconomy() {
		Comparator<MostWicketsCSV> csvComparator = Comparator.comparing(player -> Double.parseDouble(player.economy));
		this.sortMostWicketsCSV(csvComparator.reversed());
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;
	}

	public String sortBowlerDataBestStrikeRateWithFiveAndFourWicketHauls() {
		Comparator<MostWicketsCSV> csvComparator = Comparator
				.comparing(player -> player.fourWicketHaul + player.fiveWicketHaul);
		Comparator<MostWicketsCSV> csvComparator1 = csvComparator
				.thenComparing(player -> Double.parseDouble(player.strikeRate));
		this.sortMostWicketsCSV(csvComparator1);
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;
	}

	public String sortBowlerDataBestAveragesWithStrikeRate() {
		Comparator<MostWicketsCSV> csvComparator = Comparator
				.comparing(player -> Double.parseDouble(player.strikeRate));
		Comparator<MostWicketsCSV> csvComparator1 = csvComparator
				.thenComparing(player -> Double.parseDouble(player.average));
		this.sortMostWicketsCSV(csvComparator1);
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;

	}

	public String sortBowlerWithMaximumWicketsWithBestAverage() {
		Comparator<MostWicketsCSV> csvComparator = Comparator.comparing(player -> Double.parseDouble(player.average));
		Comparator<MostWicketsCSV> csvComparator1 = csvComparator
				.thenComparing(player -> Double.parseDouble(player.wicketsTaken));
		this.sortMostWicketsCSV(csvComparator1);
		String sortedWicketsList = new Gson().toJson(listBowler);
		return sortedWicketsList;

	}

	public List<String> getBatsmanName() {
		List<String> listBatsmanName = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			listBatsmanName.add(list.get(i).playerName);
		}
		return listBatsmanName;
	}

	public List<String> getBowlerName() {
		List<String> listBowlerName = new ArrayList<String>();
		for (int i = 0; i < listBowler.size(); i++) {
			listBowlerName.add(listBowler.get(i).playerName);
		}
		return listBowlerName;
	}

	public List<String> getAllRounders() {
		List<String> batsman = this.getBatsmanName();
		List<String> bowler = this.getBowlerName();
		List<String> allRounderList = new ArrayList<>();
		for (String i : batsman) {
			for (String j : bowler) {
				if (i.equals(j)) {
					allRounderList.add(i);
				}
			}
		}
		System.out.println("Printing all rounder names");
		for (String name : allRounderList) {
			System.out.println(name);
		}
		return allRounderList;
	}

	public HashMap<String, Double> getAllRounderPoints() {
		List<String> allRounders = this.getAllRounders();
		HashMap<String, Double> allRounderPoints = new HashMap<String, Double>();
		for (String allrounder : allRounders) {
			Double points = 0.0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).playerName == allrounder)
					points = points + Double.parseDouble(list.get(i).runsScored);
			}

			for (int j = 0; j < listBowler.size(); j++) {
				if ((listBowler.get(j).playerName).equals(allrounder)) {
					points = points + (Double.parseDouble(listBowler.get(j).wicketsTaken) * 10);
				}
			}
			allRounderPoints.put(allrounder, points);
		}
		// printing allrounders with points
		for (String s : allRounderPoints.keySet()) {
			System.out.println("name is = " + s + "  and points are : " + allRounderPoints.get(s));
		}
		return allRounderPoints;
	}

	public HashMap<String, Double> getAveragePoints() {
		List<String> allRounders = this.getAllRounders();
		HashMap<String, Double> averagePoints = new HashMap<String, Double>();
		for (String allrounder : allRounders) {
			Double average = 0.0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).playerName == allrounder)
					average = average + Double.parseDouble(list.get(i).average);
			}

			for (int j = 0; j < listBowler.size(); j++) {
				if ((listBowler.get(j).playerName).equals(allrounder)) {
					average = average + Double.parseDouble(listBowler.get(j).average);
				}
			}
			averagePoints.put(allrounder, average);
		}
		// printing allrounders' average(batting+bowling) with points
		for (String s : averagePoints.keySet()) {
			System.out.println("name is = " + s + "  and averagePoints are : " + averagePoints.get(s));
		}
		return averagePoints;
	}

	public List<String> getBatsmanNameWhoScoredHundreds() {
		List<String> listBatsmanWithHundreds = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i).hundredsScored) > 0)
				listBatsmanWithHundreds.add(list.get(i).playerName);
		}
		System.out.println("Batsman who scored hundreds are : ");
		for (String name : listBatsmanWithHundreds) {
			System.out.println(name);
		}
		return listBatsmanWithHundreds;
	}

	public HashMap<String, Double> getBatsmanWithHundredsAndBestAverage() {
		List<String> batsmanWithHundreds = this.getBatsmanNameWhoScoredHundreds();
		HashMap<String, Double> hundredList = new HashMap<String, Double>();
		for (String batsman : batsmanWithHundreds) {
			Double average = 0.0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).playerName == batsman)
					average = Double.parseDouble(list.get(i).average);
			}
			hundredList.put(batsman, average);
		}
		// printing batsman with hundreds and average
		for (String s : hundredList.keySet()) {
			System.out.println("name is = " + s + "  and average is  : " + hundredList.get(s));
		}
		return hundredList;
	}
	
	public List<String> getBatsmanNamesWhoZeroHundredsandZeroFifties() {
		List<String> listBatsmanWithNoHundredsNFifties = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i).hundredsScored) == 0 && Integer.parseInt(list.get(i).fiftiesScored) == 0 )
				listBatsmanWithNoHundredsNFifties.add(list.get(i).playerName);
		}
		System.out.println("Batsman who scored zero hundreds and fifties are : ");
		for (String name : listBatsmanWithNoHundredsNFifties) {
			System.out.println(name);
		}
		return listBatsmanWithNoHundredsNFifties;
	}

	public HashMap<String, Double> getBatsmanZeroHundredsAndFiftiesButBestAverage() {
		List<String> batsmanWithNoHundredsNFifties = this.getBatsmanNamesWhoZeroHundredsandZeroFifties();
		HashMap<String, Double> noHundredNFiftyList = new HashMap<String, Double>();
		for (String batsman : batsmanWithNoHundredsNFifties) {
			Double average = 0.0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).playerName == batsman)
					average = Double.parseDouble(list.get(i).average);
			}
			noHundredNFiftyList.put(batsman, average);
		}
		// printing batsman with no hundred and no fifties but best average
		for (String s : noHundredNFiftyList.keySet()) {
			System.out.println("name is = " + s + "  and average is  : " + noHundredNFiftyList.get(s));
		}
		return noHundredNFiftyList;
	}


}
