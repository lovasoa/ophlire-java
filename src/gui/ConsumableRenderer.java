package gui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import lecteurString.ConsumableRegex;

public class ConsumableRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object val,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String repr = val.toString();
		// Write regexes in bold
		if (val instanceof ConsumableRegex) {
			repr = "/" + val.toString() + "/";
		}
		return super.getTableCellRendererComponent(table, repr, isSelected, hasFocus, row, column);
	}
}
