package tank.cor;

import tank.Bullet;
import tank.GameObject;
import tank.Tank;
import tank.Wall;

/**
 * 坦克和墙相撞
 *
 * @author wangzhaobin
 * @date 2022/3/5 下午10:06
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.getRect().intersects(w.getRect())) {
                t.back();
            }

        } else if(o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2, o1);
        }
        return true;
    }

}
