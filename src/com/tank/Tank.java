package tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED=1;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random = new Random();

    private boolean moving = true;

    private boolean living = true;

    private TankFrame tf = null;
    //默认为bad
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g){
        if(!living)tf.tanks.remove(this);
        System.out.println("paint"+x+","+y);
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        //绘制矩形，x，y为坐标，width，height为长宽
//        g.fillRect(x,y,50,50);
//        g.setColor(c);
        //用图片
        switch (dir){
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            default:
                break;
        }


        move();
    }

    private void move(){
        if(moving){
            switch (dir){
                case LEFT:
                    x-=SPEED;
                    break;
                case RIGHT:
                    x+=SPEED;
                    break;
                case UP:
                    y-=SPEED;
                    break;
                case DOWN:
                    y+=SPEED;
                    break;
                default:
                    break;
            }
        }

        if(this.group == Group.BAD && random.nextInt(10) > 5){
            this.fire();
        }
    }

    public void fire(){
        //从坦克的中心发出子弹
        int bX = this.x + Tank.WIDTH/2 -Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tf.bullets.add(new Bullet(bX,by,this.dir,this.group,this.tf));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void die(){
        this.living = false;
    }
}
