package com.jtableeditor;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jtableeditor.core.*;

class TableEditorTableModel extends AbstractTableModel {

	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int SALARY_COL = 3;

	private String[] columnNames = { "Last Name", "First Name", "Email",
			"Salary" };
	private List<Table> sessions;

	public TableEditorTableModel(List<Table> theSessions) {
		sessions = theSessions;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return sessions.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Table tempTable = sessions.get(row);

		switch (col) {
		case LAST_NAME_COL:
			return tempTable.getLastName();
		case FIRST_NAME_COL:
			return tempTable.getFirstName();
		case EMAIL_COL:
			return tempTable.getEmail();
		case SALARY_COL:
			return tempTable.getSalary();
		default:
			return tempTable.getLastName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
