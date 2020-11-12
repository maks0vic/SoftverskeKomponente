package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;


public class CreateNewRepositoryDialog extends JFileChooser{

	private JTextField textField;
	private JLabel label;
	
	public CreateNewRepositoryDialog(FileSystemView fileSystemView) {
		super(fileSystemView);
		
		textField = new JTextField();
		label = new JLabel("Max entities in file: ");

		JPanel panel1 = (JPanel) this.getComponent(3);
		JPanel panel2 = (JPanel) panel1.getComponent(3);
		panel2.add(label,0);
		panel2.add(textField,1);
		panel2.getComponent(2).setEnabled(false);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if(textField.getText().equals(""))panel2.getComponent(2).setEnabled(false);
				else panel2.getComponent(2).setEnabled(true);
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if(textField.getText().equals(""))panel2.getComponent(2).setEnabled(false);
				else panel2.getComponent(2).setEnabled(true);
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if(textField.getText().equals(""))panel2.getComponent(2).setEnabled(false);
				else panel2.getComponent(2).setEnabled(true);
				
			}
		});
	}
	
	public JTextField getTextField() {
		return textField;
	}
}
