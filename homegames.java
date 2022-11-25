package javaapplication5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class homegames extends JPanel{
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("bghome.png"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("exit.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("start.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
	homegames(){
            setLayout(null);
            BExit1.setBounds(500,400, 170,70);
            add(BExit1);
            add(BStart);
            BStart.setBounds(150,400,170,70);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,800,600,this);
            g.setColor(new Color(255,255,255));
            g.setFont(new Font("Force Runner",Font.CENTER_BASELINE,70));
            g.drawString("FighterPlane",200,200);
	}
}