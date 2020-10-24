package com.bridegelabz.iplanalysis;

import com.opencsv.bean.CsvBindByName;

public class MostRunsCSV {

	@CsvBindByName(column = "PLAYER" , required =true)
	public String playerName;
		
	@CsvBindByName(column = "Mat" , required =true)
	public String matchesPlayed;
	
	@CsvBindByName(column = "Inns" , required =true)
	public String inningsPlayed;
	
	@CsvBindByName(column = "NO" , required =true)
	public String numberOFNotOuts;
	
	@CsvBindByName(column = "Runs" , required =true)
	public String runsScored;
	
	@CsvBindByName(column = "HS" , required =true)
	public String highestScore;
	
	@CsvBindByName(column = "Avg" , required =true)
	public String average;
	
	@CsvBindByName(column = "BF" , required =true)
	public String ballsFaced;
	
	@CsvBindByName(column = "SR" , required =true)
	public String strikeRate;
	
	@CsvBindByName(column = "100" , required =true)
	public String hundredsScored;
	
	@CsvBindByName(column = "50" , required =true)
	public String fiftiesScored;
	
	@CsvBindByName(column = "4s" , required =true)
	public int foursCollected;
	
	@CsvBindByName(column = "6s" , required =true)
	public int sixesCollected;

	@Override
	public String toString() {
		return "Player =" + playerName + ", Matches=" + matchesPlayed + ", Innings=" + inningsPlayed + " not outs = " + numberOFNotOuts+
				"runs = " +runsScored + " highest score  = "+highestScore + " average = " +average +" ballsfaced = "+ballsFaced 
				+" Strike Rate = " +strikeRate + "100s scored = " +hundredsScored+" 50s scored = "+fiftiesScored
				+"fours = "+foursCollected +" sixes = "+sixesCollected;
	}
}
