package tank;

import abstractFactory.*;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TankFrame extends Frame {

    //1.0  之前是一个坦克直接创建
//    int x = 200;
//    int y = 200;
//
//    Dir dir = Dir.DOWN;
//    private static final int SPEED = 10;

    //V1.1 面向对象的封装
    Tank myTank = new Tank(200, 600, Dir.DOWN, Group.GOOD, this);

    //Tank enemyTank = new Tank(200, 400, Dir.DOWN,this);

    //子弹
    public List<Bullet> bullets = new ArrayList<Bullet>();
    //坦克
    public List<Tank> tanks = new ArrayList<Tank>();

    //Explode e = new Explode(100, 100, this);


    //Bullet b = new Bullet(200, 200, Dir.DOWN, Group.GOOD, this);


    public List<Explode> explodes = new ArrayList<Explode>();


    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 900;

    //先初始化一个工厂
    //public GameFactory gf = new DefaultFactory();


    public TankFrame() {
        setResizable(true);
        //游戏画面 宽度、高度
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("tank war");
        show();

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 定于在内存中的图片
     */
    Image offScreenImage = null;

    /**
     * 解决双缓冲问题
     * a.repaint-->update
     * b.截获update
     * c.首先把该画出的东西（坦克、子弹）先画在内存的图片中，图片大小和游戏画面一致
     * d.把内存图片一次性画到屏幕中（内存中的内容复制到显存）
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {//系统画笔
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.WHITE);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        //log.info("paint！");

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌方的数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        //enemyTank.paint(g);

        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }

        //e.paint(g);


        //这种也是可以
//        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
//            Bullet b = it.next();
//            if(!b.live) it.remove();
//        }

        //下面这种写法会造成   Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
        //使用的时候默认是集合内部的迭代器，其他类没法获取到，使用简单的遍历方式就可以
//        for(Bullet b : bullets){
//            b.paint(g);
//        }
        // x += 10;
        //y += 10;
    }


    /**
     * 键盘监听处理类
     */
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //思路：记录摁键，处理位置

        @Override
        public void keyPressed(KeyEvent e) {
            log.info("key pressed!");
            int key = e.getKeyCode();
            //这种写法有问题，斜着走实现不了  --没加bL bU bR bD之前
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    //x -= 5;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    //y -= 5;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    //x += 5;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    //y += 5;
                    break;
                default:
                    break;
            }

            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            log.info("key released!");
            int key = e.getKeyCode();
            //这种写法有问题，斜着走实现不了  --没加bL bU bR bD之前   键盘抬起来需要恢复为FALSE
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                //J键打出子弹
                case KeyEvent.VK_J:
                    myTank.fire();
                    Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/tank_fire.wav");
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!bL && !bU && !bD && !bR) myTank.setMoving(false);

            else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }

        }
    }
}
