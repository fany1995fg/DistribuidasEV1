package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FrmSeleccionFile 
						 extends JDialog 
						 implements ActionListener{
	
	JButton btn, btn2;
	public JLabel lbl;
	
	public FrmSeleccionFile() {
		setLayout(null);
		setBounds(100, 50, 600, 80);
		setModal(true);
	
		
		btn = new JButton("...");
		btn.setBounds(500,10,40,25);
		btn.addActionListener(this);
		add(btn);

		lbl = new JLabel();
		lbl.setBounds(10,10,480,25);
		lbl.setBackground(Color.WHITE);
		add(lbl);
	
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showDialog(this, "Seleccione file");
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			  File file = fc.getSelectedFile();
			  lbl.setText(file.getAbsolutePath());
		}

		
	}

	

}
