package com.commonscsvbuilder;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new CommonsCSVBuilder();
	}

}
