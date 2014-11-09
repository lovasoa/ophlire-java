package gui;

import gui.actions.ActionSupprRegle;

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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Database;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private TableauRegles tableauRegles;
	private JButton btnAdd;
	private JTextField txtFieldOrtho;
	private JTextField txtFieldPhono;
	private Database openedDb = null;
	private PanelDico panelDico;

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

		panelDico = new PanelDico(this);
		getContentPane().add(panelDico, BorderLayout.EAST);
	}

	public PanelDico getPanelDico() {
		return this.panelDico;
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