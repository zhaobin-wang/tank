package tank;

import lombok.Data;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/3/5 下午9:20
 */
@Data
public abstract class GameObject {

    protected int x, y;

    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();

}
