package com.bunnings.catalog.service;

import com.bunnings.catalog.io.CsvReader;
import com.bunnings.catalog.io.data.BarcodeData;
import com.bunnings.catalog.io.data.ProductData;
import com.bunnings.catalog.io.data.SupplierData;
import com.bunnings.catalog.model.Catalog;
import com.bunnings.catalog.model.Product;
import com.bunnings.catalog.model.ProductSupplier;
import com.bunnings.catalog.model.Supplier;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CatalogLoader {

	private final CsvReader csvReader;

	public Catalog loadFromFile(String supplierFile, String catalogFile, String barcodeFile) {
		Map<String, Supplier> suppliers = loadSupplier(supplierFile);
		Map<String, Product> products = loadProducts(catalogFile);

		List<BarcodeData> barcodeDataList = csvReader.readBarcodes(barcodeFile);
		barcodeDataList.forEach(
				barcodeData -> {
					Supplier supplier = suppliers.get(barcodeData.getSupplierId());
					Product product = products.get(barcodeData.getSku());
					ProductSupplier productSupplier = product.getSupplier(supplier);
					productSupplier.addBarcode(barcodeData.getBarcode());
					product.addSupplier(productSupplier);
				}
		);

		return Catalog.builder()
				.products(new ArrayList<>(products.values()))
				.build();
	}

	private Map<String, Product> loadProducts(String catalogFile) {
		return csvReader.readCatalog(catalogFile).stream()
				.collect(Collectors.toMap(ProductData::getSku,
						p -> Product.builder()
								.sku(p.getSku())
								.description(p.getDescription())
								.suppliers(new HashMap<>())
								.build()));
	}

	private Map<String, Supplier> loadSupplier(String supplierFile) {
		return csvReader.readSuppliers(supplierFile).stream()
				.collect(Collectors.toMap(SupplierData::getSid,
						s -> Supplier.builder()
								.sid(s.getSid())
								.name(s.getName())
								.build()));
	}
}
