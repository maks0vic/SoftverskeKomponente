package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class MainFrame extends JFrame{
	
	private static MainFrame instance = null;
	private JSplitPane split;
	private JScrollPane scroll;
	private JPanel buttonPanel;
	private JTable table;
	private Dimension screenSize;
	
	private MainFrame() {
		setSize(800,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Projekat");
		
		table = new JTable();
		scroll = new JScrollPane(table);
		buttonPanel = new JPanel();
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, scroll);
		split.setDividerLocation((int)(this.screenSize.getWidth()/6));
		
		add(split);
		
		validate();
	}
	
	public static MainFrame getInstance() {
		if(instance == null) instance = new MainFrame();
		return instance;
	}
}
