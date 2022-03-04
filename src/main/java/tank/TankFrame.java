package tank;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Slf4j
public class TankFrame extends Frame {


    GameModel gm = new GameModel();


    public static final int GAME_WIDTH = 1080, GAME_HEIGHT = 900;


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
        gm.paint(g);
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
                    gm.getMainTank().fire();
                    Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/tank_fire.wav");
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!bL && !bU && !bD && !bR) gm.getMainTank().setMoving(false);

            else {
                gm.getMainTank().setMoving(true);

                if (bL) gm.getMainTank().setDir(Dir.LEFT);
                if (bU) gm.getMainTank().setDir(Dir.UP);
                if (bR) gm.getMainTank().setDir(Dir.RIGHT);
                if (bD) gm.getMainTank().setDir(Dir.DOWN);
            }

        }
    }
}
