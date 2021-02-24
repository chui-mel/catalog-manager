package com.bunnings.catalog.io.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarcodeData {
	private String supplierId;
	private String sku;
	private String barcode;
}
