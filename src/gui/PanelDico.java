package gui;

import gui.actions.ActionExportMot;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelDico extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;

	public PanelDico() {super();createGUI();}
	public PanelDico(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
		createGUI();
	}

	private void createGUI() {
		setLayout(new GridBagLayout());

		JLabel lblNombreLettres = new JLabel("Nombre de lettres :");
		GridBagConstraints gbc_lblNombreLettres = new GridBagConstraints();
		gbc_lblNombreLettres.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombreLettres.gridwidth = 5;
		gbc_lblNombreLettres.anchor = GridBagConstraints.WEST;
		gbc_lblNombreLettres.gridx = 0;
		gbc_lblNombreLettres.gridy = 0;
		add(lblNombreLettres, gbc_lblNombreLettres);

		JLabel label = new JLabel("min:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);

		JTextField txtFieldMinLettres = new JTextField();
		txtFieldMinLettres
				.setToolTipText("nombre minimum de lettres que doit avoir un mot pour être exporté");
		txtFieldMinLettres.setText("3");
		txtFieldMinLettres.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldMinLettres.setColumns(2);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(txtFieldMinLettres, gbc_textField);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);

		JLabel label_1 = new JLabel("max:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.weightx = 1.0;
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 1;
		add(label_1, gbc_label_1);

		JTextField txtFieldNbrLettresMax = new JTextField();
		txtFieldNbrLettresMax.setText("10");
		txtFieldNbrLettresMax.setColumns(2);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 1;
		add(txtFieldNbrLettresMax, gbc_textField_1);

		JButton btnExport = new JButton("Exporter les mots");
		btnExport.setVerticalAlignment(SwingConstants.TOP);
		btnExport.setMnemonic('x');
		btnExport.addActionListener(new ActionExportMot(this.mainWindow));
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.gridwidth = 5;
		gbc_btnExport.weighty = 1.0;
		gbc_btnExport.anchor = GridBagConstraints.WEST;
		gbc_btnExport.gridx = 0;
		gbc_btnExport.gridy = 2;
		add(btnExport, gbc_btnExport);
	}

}
