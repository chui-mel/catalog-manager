package com.bunnings.catalog.io;

import com.bunnings.catalog.model.Catalog;
import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static com.opencsv.CSVWriter.*;

public class CatalogCsvWriter {

	@SneakyThrows
	public void writeToCsv(String fileName, Catalog catalog) {
		if (StringUtils.isEmpty(fileName) || Objects.isNull(catalog)) {
			throw new IllegalArgumentException("Parameter should not be empty, please check");
		}

		try (
				Writer writer = Files.newBufferedWriter(Paths.get(fileName));
				CSVWriter csvWriter = new CSVWriter(writer, DEFAULT_SEPARATOR, NO_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER, DEFAULT_LINE_END)) {
			csvWriter.writeNext(new String[] { "SKU", "Description", "Source" });
			catalog.getProducts().forEach(
					product -> csvWriter.writeNext(new String[]{ product.getSku(), product.getDescription(), product.getCompanyName() })
			);
		}
	}
}
