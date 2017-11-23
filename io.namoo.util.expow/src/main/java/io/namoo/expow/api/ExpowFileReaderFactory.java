package io.namoo.expow.api;

import io.namoo.expow.lib.ExpowFileReaderLib;

public class ExpowFileReaderFactory {
	//
	public static ExpowFileReader newInstance() {
		// 
		return new ExpowFileReaderLib(); 
	}
}