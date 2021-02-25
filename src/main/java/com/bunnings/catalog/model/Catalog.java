package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Data
public class Catalog {
	private String companyName;
	private List<Product> products;

	public List<BarcodeProduct> barcodes() {
		List<BarcodeProduct> result = products.stream()
				.map(p -> p.productBarcodes())
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		Collections.sort(result);
		return result;
	}

	public Optional<Product> getProduct(String sku) {
		return products.stream()
				.filter(product -> product.getSku().equals(sku))
				.findFirst();
	}

	public Optional<String> getSupplierByProductAndBarcode(String sku, String barcode) {
		return products.stream()
				.filter(product -> product.getSku().equals(sku))
				.findFirst()
				.map(product -> product.findSupplierByBarcode(barcode))
				.map(Object::toString);
	}
}
