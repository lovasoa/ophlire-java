package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Database;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private TableauRegles tableauRegles;
	private JButton btnAdd;
	private JTextField txtFieldOrtho;
	private JTextField txtFieldPhono;
	private Database openedDb = null;
	private JPanel boutonsDico;

	private MainWindow() {
		createGUI();
	}

	private void createGUI() {
		getContentPane().setLayout(new BorderLayout());
		JScrollPane pane = new JScrollPane();
		tableauRegles = new TableauRegles();
		pane.setViewportView(tableauRegles);
		JPanel westPanel = new JPanel();

		@SuppressWarnings("serial")
		Action actionAjouter = new AbstractAction("Send") {
			public void actionPerformed(ActionEvent e) {
				tableauRegles.getModel().addRowAfterSelection(
						txtFieldOrtho.getText(), txtFieldPhono.getText());
				txtFieldPhono.setText("");

				txtFieldOrtho.grabFocus();
				txtFieldOrtho.selectAll();
			}
		};
		westPanel.setLayout(new GridBagLayout());

		JLabel lblOrtho = new JLabel("Orthographe");
		GridBagConstraints glbl_lblOrtho = new GridBagConstraints();
		glbl_lblOrtho.insets = new Insets(0, 0, 5, 5);
		glbl_lblOrtho.gridy = 0;
		glbl_lblOrtho.gridx = 0;
		westPanel.add(lblOrtho, glbl_lblOrtho);
		txtFieldOrtho = new JTextField();
		GridBagConstraints gbc_txtFieldOrtho = new GridBagConstraints();
		gbc_txtFieldOrtho.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldOrtho.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldOrtho.gridx = 1;
		gbc_txtFieldOrtho.gridy = 0;
		westPanel.add(txtFieldOrtho, gbc_txtFieldOrtho);

		JLabel lblPhono = new JLabel("Phonétique");
		GridBagConstraints gbc_lblPhono = new GridBagConstraints();
		gbc_lblPhono.fill = GridBagConstraints.BOTH;
		gbc_lblPhono.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhono.gridx = 0;
		gbc_lblPhono.gridy = 1;
		westPanel.add(lblPhono, gbc_lblPhono);
		txtFieldPhono = new JTextField();
		GridBagConstraints gbc_txtFieldPhono = new GridBagConstraints();
		gbc_txtFieldPhono.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPhono.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldPhono.gridx = 1;
		gbc_txtFieldPhono.gridy = 1;
		westPanel.add(txtFieldPhono, gbc_txtFieldPhono);
		txtFieldPhono.setAction(actionAjouter);

		btnAdd = new JButton("Ajouter");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.weighty = 1.0;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 3;
		westPanel.add(btnAdd, gbc_btnAdd);
		btnAdd.addActionListener(actionAjouter);

		getContentPane().add(westPanel, BorderLayout.WEST);

		JButton btnSuppr = new JButton("Supprimer");
		GridBagConstraints gbc_btnSuppr = new GridBagConstraints();
		gbc_btnSuppr.anchor = GridBagConstraints.NORTH;
		gbc_btnSuppr.weighty = 1.0;
		gbc_btnSuppr.gridx = 1;
		gbc_btnSuppr.gridy = 3;
		westPanel.add(btnSuppr, gbc_btnSuppr);
		btnSuppr.addActionListener(new ActionSupprRegle(tableauRegles));
		getContentPane().add(pane, BorderLayout.CENTER);

		boutonsDico = new JPanel();
		getContentPane().add(boutonsDico, BorderLayout.EAST);
		boutonsDico.setLayout(new GridBagLayout());

		JLabel lblNombreLettres = new JLabel("Nombre de lettres :");
		GridBagConstraints gbc_lblNombreLettres = new GridBagConstraints();
		gbc_lblNombreLettres.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombreLettres.gridwidth = 5;
		gbc_lblNombreLettres.anchor = GridBagConstraints.WEST;
		gbc_lblNombreLettres.gridx = 0;
		gbc_lblNombreLettres.gridy = 0;
		boutonsDico.add(lblNombreLettres, gbc_lblNombreLettres);

		JLabel label = new JLabel("min:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		boutonsDico.add(label, gbc_label);



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
		boutonsDico.add(txtFieldMinLettres, gbc_textField);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 1;
		boutonsDico.add(separator, gbc_separator);

		JLabel label_1 = new JLabel("max:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.weightx = 1.0;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 1;
		boutonsDico.add(label_1, gbc_label_1);

		JTextField txtFieldNbrLettresMax = new JTextField();
		txtFieldNbrLettresMax.setText("10");
		txtFieldNbrLettresMax.setColumns(2);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 1;
		boutonsDico.add(txtFieldNbrLettresMax, gbc_textField_1);
		
		JButton btnExport = new JButton("Exporter les mots");
		btnExport.setVerticalAlignment(SwingConstants.TOP);
		btnExport.setMnemonic('x');
		btnExport.addActionListener(new ActionExportMot(this));
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.gridwidth = 5;
		gbc_btnExport.weighty = 1.0;
		gbc_btnExport.anchor = GridBagConstraints.WEST;
		gbc_btnExport.gridx = 0;
		gbc_btnExport.gridy = 2;
		boutonsDico.add(btnExport, gbc_btnExport);
	}

	@Override
	public JMenuBar getJMenuBar() {
		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		// Build the first menu.
		JMenu menu = new FileMenu(this);
		menuBar.add(menu);
		return menuBar;
	}

	public static Runnable getRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				// Native Look and feel
				// try {
				// UIManager.setLookAndFeel(UIManager
				// .getSystemLookAndFeelClassName());
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				MainWindow frm = new MainWindow();
				frm.setLocationByPlatform(true);
				frm.setJMenuBar(frm.getJMenuBar());
				frm.pack();
				frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frm.setVisible(true);
			};
		};
	}

	public TableauRegles getTableauRegles() {
		return tableauRegles;
	}

	public Database getDatabase() {
		if (openedDb == null) {
			// Choix d'un fichier de base de données
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Base de données de mots et règles orthographiques");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Base de données SQLite de dictionnaire et règles de prononciation",
					"db");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					openedDb = new Database(chooser.getSelectedFile());
				} catch (ClassNotFoundException | SQLException e1) {
					displayError(
							"Erreur à l'ouverture de la base",
							"Impossible d'ouvrir la base de données:\n"
									+ e1.getMessage());

				}
			}
		}
		return openedDb;
	}

	public void displayError(String title, String description) {
		JOptionPane.showMessageDialog(this, description, title,
				JOptionPane.ERROR_MESSAGE);
	}
}