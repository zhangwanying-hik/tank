package tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

        //初始化敌方坦克
        for(int i=0;i<5;i++){
            tf.tanks.add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD,tf));
        }

        //窗口
//        Frame f = new Frame();
//        f.setSize(800,600);
//        //设不可改变大小(不能拖动右下角改变大小)
//        f.setResizable(false);
//        f.setTitle("tank war");
//        //设置可见
//        f.setVisible(true);
//
//        //添加窗口监听器
//        //匿名内部类
//        f.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                //监听点击右上角叉的事件
//                //点击后系统退出
//                System.exit(0);
//            }
//        });


        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tf.repaint();
        }
    }
}
