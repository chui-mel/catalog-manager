package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductSupplier {
	private String sku;
	private String sid;
	private List<String> barcodes;

	public void addBarcode(String barcode) {
		barcodes.add(barcode);
	}
}
