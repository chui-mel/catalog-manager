package com.bunnings.catalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.TreeSet;

@Data
@Builder
public class Product {
	private String sku;
	private String description;
	private Map<String, ProductSupplier> suppliers;

	public void addSupplier(ProductSupplier productSupplier) {
		suppliers.put(productSupplier.getSupplier().getSid(), productSupplier);
	}

	public ProductSupplier getSupplier(Supplier supplier) {
		return suppliers.getOrDefault(supplier.getSid(),
				ProductSupplier.builder().supplier(supplier).sku(sku).barcodes(new TreeSet<>()).build());
	}
}
