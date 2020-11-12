package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.komponente.Entity;

public class AddDialog extends JDialog{

	private JLabel nameLabel;
	private JLabel idLabel;
	private JTextField nameTextField;
	private JTextField idTextField;
	private JRadioButton autoIdRadio;
	private JRadioButton chooseIdRadio;
	private JPanel panel;
	private JButton addButton;

	private JPanel buttonPanel;
	
	private JLabel keyLabel1;
	private JTextField keyTextField1;
	private JLabel valueLabel1;
	private JTextField valueTextField1;
	private JButton addToMapButton;
	private JList list1;
	private JScrollPane listScroller1;
	private DefaultListModel<String> listModel1;
	private JPanel mapPanel1;

	
	private JLabel keyLabel2;
	private JTextField keyTextField2;
	private JLabel valueLabel2;
	private JTextField valueTextField2;
	private JButton addToMapButton2;
	private JList list2;
	private JScrollPane listScroller2;
	private DefaultListModel<String> listModel2;
	private JPanel mapPanel2;

	
	private Map<String, String> mapa1;
	private Map<String, String> mapa2;
	
	public AddDialog() {
		panel = new JPanel();
		nameLabel = new JLabel("Name: ");
		idLabel = new JLabel("ID: ");
		nameTextField = new JTextField();
		idTextField = new JTextField();
		autoIdRadio = new JRadioButton("Auto-generated ID");
		chooseIdRadio = new JRadioButton("Choose ID by yourself");
		addButton = new JButton("Add");
		buttonPanel = new JPanel();
		
		listModel1 = new DefaultListModel();
		list1 = new JList(listModel1);
		mapPanel1 = new JPanel();
		addToMapButton = new JButton("Add to map");
		keyTextField1 = new JTextField();
		keyLabel1 = new JLabel("Key: ");
		valueLabel1 = new JLabel("Value: ");
		valueTextField1 = new JTextField();
		mapPanel1 = new JPanel();
		listScroller1 = new JScrollPane(list1);
		mapa1 = new HashMap<String, String>();
		
		listModel2 = new DefaultListModel();
		list2 = new JList(listModel2);

		addToMapButton2 = new JButton("Add to map");
		keyTextField2 = new JTextField();
		keyLabel2 = new JLabel("Key: ");
		valueLabel2 = new JLabel("Value: ");
		valueTextField2 = new JTextField();

		listScroller2 = new JScrollPane(list2);
		mapa2 = new HashMap<String, String>();
		
		ButtonGroup group = new ButtonGroup();
	    group.add(autoIdRadio);
	    group.add(chooseIdRadio);
		
		GridLayout grid = new GridLayout(3,2);
		
		panel.setLayout(grid);
		
		panel.add(nameLabel);
		panel.add(nameTextField);
		
		panel.add(autoIdRadio);
		autoIdRadio.setSelected(true);
		panel.add(chooseIdRadio);
		
		panel.add(idLabel);
		panel.add(idTextField);
		idTextField.setEnabled(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
		
		chooseIdRadio.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(chooseIdRadio.isSelected())idTextField.setEnabled(true);
				else {
					idTextField.setEnabled(false);
					idTextField.setText("");
				}
			}
		});
		
		addToMapButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String key;
					String value;
					
					key = keyTextField1.getText();
					if(key.equals(""))return;
					value = valueTextField1.getText();
					if(value.equals(""))return;
					
					if(mapa1.containsKey(key))return;
					
					keyTextField1.setText("");
					valueTextField1.setText("");
					
					mapa1.put(key, value);
					listModel1.addElement(key + ":" + value);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		addToMapButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String key;
					String value;
				
					
					key = keyTextField2.getText();
					if(key.equals(""))return;
					value = valueTextField2.getText();
					if(value.equals(""))return;
					
					if(mapa2.containsKey(key))return;
					if(!MainFrame.getInstance().getStorage().checkID(value))return;
					Entity e = MainFrame.getInstance().getStorage().getEntityByID(value);
					if(!e.getName().equals(key))return;
					
					keyTextField2.setText("");
					valueTextField2.setText("");
					mapa2.put(key, value);
					listModel2.addElement(key + ":" + value);
				
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
			
		buttonPanel.add(addButton);
		
		mapPanel1.setLayout(grid);
		
		mapPanel1.add(keyLabel1);
		mapPanel1.add(keyTextField1);
		mapPanel1.add(valueLabel1);
		mapPanel1.add(valueTextField1);
		mapPanel1.add(addToMapButton);
		mapPanel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		
		listScroller1.setPreferredSize(new Dimension(200,70));
		
		mapPanel2 = new JPanel();
		mapPanel2.setLayout(grid);
		
		mapPanel2.add(keyLabel2);
		mapPanel2.add(keyTextField2);
		mapPanel2.add(valueLabel2);
		mapPanel2.add(valueTextField2);
		mapPanel2.add(addToMapButton2);
		mapPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
	
		
		listScroller2.setPreferredSize(new Dimension(200,70));
		
		JPanel mp = new JPanel();
		
		mp.add(mapPanel1);
		mp.add(listScroller1);
		mp.add(mapPanel2);
		mp.add(listScroller2);
		mp.setPreferredSize(new Dimension(350,600));
		
		add(panel, BorderLayout.NORTH);
		add(mp ,BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(idTextField.isEnabled() && idTextField.getText().equals(""))return;
				if(nameTextField.getText().equals(""))return;
				
				String name = nameTextField.getText();
				String id = "";
				if(idTextField.isEnabled())id = idTextField.getText();
				if(MainFrame.getInstance().getStorage().checkID(id))return;
				
				Map<String, String> mapToReturn = new HashMap<>();
				Map<String, Entity> enMapToReturn = new HashMap<>();
				
				if(!mapa1.isEmpty()) {
					for(Map.Entry<String, String> entry: mapa1.entrySet()) {
						mapToReturn.put(entry.getKey(), entry.getValue());
					}
				}
				
			
				if(!mapa2.isEmpty()) {
					for(Map.Entry<String, String> entry: mapa2.entrySet()) {
						enMapToReturn.put(entry.getKey(), MainFrame.getInstance().getStorage().getEntityByID(entry.getValue()));
					}
				}
				
				MainFrame.getInstance().getStorage().addEntity(name, id, mapToReturn, enMapToReturn);
				MainFrame.getInstance().refresh();
				
				
				mapa1.clear();
				mapa2.clear();
				nameTextField.setText("");
				idTextField.setText("");
				listModel1.removeAllElements();
				listModel2.removeAllElements();
				keyTextField1.setText("");
				keyTextField2.setText("");
				valueTextField1.setText("");
				valueTextField2.setText("");
			}
			
		});
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(350,550);
		setTitle("Add Entity");
		validate();
		
	}
	
	
}
