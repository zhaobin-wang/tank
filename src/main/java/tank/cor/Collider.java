package tank.cor;

import tank.GameObject;

/**
 * 碰撞器
 * @author wangzhaobin
 * @date 2022/3/5 下午10:03
 */
public interface Collider {

    boolean collide(GameObject o1, GameObject o2);

}
