package com.bunnings.catalog.io.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductData {
	private String sku;
	private String description;
}
