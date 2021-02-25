package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
public class Product {
	private String companyName;
	private String sku;
	private String description;
	private Map<String, ProductSupplier> suppliers;

	public void addSupplier(ProductSupplier productSupplier) {
		suppliers.put(productSupplier.getSid(), productSupplier);
	}

	public ProductSupplier getSupplier(String sid) {
		return suppliers.getOrDefault(sid,
				ProductSupplier.builder().sid(sid).barcodes(new ArrayList<>()).build());
	}

	public Optional<String> findSupplierByBarcode(String barcode) {
		return suppliers.values().stream()
				.filter(s -> s.getBarcodes().contains(barcode))
				.map(ProductSupplier::getSid)
				.findFirst();
	}

	public List<BarcodeProduct> productBarcodes() {
		return suppliers.values().stream()
				.map(ps -> ps.getBarcodes().stream()
							.map(barcode -> BarcodeProduct.builder()
									.barcode(barcode).sku(ps.getSku()).description(description).companyName(companyName)
									.build())
							.collect(Collectors.toList()))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

}
