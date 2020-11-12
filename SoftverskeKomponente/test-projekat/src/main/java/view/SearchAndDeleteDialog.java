package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.komponente.Entity;
import com.komponente.search.NestedSearchEnum;
import com.komponente.search.SearchEnum;

public class SearchAndDeleteDialog extends JDialog{
	
	private JComboBox mainCombo;
	private JLabel label1; 
	private JLabel label2;
	private JTextField textField1;
	private JComboBox combo1;
	private JLabel label3;
	private JTextField textField2;
	private JComboBox combo2;
	private JLabel label4;
	private JTextField textField3;
	private JButton search;
	private JButton delete;
	
	
	public SearchAndDeleteDialog() {
		setSize(400,250);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Search and delete");
		label1 = new JLabel("Search entities by: ");
		String[] cString = {"NAME", "ID", "SIMPLE_MAP", "ENTITY_MAP"};
		mainCombo = new JComboBox(cString);
		
		mainCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(mainCombo.getSelectedItem().toString().equals("NAME") || mainCombo.getSelectedItem().toString().equals("ID")) {
					textField2.setEnabled(false);
					textField3.setEnabled(false);
					combo2.setEnabled(false);
				}else if(mainCombo.getSelectedItem().toString().equals("SIMPLE_MAP")) {
					textField2.setEnabled(true);
					textField3.setEnabled(false);
					combo2.setEnabled(false);
				}else if(mainCombo.getSelectedItem().toString().equals("ENTITY_MAP")) {
					textField2.setEnabled(true);
					
					combo2.setEnabled(true);
					if(combo2.getSelectedItem().toString().equals("MAP")) {
						textField3.setEnabled(true);
					}else {
						textField3.setEnabled(false);
					}
				}
				
			}
		});
		
		
		label2 = new JLabel("Find: ");
		textField1 = new JTextField();
		combo1 = new JComboBox(SearchEnum.values());
		label3 = new JLabel("in value for key: ");
		textField2 = new JTextField();
		combo2 = new JComboBox(NestedSearchEnum.values());
		label4 = new JLabel("in value for key: ");
		textField3 = new JTextField();
		search = new JButton("Search");
		delete = new JButton("Delete");
		textField2.setEnabled(false);
		textField3.setEnabled(false);
		combo2.setEnabled(false);
		
		combo2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(combo2.getSelectedItem().toString().equals("MAP")) {
					textField3.setEnabled(true);
				}else {
					textField3.setEnabled(false);
				}
			}
		});
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,2,20,10));
		JPanel panel2 = new JPanel();
		JPanel panel = new JPanel();
		panel1.add(label1);
		panel1.add(mainCombo);
		panel1.add(label2);
		panel1.add(textField1);
		panel1.add(combo1);
		panel1.add(label3);
		panel1.add(textField2);
		panel1.add(combo2);
		panel1.add(label4);
		panel1.add(textField3);
		panel2.add(search);
		panel2.add(delete);
		
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.SOUTH);
		
		add(panel);
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Entity> toReturn = null;
				if(textField1.getText().equals(""))return;
				String s1 = textField1.getText();
				SearchEnum enum1 = SearchEnum.valueOf(combo1.getSelectedItem().toString());
				
				if(mainCombo.getSelectedItem().toString().equals("NAME")) {
					toReturn = MainFrame.getInstance().getStorage().searchByName(enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("ID")) {
					toReturn = MainFrame.getInstance().getStorage().searchByID(enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("SIMPLE_MAP")) {
					if(textField2.getText().equals(""))return;
					String s2 = textField2.getText();
					toReturn = MainFrame.getInstance().getStorage().searchByValueInSimplePropertyMap(s2, enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("ENTITY_MAP")) {
					if(textField2.getText().equals(""))return;
					String s2 = textField2.getText();
					NestedSearchEnum enum2 = NestedSearchEnum.valueOf(combo2.getSelectedItem().toString());
					if(enum2.equals(NestedSearchEnum.MAP) && textField3.getText().equals(""))return;
					String s3 = textField3.getText();
					toReturn = MainFrame.getInstance().getStorage().searchInEntityMap(s2, enum2, enum1, s1, s3);
						
				}
				
				MainFrame.getInstance().getTable().clearSelection();
				MainFrame.getInstance().getTableModel().getDataVector().removeAllElements();
				
				if(toReturn == null || toReturn.isEmpty())return;
				
				for(Entity e: toReturn) {
					MainFrame.getInstance().addToTable(e);
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Entity> toReturn = null;
				if(textField1.getText().equals(""))return;
				String s1 = textField1.getText();
				SearchEnum enum1 = SearchEnum.valueOf(combo1.getSelectedItem().toString());
				
				if(mainCombo.getSelectedItem().toString().equals("NAME")) {
					toReturn = MainFrame.getInstance().getStorage().searchByName(enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("ID")) {
					toReturn = MainFrame.getInstance().getStorage().searchByID(enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("SIMPLE_MAP")) {
					if(textField2.getText().equals(""))return;
					String s2 = textField2.getText();
					toReturn = MainFrame.getInstance().getStorage().searchByValueInSimplePropertyMap(s2, enum1, s1);
				}else if(mainCombo.getSelectedItem().toString().equals("ENTITY_MAP")) {
					if(textField2.getText().equals(""))return;
					String s2 = textField2.getText();
					NestedSearchEnum enum2 = NestedSearchEnum.valueOf(combo2.getSelectedItem().toString());
					if(enum2.equals(NestedSearchEnum.MAP) && textField3.getText().equals(""))return;
					String s3 = textField3.getText();
					toReturn = MainFrame.getInstance().getStorage().searchInEntityMap(s2, enum2, enum1, s1, s3);
						
				}
				
				MainFrame.getInstance().getStorage().deleteEntityList(toReturn);
				MainFrame.getInstance().refresh();
			}
		});
		
		validate();
	}
}
