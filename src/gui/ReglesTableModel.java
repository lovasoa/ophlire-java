package gui;

import javax.swing.table.AbstractTableModel;

import phonetique.ReglePhonetique;
import phonetique.RegleRegex;
import phonetique.RegleSubstitution;
import data.ListeRegles;

public class ReglesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ListeRegles liste;
	private TableauRegles table;

	public ReglesTableModel(TableauRegles table) {
		super();
		this.liste = new ListeRegles();
		this.table = table;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Orthographe";
		case 1:
			return "Phonétique";
		default:
			return "No Name";
		}
	}

	@Override
	public int getRowCount() {
		return liste.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO : rendre le tableau éditable
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ReglePhonetique regle = liste.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return regle.getOrtho();
		case 1:
			return regle.getPhono();
		default:
			return null;
		}
	}
	
	public void addRow(ReglePhonetique r, int pos) {
		liste.add(pos, r);
		this.fireTableRowsInserted(pos, pos);
		this.table.selectRow(pos);
	}

	public void addRow(String ortho, String phono, int pos) {
		ReglePhonetique r;
		if (ortho.startsWith("/") && ortho.endsWith("/")) {
			// REGEX
			r = new RegleRegex(ortho.substring(1, ortho.length()-1), phono);
		} else {
			// Normal replacement
			r = new RegleSubstitution(ortho, phono);
		}
		addRow(r, pos);
	}
	
	public void addRowAfterSelection (String ortho, String phono) {
		int[] selected = this.table.getSelectedRows();
		int last = (selected.length == 0) ? this.getListe().size() : selected[selected.length-1]+1;
		addRow(ortho, phono, last);
	}

	public void removeRow(int pos) {
		liste.remove(pos);
		fireTableRowsDeleted(pos, pos);
		if (pos < getRowCount()) table.selectRow(pos);
	}

	public void removeRows(int[] pos) {
		for (int i:pos) {
			removeRow(i);
		}
	}

	public ListeRegles getListe() {
		return liste;
	}

	public void setListe(ListeRegles liste) {
		assert(liste != null);
		this.liste = liste;
		this.fireTableDataChanged();
	}

	public TableauRegles getTable() {
		return table;
	}
}
