package io.namoo.util.expow.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class ExpowCellLibTest {
	//
	private ExpowCellLib cell; 
	
	@Before
	public void setUp() throws Exception {
		//
		this.cell = ExpowCellLib.getSample();
	}

	@Test
	public void testExpowCell() throws Exception {
		//
		assertNotNull(this.cell);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExpowCellWithNullValueWithIllegalArgumentException() {
		//
		int rowIndex = 0; 
		int columnIndex = 0; 
		int cellType = Cell.CELL_TYPE_STRING; 
		new ExpowCellLib(rowIndex, columnIndex, cellType, null); 
	}

	@Test
	public void testGetColumnIndex() throws Exception {
		// 
		assertEquals(0, cell.getColumnIndex()); 
	}

	@Test
	public void testGetValue() throws Exception {
		// 
		assertEquals("Hello", cell.getValue()); 
	}

	@Test
	public void testGetCellTypeAsStr() throws Exception {
		//
		ExpowCellLib cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_STRING, "StringValue"); 
		assertEquals("CELL_TYPE_STRING", cell.getCellTypeAsStr());
		
		cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_BLANK, ""); 
		assertEquals("CELL_TYPE_BLANK", cell.getCellTypeAsStr());

		cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_BOOLEAN, "true"); 
		assertEquals("CELL_TYPE_BOOLEAN", cell.getCellTypeAsStr()); 

		cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_ERROR, ""); 
		assertEquals("CELL_TYPE_ERROR", cell.getCellTypeAsStr());

		cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_FORMULA, ""); 
		assertEquals("CELL_TYPE_FORMULA", cell.getCellTypeAsStr());
		
		cell = new ExpowCellLib(0, 0, Cell.CELL_TYPE_NUMERIC, ""); 
		assertEquals("CELL_TYPE_NUMERIC", cell.getCellTypeAsStr());
	}

	@Test
	public void testToString() throws Exception {
		// 
		assertNotNull(cell.toString());
	}

	@Test
	public void testGetRowIndex() throws Exception {
		//
		assertEquals(0, cell.getRowIndex()); 
	}

	@Test
	public void testToPrettyString() throws Exception {
		// 
		assertNotNull(cell.toPrettyJson()); 
	} 
}