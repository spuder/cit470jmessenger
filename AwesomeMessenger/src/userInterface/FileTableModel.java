package userInterface;


import java.util.Iterator;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class FileTableModel extends StandardTableModel {
	
	private String[] descriptions;

	public FileTableModel(Vector<String[]> items, Vector<String> columnNames) {
		super(items, columnNames);
		Iterator<String[]> iter = items.iterator();
		Vector<String[]> newItems = new Vector<String[]>();
		descriptions = new String[items.size()];
		int i = 0;
		while(iter.hasNext()) {
			String[] columns = iter.next();
			String[] newColumns = {columns[0], columns[1], columns[3]};
			newItems.add(newColumns);
			descriptions[i] = columns[2];
			i++;
		}
		this.items = newItems;
		this.fireTableDataChanged();
	}
	
	public void setItems(Vector<String[]> items) {
		Iterator<String[]> iter = items.iterator();
		Vector<String[]> newItems = new Vector<String[]>();
		while(iter.hasNext()) {
			String[] columns = iter.next();
			String[] newColumns = {columns[0], columns[1], columns[3]};
			newItems.add(newColumns);
		}
		this.items = newItems;
		this.fireTableDataChanged();
	}
	

	@Override
	public Object getValueAt(int vectorIndex, int arrayIndex) throws ArrayIndexOutOfBoundsException{
		return (items.get(vectorIndex))[arrayIndex];
	}

	public String[] getDescriptions() {
		return descriptions;
	}

}