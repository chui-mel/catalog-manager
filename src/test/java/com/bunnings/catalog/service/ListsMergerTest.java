package com.bunnings.catalog.service;

import com.bunnings.catalog.model.BarcodeProduct;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListsMergerTest {

	private ListsMerger merger = new ListsMerger();

	@Test
	void merge() {
		List<BarcodeProduct> primary = List.of(
				BarcodeProduct.builder().companyName("A").barcode("abc").sku("sku1").build(),
				BarcodeProduct.builder().companyName("A").barcode("efg").sku("sku1").build(),
				BarcodeProduct.builder().companyName("A").barcode("hij").sku("sku1").build(),
				BarcodeProduct.builder().companyName("A").barcode("klm").sku("sku2").build(),
				BarcodeProduct.builder().companyName("A").barcode("nop").sku("sku2").build()
		);

		List<BarcodeProduct> secondary = List.of(
				BarcodeProduct.builder().companyName("B").barcode("efg").sku("sku1b").build(),
				BarcodeProduct.builder().companyName("B").barcode("efgb").sku("sku1b").build(),
				BarcodeProduct.builder().companyName("B").barcode("hij").sku("sku1b").build(),
				BarcodeProduct.builder().companyName("B").barcode("klm").sku("sku3").build(),
				BarcodeProduct.builder().companyName("B").barcode("nopddd").sku("sku2").build()
		);

		List<BarcodeProduct> merged = merger.mergeTwoSorted(primary, secondary);

		assertEquals(7, merged.size());
		assertTrue(merged.stream()
				.map(BarcodeProduct::getBarcode)
				.collect(Collectors.toList())
				.containsAll(List.of("abc", "efg", "hij", "klm", "nop", "efgb", "nopddd")));
	}
}