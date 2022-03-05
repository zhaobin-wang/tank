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
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            //todo
            if(b.collideWith(t)){
                return false;
            }

        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            //如果o1是坦克 o2是子弹  调用自己
            return collide(o2, o1);
        }
        return true;
    }

}
