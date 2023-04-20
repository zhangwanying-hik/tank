package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN);
    Bullet b = new Bullet(300,300,Dir.DOWN);
//    static final Dir left = Dir.LEFT;

    public TankFrame(){
        //窗口
        setSize(800,600);
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

    //窗口需要重新绘制的时候，它会自动调用
    @Override
    public void paint(Graphics g){
        myTank.paint(g);
        b.paint(g);
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
