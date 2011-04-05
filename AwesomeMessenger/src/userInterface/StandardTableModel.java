package userInterface;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class StandardTableModel extends AbstractTableModel {


	private Vector<String> columnNames;
	protected Vector<String[]> items;

	public StandardTableModel(Vector<String[]> items, Vector<String> columnNames){
		this.columnNames = columnNames;
		this.items = items;
	}

	@Override
	public String getColumnName(int col){
		return columnNames.get(col);
	}

	@Override
	public boolean isCellEditable(int row, int col){ 
		return false; 
	}

	public void setValueAt(String value, int row, int col) {
		(items.get(row))[col] = value;
		fireTableCellUpdated(row, col);
	}

	@Override
	public int getColumnCount() { return columnNames.size(); }

	@Override
	public int getRowCount() { return items.size(); }

	@Override
	public Object getValueAt(int vectorIndex, int arrayIndex) {
		return (items.get(vectorIndex))[arrayIndex];
	}

	public Vector<String[]> getItems() {
		return items;
	}

	public void setItems(Vector<String[]> items) {
		this.items = items;
		this.fireTableDataChanged();
	}
	

}
