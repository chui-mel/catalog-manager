package com.bunnings.catalog;

import com.bunnings.catalog.io.CatalogCsvWriter;
import com.bunnings.catalog.io.CsvReader;
import com.bunnings.catalog.model.Catalog;
import com.bunnings.catalog.service.CatalogLoader;
import com.bunnings.catalog.service.CatalogMerger;
import com.bunnings.catalog.service.ListsMerger;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class CatalogManagerApplication {
	private final CatalogLoader catalogLoader;
	private final CatalogMerger catalogMerger;
	private final CatalogCsvWriter catalogCsvWriter;

	public Catalog merge(String primaryCompany, String primarySupplierFile, String primaryCatalogFile, String primaryBarcodeFile,
										String secondaryCompany, String secondarySupplierFile, String secondaryCatalogFile, String secondaryBarcodeFile) {
		Catalog primary = catalogLoader.loadFromFile(primaryCompany, primarySupplierFile, primaryCatalogFile, primaryBarcodeFile);
		Catalog secondary = catalogLoader.loadFromFile(secondaryCompany, secondarySupplierFile, secondaryCatalogFile, secondaryBarcodeFile);;
		return catalogMerger.merge(primary, secondary);
	}

	public void output(String catalogFileName, Catalog catalog) {
		catalogCsvWriter.writeToCsv(catalogFileName, catalog);
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please input your primary catalog files in the following format: ");
			System.out.println("company name, supplier file, catalog file, barcode file: ");
			String primaryInfo = scanner.nextLine();
			String[] primaryCatalogInfo = primaryInfo.split(",");

			System.out.println("Please input your secondary catalog files in the following format: ");
			System.out.println("company name, supplier file, catalog file, barcode file: ");
			String secondaryInfo = scanner.nextLine();
			String[] secondaryCatalogInfo = secondaryInfo.split(",");

			CatalogManagerApplication manager = new CatalogManagerApplication(
					new CatalogLoader(new CsvReader()), new CatalogMerger(new ListsMerger()), new CatalogCsvWriter());

			Catalog mergedCatalog = manager.merge(primaryCatalogInfo[0], primaryCatalogInfo[1], primaryCatalogInfo[2], primaryCatalogInfo[3],
					secondaryCatalogInfo[0], secondaryCatalogInfo[1], secondaryCatalogInfo[2], secondaryCatalogInfo[3]);
			manager.output("result-output.csv", mergedCatalog);
		} catch (Exception e) {
			System.out.println("Something wrong, please check the error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
