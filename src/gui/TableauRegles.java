package gui;

import javax.swing.JTable;
import javax.swing.KeyStroke;

public class TableauRegles extends JTable {
	private ReglesTableModel tableModel;

	public TableauRegles() {
		super();
		tableModel = new ReglesTableModel(this);
		this.setModel(tableModel);
		this.setDefaultRenderer(Object.class, new ConsumableRenderer());
		this.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "deleteLine");
		this.getActionMap().put("deleteLine", new ActionSupprRegle(this));
	}

	private static final long serialVersionUID = 1L;

	public ReglesTableModel getModel() {
		return tableModel;
	}

	public void selectRow(int last) {
		if (last >= tableModel.getRowCount()) last = tableModel.getRowCount()-1;
		if (last < 0) return;
		this.clearSelection();
		this.addRowSelectionInterval(last, last);
	}

	public void removeRow(int pos) {
		tableModel.removeElem(pos);
		selectRow(pos);
	}

	public void removeRows(int[] pos) {
		int deleted = 0;
		for (int i : pos) {
			removeRow(i-deleted);
			deleted++;
		}
	}
}
