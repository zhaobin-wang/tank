package abstractFactory;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/2/25 下午11:30
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);


}
