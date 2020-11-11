package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddDialog;

public class AddDialogAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AddDialog dialog = new AddDialog();
		System.out.println("DUMMY");
		
	}

}
