package com.bunnings.catalog.service;

import com.bunnings.catalog.model.*;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CatalogMerger {
	private final ListsMerger listsMerger;

	public Catalog merge(Catalog primary, Catalog secondary) {
		List<BarcodeProduct> primaryBarcodes = primary.barcodes();
		List<BarcodeProduct> secondaryBarcodes = secondary.barcodes();

		List<BarcodeProduct> merged = listsMerger.mergeTwoSorted(primaryBarcodes, secondaryBarcodes);

		Map<String, Product> mergedProducts = new HashMap<>();

		Map<String, Catalog>
		   catalogsMap = Map.of(primary.getCompanyName(), primary, secondary.getCompanyName(), secondary);

		merged.forEach(b -> {
			Product product = mergedProducts.getOrDefault(b.getSku() + ":" + b.getCompanyName(),
							Product.builder()
									.companyName(b.getCompanyName()).sku(b.getSku()).description(b.getDescription()).suppliers(new HashMap<>())
									.build());

			catalogsMap.get(b.getCompanyName()).getSupplierByProductAndBarcode(b.getSku(), b.getBarcode())
					.ifPresent(id -> {
						ProductSupplier pb = product.getSupplier(id);
						pb.addBarcode(b.getBarcode());
						product.addSupplier(pb);
					});

			mergedProducts.put(product.getSku() + ":" + product.getCompanyName(), product);
		});

		return Catalog.builder()
				.companyName(primary.getCompanyName())
				.products(mergedProducts.values().stream().collect(Collectors.toList()))
				.build();
	}
}
