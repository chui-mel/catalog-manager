package com.bunnings.catalog.io;

import com.bunnings.catalog.io.data.BarcodeData;
import com.bunnings.catalog.io.data.ProductData;
import com.bunnings.catalog.io.data.SupplierData;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

	@SneakyThrows
	public List<BarcodeData> readBarcodes(String fileName) {
		List<BarcodeData> barcodeDataList = new ArrayList<>();

		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
			String[] record;
			while ((record = csvReader.readNext()) != null) {
				barcodeDataList.add(BarcodeData.builder()
						.supplierId(record[0])
						.sku(record[1])
						.barcode(record[2])
						.build());
			}
		}
		return barcodeDataList;
	}

	@SneakyThrows
	public List<ProductData> readCatalog(String fileName) {
		List<ProductData> productDataList = new ArrayList<>();

		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
			String[] record;
			while ((record = csvReader.readNext()) != null) {
				productDataList.add(ProductData.builder()
						.sku(record[0])
						.description(record[1])
						.build());
			}
		}
		return productDataList;
	}

	@SneakyThrows
	public List<SupplierData> readSuppliers(String fileName) {
		List<SupplierData> supplierDataList = new ArrayList<>();

		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
			String[] record;
			while ((record = csvReader.readNext()) != null) {
				supplierDataList.add(SupplierData.builder()
						.sid(record[0])
						.name(record[1])
						.build());
			}
		}
		return supplierDataList;
	}
}
