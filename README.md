# Catalog Manager
## Tech Stack:
* Java 11
* Lombok
* JUnit

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [Design and implementation](#Design-and-implementation)
3. [Assumptions and TradeOffs](#Assumptions-and-tradeoffs)

## How to run this application

* Navigate the root folder /catalog-manager under the command line
* Run the command to build the whole project:
 `./gradlew clean shadowJar`
* Run the command to start the application: 
  `java  -jar ./build/libs/catalog-manager-1.0-SNAPSHOT-all.jar`
* Or you can run in IDE directly for CatalogManagerApplication

You will be asked to input the files needed for primary company and secondary company (the one was bought) in the following format:
`company name, supplier csv file, catalog csv file, barcode csv file`
If all inputs correct and all files are correct, you will see the result in `result-output.csv`.

## Design and implementation
* Set up domain models for Supplier, Product, ProductSupplier and Catalog
* Implement the catalogs merge by using merging two sorted list
* Separate io operation and business logic
* Lombok automatically generates getter,setter, constructor, hashcode, log etc.
* High test coverage

## Assumptions and TradeOffs
* There is no duplicated barcode and sku in one company
* The order of output is not important as long the results are correct
* Didn't check input of Java application because of tight time
* Didn't handle invalid data from CSV files
* Didn't handle specific exceptions because of tight time 
