package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SearchAndDeleteDialog;

public class SearchAndDeleteAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SearchAndDeleteDialog sc = new SearchAndDeleteDialog();
	}

}
