package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private TableauRegles tableauRegles;
    private JButton btnAdd;
    private JTextField txtFieldOrtho;
    private JTextField txtFieldPhono;
    private Database openedDb = null;
    
    private MainWindow() {
        createGUI();
    }

    private void createGUI() {
        getContentPane().setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        tableauRegles = new TableauRegles();
        pane.setViewportView(tableauRegles);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(2,1));
        
        JPanel panel_1 = new JPanel();
        westPanel.add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel textFieldsPanel = new JPanel();
        panel_1.add(textFieldsPanel);
        textFieldsPanel.setLayout(new GridLayout(2, 2, 3, 10));

        @SuppressWarnings("serial")
		Action actionAjouter = new AbstractAction("Send") {
            public void actionPerformed(ActionEvent e) {
                tableauRegles.getModel().addRowAfterSelection(txtFieldOrtho.getText(),txtFieldPhono.getText());
                txtFieldPhono.setText("");
                txtFieldOrtho.grabFocus();
                txtFieldOrtho.selectAll();
            }
        };

        JLabel lblOrtho = new JLabel("Orthographe");
        textFieldsPanel.add(lblOrtho);
        txtFieldOrtho = new JTextField();
        textFieldsPanel.add(txtFieldOrtho);
        
        JLabel lblPhono = new JLabel("Phonétique");
        textFieldsPanel.add(lblPhono);
        txtFieldPhono = new JTextField();
        textFieldsPanel.add(txtFieldPhono);
        txtFieldPhono.setAction(actionAjouter);

        getContentPane().add(westPanel, BorderLayout.WEST);
        
        JPanel panel = new JPanel();
        westPanel.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        btnAdd = new JButton("Ajouter");
        panel.add(btnAdd);
        btnAdd.addActionListener(actionAjouter);
        
        JButton btnSuppr = new JButton("Supprimer");
        panel.add(btnSuppr);
        btnSuppr.addActionListener(new ActionSupprRegle(tableauRegles));
        getContentPane().add(pane,BorderLayout.CENTER);

        JPanel boutonsDico = new JPanel();
        getContentPane().add(boutonsDico, BorderLayout.EAST);
        boutonsDico.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnExport = new JButton("Exporter les mots");
        btnExport.addActionListener(new ActionExportMot(this));
        boutonsDico.add(btnExport);
    }
    
    @Override
    public JMenuBar getJMenuBar () {
    	//Create the menu bar.
    	JMenuBar menuBar = new JMenuBar();

    	//Build the first menu.
    	JMenu menu = new FileMenu(this);
    	menuBar.add(menu);
    	return menuBar;
    }

    public static Runnable getRunnable() {
    	return new Runnable() {
            @Override
            public void run() {
            	// Native Look and feel
//				try {
//					UIManager.setLookAndFeel(UIManager
//							.getSystemLookAndFeelClassName());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
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
					displayError("Erreur à l'ouverture de la base",
							"Impossible d'ouvrir la base de données:\n" + e1.getMessage());

				}
			}		}
		return openedDb;
	}
	
	public void displayError(String title, String description) {
		JOptionPane.showMessageDialog(
				this,
				description,
				title,
				JOptionPane.ERROR_MESSAGE);
	}
}