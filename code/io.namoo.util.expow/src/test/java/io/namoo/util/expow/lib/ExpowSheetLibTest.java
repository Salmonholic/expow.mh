/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoo.util.expow.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpowSheetLibTest {
	//
	private ExpowSheetLib sheetSimple; 
	private ExpowSheetLib sheetMulti; 
	
	@Before
	public void setUp() throws Exception {
		//
		this.sheetSimple = new ExpowSheetLib(0, "Software"); 
		this.sheetMulti = new ExpowSheetLib(0, "Software"); 
		this.sheetMulti.addRow(ExpowRowLib.getSample(0)); 
	}

	@Test
	public void testExpowSheet() throws Exception {
		// 
		assertNotNull(sheetSimple); 
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testExpowSheetWithMinusWithIllegalArgumentException() {
		// 
		new ExpowSheetLib(-1, "Software");
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testExpowSheetWithNullWithIllegalArgumentException() {
		// 
		new ExpowSheetLib(0, null);
	}

	@Test
	public void testToString() throws Exception {
		//
		assertNotNull(sheetSimple.toString()); 
	}

	@Test
	public void testToPrettyString() throws Exception {
		//
		assertNotNull(sheetSimple.toPrettyJson()); 
	}

	@Test
		public void testGetSheetIndex() throws Exception {
			//
			assertEquals(0, sheetSimple.getSheetIndex());
		}

	@Test
		public void testGetSheetName() throws Exception {
			// 
			assertEquals("Software", sheetSimple.getSheetName());
		}

	@Test
	public void testCountRow() throws Exception {
		//
		assertEquals(0, sheetSimple.countRow()); 
		
		ExpowRowLib rowMock = mock(ExpowRowLib.class); 
		
		sheetSimple.addRow(rowMock); 
		assertEquals(1, sheetSimple.countRow());
	}

	@Test
	public void testCountColumn() throws Exception {
		//
		assertEquals(0, sheetSimple.countColumn()); 
		
		sheetSimple.addColumn(mock(ExpowColumnLib.class)); 
		assertEquals(1, sheetSimple.countColumn());
	}

	@Test
	public void testGetRowKey() throws Exception {
		// 
		ExpowSheetLib sheetMock = mock(ExpowSheetLib.class); 
		assertNull(sheetMock.getRowKey()); 
		
		when(sheetMock.getRowKey()).thenReturn("key");
		assertNotNull(sheetMock.getRowKey());
		assertEquals("key", sheetMock.getRowKey());
	}
	
	@Test
	public void testSetRowKey() throws Exception {
		//
		assertNull(sheetMulti.getRowKey()); 
		
		sheetMulti.setRowKey("My");
		assertNotNull(sheetMulti.getRowKey());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetRowKeyWithIllegalArgumentException() {
		//
		assertNull(sheetMulti.getRowKey());  
		sheetMulti.setRowKey("NoOne");
	}

	@Test
	public void testRequestRowKeyRow() throws Exception {
		//
		assertNull(sheetMulti.getRowKey()); 
		
		sheetMulti.setRowKey("My");
		assertNotNull(sheetMulti.requestRowKeyRow("My")); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRequestRowKeyRowWithIllegalArgumentException() throws Exception {
		//
		assertNull(sheetMulti.getRowKey()); 
		sheetMulti.requestRowKeyRow(null); 
	}

	@Test
	public void testAddRow() throws Exception {
		//
		assertEquals(0, sheetSimple.countRow()); 
		
		sheetSimple.addRow(new ExpowRowLib(0)); 
		assertEquals(1, sheetSimple.countRow());
	}

	@Test
	public void testAddColumn() throws Exception {
		// 
		assertEquals(0, sheetSimple.countColumn()); 
		
		sheetSimple.addColumn(new ExpowColumnLib(0)); 
		assertEquals(1, sheetSimple.countColumn());
	}

	@Test
	public void testRequestRow() throws Exception {
		// 
		assertNotNull(sheetMulti.requestRow(0)); 
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testRequestRowWithIndexOutOfBoundsException() {
		// 
		sheetMulti.requestRow(10); 
	}
}