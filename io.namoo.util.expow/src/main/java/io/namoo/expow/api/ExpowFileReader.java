package io.namoo.expow.api;

public interface ExpowFileReader {

	ArrayFile readAsArray(String fileName);

	ExpowFile read(String fileName);

	ExpowFile read(String fileName, int sheetIndex);

	ExpowFile read(String fileName, int sheetIndex, int startIndex, int endIndex);

}