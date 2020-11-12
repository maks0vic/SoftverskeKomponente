package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;


import com.komponente.Entity;
import com.komponente.Storage;

import view.MainFrame;

public class DeleteAction implements ActionListener{
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Storage storage = MainFrame.getInstance().getStorage();
		JTable table = MainFrame.getInstance().getTable();
		
		List<Entity> entitiesToDelete = new ArrayList<>();
		
		int[] niz = table.getSelectedRows();
		
		for(int i = 0; i < niz.length; i++) {
			entitiesToDelete.add(storage.getEntityByID((String) table.getValueAt(niz[i], 1)));
			
		}
		
		
		if(entitiesToDelete.isEmpty())return;
		storage.deleteEntityList(entitiesToDelete);
	
		MainFrame.getInstance().refresh();
	
	}

}
