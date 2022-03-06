package tank.decorator;

import tank.GameObject;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/3/6 下午5:54
 */
public abstract class GODecorator extends GameObject {

    //聚合一个GameObject
    GameObject go;

    public GODecorator(GameObject go){
        this.go = go;
    }

    public abstract void paint(Graphics g);

}
