package gui;

import gui.actions.ActionSupprRegle;

import javax.swing.JTable;
import javax.swing.KeyStroke;

import phonetique.ListeRegles;

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
	
	public ListeRegles getListeRegles(){
		return tableModel.getListe();
	}

	public ListeRegles getSelectedRegles(){
		int[] selection = convertRowIndexesToModel(getSelectedRows());
		ListeRegles liste = new ListeRegles();
		for (int i:selection){
			liste.add(tableModel.getRowAt(i));
		}
		return liste;
	}

	private int[] convertRowIndexesToModel(int[] rows) {
		int[] res = new int[rows.length];
		for (int i=0;i<res.length;i++){
			res[i] = convertRowIndexToModel(rows[i]);
		}
		return res;
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
