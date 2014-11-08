package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.ScrollPaneConstants;

public class AppWindow {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel boutons = new JPanel();
		frame.getContentPane().add(boutons, BorderLayout.NORTH);
		boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnChargerUneBase = new JButton("Charger une base");
		boutons.add(btnChargerUneBase);
		
		JButton btnSauvegarderLaBase = new JButton("Sauvegarder la base");
		boutons.add(btnSauvegarderLaBase);
		
		JPanel regles = new JPanel();
		frame.getContentPane().add(regles, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		regles.add(scrollPane);

		  AbstractTableModel dataModel = new AbstractTableModel() {
		      public int getColumnCount() { return 3; }
		      public int getRowCount() { return 10;}
		      public Object getValueAt(int row, int col) { return "LOL"; }
		      public String getColumnName(int col){
		    	  switch (col) {
				case 0:
					return "Règle";
				case 1:
					return "Actif";
				default:
					return "NoName";
				}
		      }
		  };
		table = new JTable(dataModel);
		table.getColumn("Actif").setCellRenderer(
				new TableCellRenderer() {
					
					@Override
					public Component getTableCellRendererComponent(JTable t, Object val,
							boolean isSelected, boolean hasFocus, int row, int col) {
						return new JCheckBox();
					}
				}
				);
		scrollPane.setViewportView(table);
	}

}
