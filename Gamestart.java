package javaapplication5;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;
import javax.swing.*;

public class Gamestart extends JPanel implements ActionListener {

    private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("bg.jpg"));
    private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("bg1.png"));
    private final ImageIcon imgplane = new ImageIcon(this.getClass().getResource("plane1.png"));
    private final ImageIcon pause = new ImageIcon(this.getClass().getResource("p.jpg"));
    private final ImageIcon resum = new ImageIcon(this.getClass().getResource("play.jpg"));
    private final ImageIcon back = new ImageIcon(this.getClass().getResource("back.png"));
    Plane m = new Plane();

    homegames hg = new homegames();
    ImageIcon feildover = new ImageIcon(this.getClass().getResource("overbg.jpg"));
    ImageIcon img_paralyze = new ImageIcon(this.getClass().getResource("plane7.png"));
    ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("start.png"));
    JButton BStartover = new JButton(restart);
    JButton BExitover = new JButton(exitover);

    private JLabel score = new JLabel();
    public JButton BPause = new JButton(pause);
    public JButton BExithome = new JButton(back);
    public JButton Bresum = new JButton(resum);

    public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
    public ArrayList<alien> alien = new ArrayList<alien>();
    public ArrayList<airplane> air = new ArrayList<airplane>();
    public int times;
    public int HP = 3;
    public int rs1 = 1;
    public int rs2 = 2;
    boolean timestart = true;
    boolean startbullet = false;

    private gameover gover = new gameover();
    public int scor = 0;
    boolean paralyze1 = false;
    int time_paralyze = 5;

    Thread time = new Thread(new Runnable(){
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }

                if (timestart == false) {
                    repaint();
                }
            }
        }
    });

    Thread actor = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
                repaint();
            }
        }
    });
    Thread plane = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startbullet == false) {
                        Thread.sleep((long) (Math.random() * 10000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startbullet == false) {
                   alien.add(new alien());
                }
            }
        }
    });
    Thread airplane = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startbullet == false) {
                        Thread.sleep((long) (Math.random() * 10000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startbullet == false) {
                    air.add(new airplane());
                }
            }
        }
    });
    Thread paralyze = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (time_paralyze < 1) {
                    paralyze1 = false;
                    time_paralyze = 5;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (timestart == false) {
                    times = (times - 1);
                    if (paralyze1) {
                        time_paralyze--;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    Gamestart() {
        this.setFocusable(true);
        this.setLayout(null);
        BPause.setBounds(725, 20, 40, 40);

        //BExithome.setBounds(850,30,40,40);
        Bresum.setBounds(650, 20, 40, 40);
        BPause.addActionListener(this);
        BExithome.addActionListener(this);
        Bresum.addActionListener(this);
        BExithome.setBounds(325,350,150,50);
        this.add(BPause);

        this.add(score);
        this.add(Bresum);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (!paralyze1) {
                    if (a == KeyEvent.VK_A) {
                        m.x -= 10;
                        m.count++;
                    } else if (a == KeyEvent.VK_D) {
                        m.x += 10;
                        m.count++;
                    }
                    if (m.count > 3) {
                        m.count = 0;
                    } else if (a == KeyEvent.VK_W) {
                        m.count = 5;
                        bullet.add(new Bullet(m.x+75, 500));
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                m.count = 0;
            }
        });
        m.x = 300;

        time.start();
        actor.start();
        t.start();
        plane.start();
        airplane.start();
        paralyze.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (times <= 0 || HP <= 0) {
            this.remove(BPause);
            this.remove(Bresum);
            this.add(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 800, 730, this);
            g.setColor(Color.RED);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("SCORE   " + scor, 275, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 180, 125);
            //

        } else if (times <= 50) {
            g.drawImage(imgstate2.getImage(), 0, 0, 800, 730, this);
            if (paralyze1) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Hobo Std", Font.BOLD, 50));
                g.drawImage(img_paralyze.getImage(), m.x, 550, 100, 150, this);
                g.drawString("AHHHH !!!", m.x + 100, 560);
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 550, 200, 160, this);
            }
            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }
            for (int i = 0; i < bullet.size(); i++) {
                Bullet ba = bullet.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 50, 50, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    bullet.remove(i);
                }
            }
            //===========ghost================
            for (int i = 0; i < alien.size(); i++) {
                g.drawImage(alien.get(i).getImage(), alien.get(i).getX(), alien.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < bullet.size(); i++) {
                for (int j = 0; j < alien.size(); j++) {
                    if (Intersect(bullet.get(i).getbound(), alien.get(j).getbound())) {
                        alien.remove(j);
                        bullet.remove(i);
                        scor += 10;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            for (int i = 0; i < air.size(); i++) {
                g.drawImage(air.get(i).getImage(), air.get(i).getX(),
                        air.get(i).getY(), 100, 100, this);

            }
            for (int i = 0; i < bullet.size(); i++) {
                for (int j = 0; j < air.size(); j++) {
                    if (Intersect(bullet.get(i).getbound(), air.get(j).getbound())) {
                        air.remove(j);
                        bullet.remove(i);
                        scor -= 20;
                        HP = HP - 1;
                        g.drawString("-1HP", m.x + 100, 650);
                        g.drawString("-20", m.x + 100, 580);
                    }
                }
            }

            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.RED);
            g.drawString("SCORE =  " + scor, 50, 40);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.drawString("Time " + times, this.getWidth() - 150, this.getHeight() - 50);
            g.setColor(Color.RED);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        } else if (times <= 0 || HP <= 0) {
            this.remove(BPause);
            this.remove(Bresum);
            this.add(BExithome);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 800, 600, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("SCORE   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
            g.drawImage(imgplane.getImage(), 580, 360, 400, 400, this);
        } else {
            g.drawImage(imgstate1.getImage(), 0, 0, 800, 700, this);
            if (paralyze1) {
                g.setColor(Color.RED);
                g.setFont(new Font("Hobo Std", Font.BOLD, 50));
                g.drawImage(img_paralyze.getImage(), m.x, 550, 100, 150, this);
                g.drawString("-10", m.x + 100, 650);
                g.drawString("AHHHH !!!", m.x + 100, 560);
            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 550, 200, 150, this);
            }
            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }
            for (int i = 0; i < bullet.size(); i++) {
                Bullet ba = bullet.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 50, 50, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    bullet.remove(i);
                }
            }

            //========================================ball1================= 
            for (int i = 0; i < alien.size(); i++) {
                g.drawImage(alien.get(i).getImage(), alien.get(i).getX(), alien.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < bullet.size(); i++) {
                for (int j = 0; j < alien.size(); j++) {
                    if (Intersect(bullet.get(i).getbound(), alien.get(j).getbound())) {
                        alien.remove(j);
                        bullet.remove(i);
                        scor += 10;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }
            for (int i = 0; i < air.size(); i++) {
                g.drawImage(air.get(i).getImage(), air.get(i).getX(),
                        air.get(i).getY(), 100, 100, this);

            }
            for (int i = 0; i < bullet.size(); i++) {
                for (int j = 0; j < air.size(); j++) {
                    if (Intersect(bullet.get(i).getbound(), air.get(j).getbound())) {
                        air.remove(j);
                        bullet.remove(i);
                        scor -= 20;
                        HP = HP - 1;
                        g.drawString("-1HP", m.x + 100, 650);
                        g.drawString("-20", m.x + 100, 580);
                    }
                }
            }
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.RED);
            g.drawString("SCORE =  " + scor, 50, 40);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.drawString("Time " + times, this.getWidth() - 150, this.getHeight() - 50);
            g.setColor(Color.RED);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BStartover) {
            this.setSize(800, 730);
            this.add(hg);
            this.setLocation(null);
            timestart = true;
            startbullet = true;
        } else if (e.getSource() == BExitover) {
            System.exit(0);
        }
    }
}
