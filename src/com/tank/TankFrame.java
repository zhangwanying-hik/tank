package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN,this);
    //多颗子弹
    List<Bullet> bullets = new ArrayList<Bullet>();
//    Bullet b = new Bullet(300,300,Dir.DOWN);
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    public TankFrame(){
        //窗口
        setSize(GAME_WIDTH,GAME_HEIGHT);
        //设不可改变大小(不能拖动右下角改变大小)
        setResizable(false);
        setTitle("tank war");
        //设置可见
        setVisible(true);

        //键盘监听处理
        this.addKeyListener(new MyKeyListener());

        //添加窗口监听器
        //匿名内部类
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //监听点击右上角叉的事件
                //点击后系统退出
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    //双缓冲[游戏中的一个概念]
    //在大的图片加载时，会出现一条一条加载的现象
    //这里在内存中画完再展示，消除这种现象(将内存的内容复制到显存)
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            //在内存里创建出来
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        //拿到图片中的画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        //把背景重新画一遍
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        //调用paint方法，把这只画笔给paint
        paint(gOffScreen);
        //最后一次性将画面画出来
        g.drawImage(offScreenImage,0,0,null);
    }


    //窗口需要重新绘制的时候，它会自动调用
    @Override
    public void paint(Graphics g){
        //有隐藏缺陷：新增子弹，list一直增加，会内存泄露
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bullets.size(),10,60);
        g.setColor(c);

        myTank.paint(g);
//        b.paint(g);
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
    }

    //键盘监听处理类，是一个内部类
    class MyKeyListener extends KeyAdapter{
        //表示这个键被按下
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //在一个键被按下去的时候调用
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=true;
//                    x-=10;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
//                    x+=10;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
//                    y-=10;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
//                    y+=10;
                    break;
                default:
                    break;
            }
            setMainTankDir();
//            System.out.println("key pressed");
//            x+=100;
            //会默认调用paint方法，直接调paint不行
//            repaint();
        }

        //在一个键被抬起来的时候调用
        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("key released");
//            y+=100;

            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=false;
//                    x-=10;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
//                    x+=10;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
//                    y-=10;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
//                    y+=10;
                    break;
                case KeyEvent.VK_CONTROL:
                    //ctrl键被抬起时打出一颗子弹（按下时发射则长按会打出一串）
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            //当四个键都没按时静止
            if(!bL & !bU & !bR & !bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);

                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }

}
