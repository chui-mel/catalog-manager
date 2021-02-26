package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@Getter
public class Catalog {
	private String companyName;
	private List<Product> products;

	public List<BarcodeProduct> barcodes() {
		List<BarcodeProduct> result = products.stream()
				.map(Product::productBarcodes)
				.flatMap(Collection::stream)
				.sorted().collect(Collectors.toList());

		return result;
	}

	public Optional<String> getSupplierByProductAndBarcode(String sku, String barcode) {
		return products.stream()
				.filter(product -> product.getSku().equals(sku))
				.findFirst()
				.map(product -> product.findSupplierByBarcode(barcode))
				.map(Object::toString);
	}

}
