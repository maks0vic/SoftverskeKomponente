package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.componente.comparators.Order;
import com.componente.comparators.SortBy;

public class SortDialog extends JDialog{
	
	private JLabel sortBy;
	private JLabel order;
	private List<String> enums1;
	private List<String> enums2;
	private JComboBox combo1;
	private JComboBox combo2;
	private JPanel panel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton sortButton;
	
	
	public SortDialog() {
		setLocationRelativeTo(null);
		setSize(350,150);
		setVisible(true);
		setTitle("Sort");
		
		enums1 = new ArrayList<>();
		enums2 = new ArrayList<>();
		
		sortBy = new JLabel("Sort by ");
		order = new JLabel("order");
		
		SortBy[] sortByEnums = SortBy.values();
		Order[] orderEnums = Order.values();
		
		for(int i = 0; i < sortByEnums.length; i++) {
			enums1.add(sortByEnums[i].name());
		}
		for(int i = 0; i < orderEnums.length; i++) {
			enums2.add(orderEnums[i].name());
		}
		
		combo1 = new JComboBox(enums1.toArray());
		combo2 = new JComboBox(enums2.toArray());
		
		sortButton = new JButton("Sort");
		cancelButton = new JButton("Cancel");
		buttonPanel = new JPanel();
		buttonPanel.add(sortButton);
		buttonPanel.add(cancelButton);
		
		panel = new JPanel();
		panel.add(sortBy);
		panel.add(combo1);
		panel.add(order);
		panel.add(combo2);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
		
		add(panel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		
		validate();
	}
}
