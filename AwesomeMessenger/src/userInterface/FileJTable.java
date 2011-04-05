package userInterface;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class FileJTable extends JTable {
	
	public FileJTable(FileTableModel model) {
		super(model);
	}
	
	//Override method to handle mouse overs for tool tip
	public String getToolTipText(MouseEvent event) {
		java.awt.Point loc = event.getPoint();
		int row = rowAtPoint(loc);
		FileTableModel model = (FileTableModel) getModel();
		return model.getDescriptions()[row];
	}
}
