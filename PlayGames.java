package javaapplication5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class PlayGames extends JFrame implements ActionListener {

    homegames homegames1 = new homegames();
    Gamestart state1 = new Gamestart();
    gameover gover = new gameover();

    public PlayGames() {
        this.setSize(800, 600);
        this.add(homegames1);
        homegames1.BExit1.addActionListener(this);
        homegames1.BStart.addActionListener(this);
        state1.BExithome.addActionListener(this);
        state1.BPause.addActionListener(this);
        state1.Bresum.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homegames1.BStart) {
            this.setLocationRelativeTo(null);
            this.remove(homegames1);
            this.setSize(800, 730);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.timestart = false;
            state1.scor = 0;
            state1.HP = 3;
            state1.times = 100;
            state1.startbullet = false;
            state1.timestart = false;
        } else if (e.getSource() == state1.BExithome) {
            System.exit(0);
        } else if (e.getSource() == homegames1.BExit1) {
            System.exit(0);
        } else if (e.getSource() == state1.BPause) {
            this.setLocationRelativeTo(null);
            this.setSize(800, 730);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.t.suspend();
            state1.time.suspend();
            state1.actor.suspend();
            state1.plane.suspend();
            state1.airplane.suspend();
        } else if (e.getSource() == state1.Bresum) {
            this.setLocationRelativeTo(null);
            this.setSize(800, 730);
            this.add(state1);
            state1.requestFocusInWindow();
            state1.t.resume();
            state1.time.resume();
            state1.actor.resume();
            state1.plane.resume();
            state1.airplane.resume();
        }
        this.validate();
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame jf = new PlayGames();
        jf.setSize(800, 600);
        jf.setTitle("FighterPlane");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
}
