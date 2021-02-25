package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarcodeProduct implements Comparable<BarcodeProduct> {
	private String barcode;
	private String sku;
	private String companyName;

	@Override
	public int compareTo(BarcodeProduct o) {
		return barcode.compareTo(o.barcode);
	}
}
