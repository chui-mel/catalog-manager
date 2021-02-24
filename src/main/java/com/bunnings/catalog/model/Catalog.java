package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Catalog {
	private List<Product> products;
}
