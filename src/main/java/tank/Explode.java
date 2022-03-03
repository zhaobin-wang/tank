package tank;

import abstractFactory.BaseExplode;
import lombok.Data;

import java.awt.*;

/**
 * 子弹类
 *
 * @author wangzhaobin
 * @date 2022/2/14 上午11:56
 */
@Data
public class Explode extends BaseExplode {

    //大小
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static  int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    //是否还活着
    private boolean living = true;

    //引用TankFrame
    private TankFrame tf = null;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/explode.wav");

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length) tf.explodes.remove(this);

    }

}