package com.bridegelabz.iplanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.bridegelabz.iplanalysis.IplAnalyzerException.ExceptionType;
import com.google.gson.Gson;

public class IPLAnalyzer {

	List<MostRunsCSV> list;
	ICSVBuilder csvBuilder = BuilderFactoryCSV.generateBuilder();

	public IPLAnalyzer() {
		list = new ArrayList<>();
	}

	public void loadIplData(String filePath) throws IplAnalyzerException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			list = csvBuilder.getList(reader, MostRunsCSV.class);
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
		Comparator<MostRunsCSV> csvComparator = Comparator.comparing(player -> player.foursCollected+player.sixesCollected);
		this.sortMostRunsCSV(csvComparator);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnBoundariesThenStrikeRate() {
		Comparator<MostRunsCSV> csvComparator1 = Comparator.comparing(player -> player.foursCollected+player.sixesCollected);
		Comparator<MostRunsCSV> csvComparator2 = csvComparator1.thenComparing(player -> Double.parseDouble(player.strikeRate));
		this.sortMostRunsCSV(csvComparator2);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;
	}

	public String sortBatsmanDataOnStrikeRateThenAverage() {
		Comparator<MostRunsCSV> csvComparator1 = Comparator.comparing(player -> Double.parseDouble(player.strikeRate));
		Comparator<MostRunsCSV> csvComparator2 = csvComparator1.thenComparing(player -> Double.parseDouble(player.average));
		this.sortMostRunsCSV(csvComparator2);
		String sortedRunsList = new Gson().toJson(list);
		return sortedRunsList;

	}

}
