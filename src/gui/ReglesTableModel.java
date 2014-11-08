package gui;

import javax.swing.table.AbstractTableModel;

import phonetique.ReglePhonetique;
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
	
	public void addRow(String ortho, String phono) {
		liste.add(new RegleSubstitution(ortho, phono));
		this.fireTableRowsInserted(getRowCount(), getRowCount());
	}
	
	public void addRowAfterSelection (String ortho, String phono) {
		int[] selected = this.table.getSelectedRows();
		int last = (selected.length == 0) ? this.getListe().size() : selected[selected.length-1]+1;
		liste.add(last, new RegleSubstitution(ortho, phono));
		this.fireTableRowsInserted(last, last);
		this.table.selectRow(last);
	}

	public void removeRow(int pos) {
		liste.remove(pos);
		this.fireTableRowsDeleted(pos, pos);
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
