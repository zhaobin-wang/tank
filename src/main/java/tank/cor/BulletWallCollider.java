package tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;
import tank.Wall;

/**
 * 负责坦克和子弹的相撞
 *
 * @author wangzhaobin
 * @date 2022/3/5 下午10:06
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;

            if (b.getRect().intersects(w.getRect())) {
                b.die();
            }

        } else if(o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2, o1);
        }
        return true;
    }

}
