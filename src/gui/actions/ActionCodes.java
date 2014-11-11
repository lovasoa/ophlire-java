package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ActionCodes implements ActionListener {
	public ActionCodes() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frm = createGUI();
		frm.setSize(600, 750);
		frm.setVisible(true);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private JFrame createGUI() {
		JFrame frm = new JFrame("Codes phonémiques");
		try {
			JScrollPane scrollPane = new JScrollPane();
			frm.getContentPane().add(scrollPane);
			JEditorPane txt = new JEditorPane(this.getClass().getResource("codes_phonemiques.html"));
			txt.setEditable(false);
			frm.getContentPane().add(txt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frm.pack();
		return frm;
	}

}
