package com.bunnings.catalog.io;

import com.bunnings.catalog.io.data.BarcodeData;
import com.bunnings.catalog.io.data.ProductData;
import com.bunnings.catalog.io.data.SupplierData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvReaderTest {
	private CsvReader reader = new CsvReader();

	@Test
	void readBarcodes() {
		List<BarcodeData> barcodeDataList = reader.readBarcodes("src/test/resources/input/barcodesA.csv");

		assertEquals(52, barcodeDataList.size());

		BarcodeData barcodeData = barcodeDataList.get(0);
		assertEquals("00001", barcodeData.getSupplierId());
		assertEquals("647-vyk-317", barcodeData.getSku());
		assertEquals("z2783613083817", barcodeData.getBarcode());
	}

	@Test
	void readCatalog() {
		List<ProductData> productDataList = reader.readCatalog("src/test/resources/input/catalogA.csv");

		assertEquals(5, productDataList.size());

		ProductData productData = productDataList.get(0);
		assertEquals("647-vyk-317", productData.getSku());
		assertEquals("Walkers Special Old Whiskey", productData.getDescription());
	}

	@Test
	void readSuppliers() {
		List<SupplierData> supplierDataList = reader.readSuppliers("src/test/resources/input/suppliersA.csv");

		assertEquals(5, supplierDataList.size());

		SupplierData supplierData = supplierDataList.get(0);
		assertEquals("00001", supplierData.getSid());
		assertEquals("Twitterbridge", supplierData.getName());
	}
}