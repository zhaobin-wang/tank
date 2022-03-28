package tank.observer;

import java.io.Serializable;

/**
 * @author wangzhaobin
 * @date 2022/3/10 下午11:06
 */
public interface TankFireObserver extends Serializable {
    void actionOnFire(TankFireEvent event);
}
