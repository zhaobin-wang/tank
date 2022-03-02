package abstractFactory;

import tank.Dir;
import tank.Group;
import tank.TankFrame;

/**
 * 抽象工厂   产品一族
 * @author wangzhaobin
 * @date 2022/2/25 下午10:44
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
