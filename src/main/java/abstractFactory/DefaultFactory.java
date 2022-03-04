package abstractFactory;

import tank.*;

/**
 * @author wangzhaobin
 * @date 2022/2/25 下午11:31
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {

        //return new Tank(x, y, dir, group, tf);
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        //return new Explode(x, y, tf);
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {

        //return new Bullet(x, y, dir, group, tf);
        return null;
    }
}
