package org.excel2db.browse;

import javax.swing.table.AbstractTableModel;

public class NDBTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Object[] columnNames;
	private Object[][] tableVales;

	public NDBTableModel(Object[] columnNames, Object[][] tableVales) {
		this.columnNames = columnNames;
		this.tableVales = tableVales;
	}

	@Override
	public int getRowCount() {
		return tableVales.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return (String) columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableVales[rowIndex][columnIndex];
	}

}
