package com.bunnings.catalog.service;

import com.bunnings.catalog.model.Catalog;
import com.bunnings.catalog.model.Product;
import com.bunnings.catalog.model.ProductSupplier;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CatalogMergerTest {

	private CatalogMerger catalogMerger = new CatalogMerger(new ListsMerger());

	@Test
	void merge() {
		Catalog primary = Catalog.builder()
				.companyName("A")
				.products(List.of(getProducts("A", "sku1", "sku1 desc", "aa1-", List.of("same1", "same2")),
						getProducts("A", "sku2", "sku2 desc", "aa2-", List.of("same21", "same22"))))
				.build();

		Catalog secondary = Catalog.builder()
				.companyName("B")
				.products(List.of(getProducts("B", "skub1", "sku1 desc", "bb1-", List.of("same1", "same2")),
						getProducts("B", "sku2", "sku2 desc", "bb2-", List.of("same21", "same22"))))
				.build();

		Catalog merged = catalogMerger.merge(primary, secondary);

		assertEquals(4, merged.getProducts().size());
	}


	private Product getProducts(String companyName, String sku, String desc, String sidPrefix, List<String> sameBarcodes) {
		return Product.builder().companyName(companyName).sku(sku).description(desc).suppliers(
				Map.of(sidPrefix +"s001", ProductSupplier.builder().sid(sidPrefix + "s001").sku(sku).barcodes(List.of(sidPrefix + "abc", sidPrefix+ "cde")).build(),
						sidPrefix +"s002", ProductSupplier.builder().sid(sidPrefix + "s002").sku(sku).barcodes(List.of(sidPrefix+ "001", sidPrefix + "345")).build(),
						sidPrefix + "s003", ProductSupplier.builder().sid(sidPrefix + "s003").sku(sku).barcodes(sameBarcodes).build())
		).build();
	}
}