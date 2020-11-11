package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import view.MainFrame;

public class OpenFileChooserAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int r = fc.showOpenDialog(null); 
		  
        if (r == JFileChooser.APPROVE_OPTION) {  
            MainFrame.getInstance().setDataRepository(fc.getSelectedFile().getAbsolutePath()); 
        } 
   
        
     
	}

}
