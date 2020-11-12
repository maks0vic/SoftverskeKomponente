package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import view.CreateNewRepositoryDialog;
import view.MainFrame;

public class CreateNewRepositoryAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateNewRepositoryDialog fc = new CreateNewRepositoryDialog(FileSystemView.getFileSystemView());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int r = fc.showOpenDialog(null); 
		  
        if (r == JFileChooser.APPROVE_OPTION) {  
            MainFrame.getInstance().createNewRepository(fc.getSelectedFile().getAbsolutePath(), Integer.parseInt(fc.getTextField().getText())); 
            
        } 
	}

}
