# IDX PDF Util API

## Description:
IDX PDF Util API is a REST service as a Spring Boot Application providing some useful operations on PDF files using iText library.

Operations:
* Extracting attachments from pdf
* Merging multiple pdfs into one
* Stamping watermarks on pdf
* Adding folding lines
* Attaching files
* Paging


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
