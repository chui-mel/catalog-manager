package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductSupplier {
	private String sku;
	private Supplier supplier;
	private Set<String> barcodes;

	public void addBarcode(String barcode) {
		barcodes.add(barcode);
	}
}
