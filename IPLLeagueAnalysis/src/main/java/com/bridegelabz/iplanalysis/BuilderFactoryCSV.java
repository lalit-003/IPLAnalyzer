package com.bridegelabz.iplanalysis;

public class BuilderFactoryCSV {

	public static ICSVBuilder generateBuilder() {
		return new OpenCSVBuilder();
	}
}