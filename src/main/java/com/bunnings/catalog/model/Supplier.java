package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Supplier {
	private String sid;
	private String name;
}
