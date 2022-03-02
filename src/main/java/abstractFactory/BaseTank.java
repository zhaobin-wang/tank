package abstractFactory;

import tank.Group;

import java.awt.*;

/**
 * @author wangzhaobin
 * @date 2022/2/25 下午11:29
 */
public abstract class BaseTank {

    public Group group = Group.BAD;

    public Rectangle rect = new Rectangle();
    
    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
