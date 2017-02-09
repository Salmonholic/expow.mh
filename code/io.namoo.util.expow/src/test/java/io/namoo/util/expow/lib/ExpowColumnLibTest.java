package io.namoo.util.expow.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.Before;
import org.junit.Test;

import io.namoo.util.expow.api.ExpowCell;

@SuppressWarnings("deprecation")
public class ExpowColumnLibTest {
	//
	private ExpowColumnLib columnWithSingleCell; 
	private ExpowColumnLib columnWithMultiCells; 
	
	@Before
	public void setUp() throws Exception {
		// 
		int columnIndex = 0; 
		int cellType = Cell.CELL_TYPE_STRING;
		
		this.columnWithSingleCell = new ExpowColumnLib(columnIndex); 
		this.columnWithSingleCell.addCell(ExpowCellLib.getSample());
		
		this.columnWithMultiCells = new ExpowColumnLib(columnIndex); 
		this.columnWithMultiCells.addCell(new ExpowCellLib(0,0,cellType, "Hello")); 
		this.columnWithMultiCells.addCell(new ExpowCellLib(1,0,cellType, "Hey")); 
		this.columnWithMultiCells.addCell(new ExpowCellLib(2,0,cellType, "Hi")); 
	}

	@Test
	public void testExpowColumn() throws Exception {
		// 
		assertNotNull(columnWithSingleCell); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpowColumnWithMinusIllegalArgumentException() throws Exception {
		// 
		new ExpowColumnLib(-1); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpowColumnWithMaxLimitIllegalArgumentException() throws Exception {
		// 
		new ExpowColumnLib(ExpowColumnLib.IndexLimit+1); 
	}

	@Test
	public void testToString() throws Exception {
		//
		assertNotNull(columnWithSingleCell.toString());
	}

	@Test
	public void testToPrettyString() throws Exception {
		//
		assertNotNull(columnWithSingleCell.toPrettyJson());
	}

	@Test
	public void testRequestCellString() throws Exception {
		//
		assertNotNull(columnWithMultiCells.requestCell("Hi")); 
		assertEquals("Hi", columnWithMultiCells.requestCell("Hi").getValue()); 
	}

	@Test(expected=NoSuchElementException.class) 
	public void testRequestCellStringWithNoSuchElementException() throws Exception {
		//
		columnWithMultiCells.requestCell("Nothing"); 
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testRequestCellStringWithIllegalArgumentException() throws Exception {
		//
		columnWithMultiCells.requestCell(null); 
	}

	@Test
	public void testRequestCellsFrom() throws Exception {
		//
		ListIterator<ExpowCell> cellIter = columnWithMultiCells.requestCellsFrom("Hey");
		
		assertNotNull(cellIter); 
		assertEquals("Hi", cellIter.next().getValue()); 
	}

	@Test(expected=NoSuchElementException.class) 
	public void testRequestCellsFromWithNoSuchElementException() throws Exception {
		//
		columnWithMultiCells.requestCellsFrom("Nothing");
	}

	@Test
	public void testHasCellValue() throws Exception {
		//
		assertTrue(columnWithMultiCells.hasCellValue("Hi")); 
		assertTrue(columnWithMultiCells.hasCellValue("Hey")); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testHasCellValueWithIllegalArgumentExcetption() throws Exception {
		//
		columnWithMultiCells.hasCellValue(null); 
	}

	@Test
	public void testHasCellValueAt() throws Exception {
		// 
		assertTrue(columnWithMultiCells.hasCellValueAt(2, "Hi")); 
		assertTrue(columnWithMultiCells.hasCellValueAt(1, "Hey")); 
	}

	@Test
	public void testHasCellValueAtWithFalse() throws Exception {
		// 
		columnWithMultiCells.hasCellValueAt(2, "Nothing"); 
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testHasCellValueAtWithIndexOutOfBoundException() throws Exception {
		// 
		columnWithMultiCells.hasCellValueAt(7, "Hi"); 
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testHasCellValueAtWithIllegalArguementException() throws Exception {
		// 
		columnWithMultiCells.hasCellValueAt(2, null); 
	}

	@Test
	public void testRequestCellInt() throws Exception {
		//
		assertNotNull(columnWithSingleCell.hasCellValue("Hello")); 
		assertNotNull(columnWithMultiCells.hasCellValue("Hay")); 
		assertNotNull(columnWithMultiCells.hasCellValue("Hi")); 
	}

	@Test
	public void testAddCell() throws Exception {
		//
		int cellCount = columnWithMultiCells.countCells(); 
		columnWithMultiCells.addCell(new ExpowCellLib(0, cellCount, Cell.CELL_TYPE_STRING, "AddedCell")); 
		
		assertEquals(cellCount+1, columnWithMultiCells.countCells());
		assertEquals("AddedCell", columnWithMultiCells.requestCell("AddedCell").getValue());
	}

	@Test(expected=IllegalArgumentException.class) 
	public void testAddCellWithIllegalArgumentException() throws Exception {
		//
		columnWithMultiCells.addCell(null); 
	}

	@Test
	public void testCountCells() throws Exception {
		//
		assertEquals(1, columnWithSingleCell.countCells()); 
		assertEquals(3, columnWithMultiCells.countCells()); 
	}

	@Test
	public void testGetIndex() throws Exception {
		//
		assertEquals(0, columnWithSingleCell.getIndex()); 
	}
}
