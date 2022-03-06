package tank.decorator;

import tank.GameObject;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/3/6 下午5:56
 */
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {

        //必须跟着子弹的位置变化而变化
        this.x = go.getX();
        this.y = go.getY();
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(super.go.getX(), super.go.getY(), super.go.getWidth() + 2, super.go.getHeight() + 2);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
