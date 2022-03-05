package tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;

/**
 * 负责坦克和子弹的相撞
 *
 * @author wangzhaobin
 * @date 2022/3/5 下午10:06
 */
public class BulletTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            b.collideWith(t);
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            //如果o1是坦克 o2是子弹  调用自己
            collider(o2, o1);
        } else {
            return;
        }
    }
}
