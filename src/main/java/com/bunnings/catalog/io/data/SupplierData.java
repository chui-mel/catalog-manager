package com.bunnings.catalog.io.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierData {
	private String sid;
	private String name;
}
