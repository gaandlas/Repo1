package com.spe.util

import com.kms.katalon.core.annotation.Keyword
import com.univocity.parsers.tsv.TsvParser
import com.univocity.parsers.tsv.TsvParserSettings

public class Parser {

	@Keyword
	public List<String[]> parseTsvFile(String fileLocation, int column) {
		TsvParserSettings settings = new TsvParserSettings();
		TsvParser parser = new TsvParser(settings);

		List<String[]> allRows = parser.parseAll(new FileReader(fileLocation));

		List<String> colValues = new ArrayList<String>();

		for(int i = 1; i < allRows.size(); i++) {
			colValues.add(allRows.get(i)[0].split("\\s+")[column])
		}

		for(String s : colValues) {
			System.out.println(s)
		}

		return colValues;
	}
}
