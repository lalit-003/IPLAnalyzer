package com.bridegelabz.iplanalysis;

import com.opencsv.bean.CsvBindByName;

public class MostWicketsCSV {

	@CsvBindByName(column = "PLAYER" , required =true)
	public String playerName;
		
	@CsvBindByName(column = "Mat" , required =true)
	public String matchesPlayed;
	
	@CsvBindByName(column = "Inns" , required =true)
	public String inningsPlayed;
	
	@CsvBindByName(column = "Ov" , required =true)
	public String overBowled;
	
	@CsvBindByName(column = "Runs" , required =true)
	public String runsGiven;
	
	@CsvBindByName(column = "Wkts" , required =true)
	public String wicketsTaken;
	
	@CsvBindByName(column = "Avg" , required =true)
	public String average;
	
	@CsvBindByName(column = "BBI" , required =true)
	public String bbi;
	
	@CsvBindByName(column = "Econ" , required =true)
	public String Economy;
	
	@CsvBindByName(column = "SR" , required =true)
	public String strikeRate;
	
	@CsvBindByName(column = "4w" , required =true)
	public int fourWicketHaul;
	
	@CsvBindByName(column = "5w" , required =true)
	public int fiveWicketHaul;

	@Override
	public String toString() {
		return "Player =" + playerName + ", Matches=" + matchesPlayed + ", Innings=" + inningsPlayed + " oversBowled = " + overBowled+
				"runsGiven = " +runsGiven + " wickets  = "+wicketsTaken + " average = " +average +" BBI = " +bbi 
				+" Strike Rate = " +strikeRate + "Economy = " +Economy+" 4WicketsHaul = "+fourWicketHaul
				+"5WicketsHaul = "+fiveWicketHaul;
	}
}


