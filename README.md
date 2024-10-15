# IDX PDF Util API

## Description:
IDX PDF Util API is a REST service as a Spring Boot Application providing some useful operations on PDF files using iText library.

Operations:
* Count pages of the pdf document
* Get a page of the pdf document by page number
* Adding folding lines
* see the openapi specification for further information: idx-pdfutil-api-rest/src/main/resources/static/openapi.yml


## Prerequisites:
Java 17
Gradle (Gradle wrapper included)

iText JDK Vendor compatibility: Open JRE 17.

Please find more details below
https://kb.itextpdf.com/2data/requirements-and-prerequisites


## Usages:
# Build in the root folder
Build and run as a usual Spring Boot application

### Build and run
```bash
./gradlew build
```
Then run the resulting jar file

### Or build and run as "bootJar"
```bash
./gradlew bootJar
```
Then run the resulting jar file

### Or simply run with "bootRun"
```bash
./gradlew bootRun
```
