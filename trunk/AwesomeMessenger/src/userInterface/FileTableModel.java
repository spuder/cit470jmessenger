package userInterface;


import java.util.Vector;


import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class FileTableModel extends AbstractTableModel {


	private Vector<String> columnNames;
	private Vector<String[]> files;

	public FileTableModel(Vector<String[]> files, Vector<String> columnNames){
		this.columnNames = columnNames;
		this.files = files;
	}

	public String getColumnName(int col){
		return columnNames.get(col);
	}

	public boolean isCellEditable(int row, int col){ 
		return false; 
	}

	public void setValueAt(String value, int row, int col) {
		(files.get(row))[col] = value;
		fireTableCellUpdated(row, col);
	}

	@Override
	public int getColumnCount() { return columnNames.size(); }

	@Override
	public int getRowCount() { return files.size(); }

	@Override
	public Object getValueAt(int vectorIndex, int arrayIndex) {
		return (files.get(vectorIndex))[arrayIndex];
	}

}