package tank.cor;

import tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 碰撞器的责任链
 * <p>
 * 增加一个返回值，只要有一个已经碰撞了，我就不在往下走
 *
 * @author wangzhaobin
 * @date 2022/3/5 下午10:49
 */
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
