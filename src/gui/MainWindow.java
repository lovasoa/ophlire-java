package gui;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame{
    private TableauRegles table;
    private JButton btnAdd;
    private JTextField txtFieldOrtho;
    private JTextField txtFieldPhono;

    private MainWindow() {
        createGUI();
    }

    private void createGUI() {
        getContentPane().setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new TableauRegles();
        pane.setViewportView(table);
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(2,1));
        
        JPanel panel_1 = new JPanel();
        westPanel.add(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel textFieldsPanel = new JPanel();
        panel_1.add(textFieldsPanel);
        textFieldsPanel.setLayout(new GridLayout(2, 2, 3, 10));

        Action actionAjouter = new AbstractAction("Send") {
            public void actionPerformed(ActionEvent e) {
                table.getModel().addRowAfterSelection(txtFieldOrtho.getText(),txtFieldPhono.getText());
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
        btnSuppr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                table.getModel().removeRows(table.getSelectedRows());
            }
        });
        getContentPane().add(pane,BorderLayout.CENTER);
    }
    
    @Override
    public JMenuBar getJMenuBar () {
    	//Create the menu bar.
    	JMenuBar menuBar = new JMenuBar();

    	//Build the first menu.
    	JMenu menu = new FileMenu();
    	menuBar.add(menu);
    	return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	// Native Look and feel
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
            	MainWindow frm = new MainWindow();
                frm.setLocationByPlatform(true);
                frm.setJMenuBar(frm.getJMenuBar());
                frm.pack();
                frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frm.setVisible(true);
            }

        });
    }
} 