package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SortDialog;

public class SortDialogAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SortDialog dialog = new SortDialog();
	}

}
