package com.bunnings.catalog.service;

import com.bunnings.catalog.io.CsvReader;
import com.bunnings.catalog.model.Catalog;
import com.bunnings.catalog.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogLoaderTest {

	private CatalogLoader catalogLoader = new CatalogLoader(new CsvReader());

	@Test
	void loadCatalogFromFile() {
		Catalog catalogA = catalogLoader.loadFromFile(
				"A",
				"src/test/resources/input/suppliersA.csv",
				"src/test/resources/input/catalogA.csv",
				"src/test/resources/input/barcodesA.csv");

		List<Product> products = catalogA.getProducts();

		assertEquals(5, products.size());
	}
}