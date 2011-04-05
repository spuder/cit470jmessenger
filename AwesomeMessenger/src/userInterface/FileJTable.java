package userInterface;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class FileJTable extends JTable {
	//Override method to handle mouse overs for tool tip
	public String getToolTipText(MouseEvent event) {
		java.awt.Point loc = getToolTipLocation(event);
		int row = rowAtPoint(loc);
		return null;
	}
}
