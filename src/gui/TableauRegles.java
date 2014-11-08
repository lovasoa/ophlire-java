package gui;

import javax.swing.JTable;

public class TableauRegles extends JTable {
    private ReglesTableModel tableModel;

	public TableauRegles() {
		super();
	    tableModel = new ReglesTableModel(this);
	    this.setModel(tableModel);
	    this.setDefaultRenderer(Object.class, new ConsumableRenderer());
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
