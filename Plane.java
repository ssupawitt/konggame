
package javaapplication5;

import javax.swing.ImageIcon;

public class Plane {
    public ImageIcon[] im = new ImageIcon[7];
    public int x;
    public int count = 0;
    Plane(){
        for(int i=0;i<im.length;i++){
            im[i] = new ImageIcon(this.getClass().getResource("plane"+(i+1)+".png"));
	}
    }
}
