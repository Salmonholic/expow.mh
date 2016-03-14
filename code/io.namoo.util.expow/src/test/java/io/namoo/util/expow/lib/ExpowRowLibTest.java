package io.namoo.util.expow.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.Before;
import org.junit.Test;

import io.namoo.util.expow.api.ExpowCell;

public class ExpowRowLibTest {
	// 
	private ExpowRowLib rowWithSingleCell; 
	private ExpowRowLib rowWithMultiCells; 
	
	@Before
	public void setUp() throws Exception {
		// 
		int rowIndex = 0; 
		int columnIndex = 0; 
		int cellType = Cell.CELL_TYPE_STRING; 
		
		rowWithSingleCell = new ExpowRowLib(rowIndex); 
		rowWithSingleCell.addCell(ExpowCellLib.getSample()); 
		
		rowWithMultiCells = new ExpowRowLib(rowIndex); 
		rowWithMultiCells.addCell(new ExpowCellLib(rowIndex, columnIndex++, cellType, "Hello"));
		rowWithMultiCells.addCell(new ExpowCellLib(rowIndex, columnIndex++, cellType, "My"));
		rowWithMultiCells.addCell(new ExpowCellLib(rowIndex, columnIndex++, cellType, "Friends"));
	}

	@Test
	public void testExpowRow() throws Exception {
		//
		assertNotNull(rowWithSingleCell); 
		assertEquals(1, rowWithSingleCell.countCells()); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpowRowWithMinusWithIllegalArgumentException() throws Exception {
		//
		new ExpowRowLib(-1);  
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpowRowWithLimitWithIllegalArgumentException() throws Exception {
		//
		new ExpowRowLib(ExpowRowLib.IndexLimit+1); 
	}

	@Test
	public void testRequestCellString() throws Exception {
		//
		ExpowCell cell = rowWithSingleCell.requestCell("Hello"); 
		
		assertNotNull(cell); 
		assertEquals("Hello", cell.getValue()); 
	}

	@Test
	public void testRequestRightCellOf() throws Exception {
		//
		ExpowCell rightCell = rowWithMultiCells.requestRightCellOf("Hello");
		assertEquals("My", rightCell.getValue()); 
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRequestCellStringWithNoSuchElementException() throws Exception {
		//
		rowWithSingleCell.requestCell("Nothing"); 
	}

	@Test
	public void testRequestCellStringInMulti() throws Exception {
		//
		ExpowCell cell = rowWithMultiCells.requestCell("My"); 
		
		assertNotNull(cell); 
		assertEquals("My", cell.getValue()); 
	}

	@Test
	public void testRequestCellsFrom() throws Exception {
		//
		ListIterator<ExpowCell> cellIter = rowWithMultiCells.requestCellsFrom("My"); 
		assertEquals("Friends", cellIter.next().getValue()); 
	}

	@Test(expected=NoSuchElementException.class)
	public void testRequestCellsFromWithNoSuchElementException() throws Exception {
		//
		ListIterator<ExpowCell> cellIter = rowWithMultiCells.requestCellsFrom("Ghost"); 
	} 
	
	@Test
	public void testHasCellValue() throws Exception {
		//
		assertTrue(rowWithMultiCells.hasCellValue("My")); 
		assertTrue(rowWithMultiCells.hasCellValue("Friends")); 
	}

	@Test
	public void testHasCellValueWithFalse() throws Exception {
		//
		assertFalse(rowWithMultiCells.hasCellValue("Your")); 
		assertFalse(rowWithMultiCells.hasCellValue("Enemy")); 
	}

	@Test
	public void testHasCellValueAt() throws Exception {
		//
		assertTrue(rowWithMultiCells.hasCellValueAt(0, "Hello")); 
		assertTrue(rowWithMultiCells.hasCellValueAt(1, "My")); 
		assertTrue(rowWithMultiCells.hasCellValueAt(2, "Friends")); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testHasCellValueAtWithIllegalArgumentException() throws Exception {
		//
		assertTrue(rowWithMultiCells.hasCellValueAt(0, null)); 
	}

	@Test
	public void testHasCellValueAtWithFalse() throws Exception {
		//
		assertFalse(rowWithMultiCells.hasCellValueAt(0, "Olleh")); 
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testHasCellValueAtWithIndexOutOfBoundException() throws Exception {
		//
		assertFalse(rowWithMultiCells.hasCellValueAt(5, "My")); 
	}

	@Test
	public void testRequestCellInt() throws Exception {
		// 
		assertEquals("Hello", rowWithMultiCells.requestCell(0).getValue()); 
		assertEquals("My", rowWithMultiCells.requestCell(1).getValue()); 
		assertEquals("Friends", rowWithMultiCells.requestCell(2).getValue()); 
	}

	@Test
	public void testAddCell() throws Exception {
		// 
		rowWithSingleCell.addCell(new ExpowCellLib(0, 1, Cell.CELL_TYPE_STRING, "AddedCell"));
		assertTrue(rowWithSingleCell.hasCellValueAt(1, "AddedCell")); 
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddCellWithIllegalArgumentException() {
		// 
		rowWithSingleCell.addCell(null);
	}

	@Test
	public void testCountCells() throws Exception {
		// 
		assertEquals(3, rowWithMultiCells.countCells()); 
	}

	@Test
	public void testGetRowIndex() throws Exception {
		//
		assertEquals(0, rowWithSingleCell.getRowIndex()); 
	}

	@Test
	public void testToString() throws Exception {
		// 
		assertNotNull(rowWithSingleCell.toString()); 
	}

	@Test
	public void testToPrettyString() throws Exception {
		// 
		assertNotNull(rowWithSingleCell.toPrettyJson()); 
	}

}