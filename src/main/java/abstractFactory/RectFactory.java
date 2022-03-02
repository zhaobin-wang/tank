package abstractFactory;

import tank.Dir;
import tank.Group;
import tank.TankFrame;

/**
 * @author wangzhaobin
 * @date 2022/2/25 下午11:51
 */
public class RectFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group,TankFrame tf) {


        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {

        return new RectBullet(x, y, dir, group, tf);
    }
}
