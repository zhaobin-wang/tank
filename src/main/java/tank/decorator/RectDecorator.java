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
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.drawRect(x, y, super.go.getWidth(), super.go.getHeight());
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
