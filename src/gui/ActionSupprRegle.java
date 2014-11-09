package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

public final class ActionSupprRegle extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private TableauRegles table;

	/**
	 * @param table
	 */
	ActionSupprRegle(TableauRegles table) {
		super("Supprimer regle");
		this.table = table;
        putValue(SHORT_DESCRIPTION, "Supprimer la règle");
        putValue(ACCELERATOR_KEY, KeyEvent.VK_DELETE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		table.removeRows(table.getSelectedRows());
	}
}