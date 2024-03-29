package tank;

import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;
    private TankFrame tf = null;

    private boolean living = true;

    //画到第几步了
    private int step = 0;


    public Explode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Audio("audio/explode.wav").play();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >= ResourceMgr.explodes.length){
            step = 0;
        }
    }
}
