package com.bunnings.catalog;

import com.bunnings.catalog.io.CatalogCsvWriter;
import com.bunnings.catalog.io.CsvReader;
import com.bunnings.catalog.model.Catalog;
import com.bunnings.catalog.service.CatalogLoader;
import com.bunnings.catalog.service.CatalogMerger;
import com.bunnings.catalog.service.ListsMerger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CatalogManagerApplicationTest {

	private CatalogManagerApplication manager = new CatalogManagerApplication(
			new CatalogLoader(new CsvReader()), new CatalogMerger(new ListsMerger()), new CatalogCsvWriter());

	@Test
	void merge() {
		String dir = "src/test/resources/input/";
		Catalog merged = manager.merge(
				"A", dir + "suppliersA.csv",dir + "catalogA.csv", dir + "barcodesA.csv",
				"B", dir + "suppliersB.csv",dir + "catalogB.csv", dir + "barcodesB.csv");

		assertEquals(7, merged.getProducts().size());
	}

	@Test
	void output() throws Exception {
		String dir = "src/test/resources/input/";
		Catalog merged = manager.merge(
				"A", dir + "suppliersA.csv",dir + "catalogA.csv", dir + "barcodesA.csv",
				"B", dir + "suppliersB.csv",dir + "catalogB.csv", dir + "barcodesB.csv");

		String outputFile = "result.csv";
		manager.output(outputFile, merged);
		assertTrue(Files.exists(Paths.get(outputFile)));

		List<String> outputContent = readFromFile(outputFile);
		List<String> expectedContent = readFromFile("src/test/resources/output/result_output.csv");
		assertTrue(outputContent.containsAll(expectedContent));

		Files.delete(Paths.get(outputFile));
	}


	private List<String> readFromFile(String fileName) throws IOException {
		List<String> content = new ArrayList<>();
		try (FileReader fr= new FileReader(fileName);   //reads the file
				 BufferedReader br = new BufferedReader(fr);) {
			String line;
			while ((line =br.readLine()) != null) {
				content.add(line);
			}
		}
		return content;
	}
}