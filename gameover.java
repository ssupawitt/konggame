package javaapplication5;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class gameover extends JPanel{
	
	private ImageIcon feildover = new ImageIcon("Tree_Root_Canopy_by_ANTIFAN_REAL.jpg");
	private ImageIcon exitover = new ImageIcon("exit.jpg");
	private ImageIcon restart = new ImageIcon("Start.jpg");
	public JButton BStartover = new JButton(restart);
	public JButton BExitover  = new JButton(exitover);
	
	gameover(){
		
		this.setLayout(null);
		BExitover.setBounds(500, 650, 150,90);
		add(BExitover);
		add(BStartover);
		BStartover.setBounds(750,650,150,90);
                add(BStartover);
		
	}
	public void paintComponent(Graphics g){
		  super.paintComponent(g);
		  g.drawImage(feildover.getImage(),0,0,800,600,this);
		  
		  
	      
	  }
}
