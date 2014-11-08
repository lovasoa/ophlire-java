package gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableauRegles extends JTable {
    private ReglesTableModel tableModel;

	public TableauRegles() {
		super();
	    tableModel = new ReglesTableModel(this);
	    this.setModel(tableModel);
	}
	
	
	private static final long serialVersionUID = 1L;

	public ReglesTableModel getModel() {
		return tableModel;
	}

	public void selectRow(int last) {
		this.clearSelection();
		this.addRowSelectionInterval(last, last);
	}
}
