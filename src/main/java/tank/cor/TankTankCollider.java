package tank.cor;

import tank.GameObject;
import tank.Tank;

/**
 * 负责坦克和坦克的相撞
 *
 * @author wangzhaobin
 * @date 2022/3/5 下午10:06
 */
public class TankTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            //两个坦克相交
            if (t1.getRect().intersects(t2.getRect())) {
                //这样写的话，只要相交就会停止
                //?怎么处理这个情况    A-相撞之后，下一次回到上一个位置   添加两个变量，保存上一次的位置
                // move 的时候，都需要记录   oldx,oldy
                t1.stop();
                t2.stop();
            }
        } else {
            return;
        }
    }
}
