package abstractFactory;

import tank.Audio;
import tank.ResourceMgr;
import tank.TankFrame;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/2/25 下午11:50
 */
public class RectExplode extends BaseExplode {

    //大小
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static  int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    //是否还活着
    private boolean living = true;

    //引用TankFrame
    private TankFrame tf = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/explode.wav");

    }

    /**
     * 新的explode
     * @param g
     */
    @Override
    public void paint(Graphics g) {

//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
//        if (step >= ResourceMgr.explodes.length) tf.explodes.remove(this);

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step, 10*step);

        step++;

        if(step >= 5){
            //tf.explodes.remove(this);
        }
        g.setColor(c);




    }
}
