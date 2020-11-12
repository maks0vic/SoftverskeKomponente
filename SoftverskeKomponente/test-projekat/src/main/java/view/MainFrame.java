package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.komponente.DataRepositoryJson;
import com.komponente.Entity;
import com.komponente.Storage;

import actions.AddDialogAction;
import actions.CreateNewRepositoryAction;
import actions.DeleteAction;
import actions.OpenFileChooserAction;
import actions.SortDialogAction;

public class MainFrame extends JFrame{
	
	private static MainFrame instance = null;
	private JScrollPane scroll;
	private JTable table;
	private Dimension screenSize;
	private JButton addEntityButton;
	private JButton deleteButton;
	private JButton searchButton;
	private JButton sortButton;
	private JToolBar toolbar;
	private DefaultTableModel tableModel;
	private JButton openDataRepositoryButton;
	private String dataRepositoryPath;
	private Storage storage;
	private JButton saveButton;
	private JButton createButton;
	
	private MainFrame() {
		setSize(800,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Projekat");
		
		addEntityButton = new JButton("Add");
	//	addEntityButton.setEnabled(false);
		deleteButton = new JButton("Delete");
	//	deleteButton.setEnabled(false);
		searchButton = new JButton("Search");
		searchButton.setEnabled(false);
		sortButton = new JButton("Sort");
	//	sortButton.setEnabled(false);
		openDataRepositoryButton = new JButton("Open data repository");
		toolbar = new JToolBar();
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		createButton = new JButton("Create new repository");
		
		
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(openDataRepositoryButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(createButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(saveButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(addEntityButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(deleteButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(searchButton);
		toolbar.addSeparator(new Dimension(30, 20));
		toolbar.add(sortButton);
		toolbar.setOrientation(javax.swing.SwingConstants.VERTICAL);
		
		toolbar.setFloatable(false);
		toolbar.setPreferredSize(new Dimension(200,200));
		add(toolbar, BorderLayout.WEST);
		
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table = new JTable(tableModel);
		
		tableModel.addColumn("Entity name");
		tableModel.addColumn("Entity ID");
		tableModel.addColumn("Key - value map");
		tableModel.addColumn("Nested entities");
		table.setColumnSelectionAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getColumnModel().getColumn(2).setMinWidth(400);
		table.getColumnModel().getColumn(3).setMinWidth(400);
		
		
		scroll = new JScrollPane(table);
		
		add(scroll);
		
		
		
		validate();
		initActionListeners();
	}
	
	public static MainFrame getInstance() {
		if(instance == null) instance = new MainFrame();
		return instance;
	}
	
	public void addToTable(Entity e) {
		List<String> list = new ArrayList<>();
		list.add(e.getName());
		list.add(e.getID());
		String mapa = "";
		String enMapa = "";
		if(!e.getMapa().isEmpty()) {
			for (String key : e.getMapa().keySet()) {
			    mapa += key + ":" + e.getMapa().get(key) + " , ";
			}
		}
		if(!mapa.equals(""))mapa = mapa.substring(0, mapa.length() - 2);
		list.add(mapa);
		
		if(!e.getEnMapa().isEmpty()) {
			for (String key : e.getEnMapa().keySet()) {
			    enMapa += key + ":" + e.getEnMapa().get(key).getID() + " , ";
			}
		}
		if(!enMapa.equals(""))enMapa = enMapa.substring(0, enMapa.length() - 2);
		list.add(enMapa);
		tableModel.addRow(list.toArray());
	}
	
	public void removeFromTable(Entity e) {
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			if(e.getID().equals(tableModel.getValueAt(i, 1)))tableModel.removeRow(i);
		}
	}
	
	private void initActionListeners() {
		openDataRepositoryButton.addActionListener(new OpenFileChooserAction());
		addEntityButton.addActionListener(new AddDialogAction());
		sortButton.addActionListener(new SortDialogAction());
		createButton.addActionListener(new CreateNewRepositoryAction());
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				storage.save();
				
			}
		});
		deleteButton.addActionListener(new DeleteAction());
	}

	public void setStorage(Storage storage) {
		for (Entity e: storage.getWorkingList()){
			addToTable(e);
		}
		
	}
	//set
	public void setDataRepository(String dp) {
		this.dataRepositoryPath = dp;
		storage = new DataRepositoryJson(dataRepositoryPath);
		storage.readConfig(storage.getAdress());
		storage.load();
		enableButtons();
		refresh();
	}
	
	public void createNewRepository(String dataRepositoryPath, int maxEntities) {
		storage = new DataRepositoryJson(dataRepositoryPath);
		storage.setMaxEntities(maxEntities);
		storage.setStorageType("JSON");
		enableButtons();
		refresh();
	}
	
	public void enableButtons() {
		addEntityButton.setEnabled(true);
		sortButton.setEnabled(true);
		searchButton.setEnabled(true);
		saveButton.setEnabled(true);
		deleteButton.setEnabled(true);

	}
	
	public Storage getStorage() {
		return storage;
	}

	public void refresh() {
		this.table.clearSelection();
		this.tableModel.getDataVector().removeAllElements();
		

		for(Entity e: this.storage.getWorkingList()) {
			this.addToTable(e);
		}
		
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public JTable getTable() {
		return table;
	}
}
