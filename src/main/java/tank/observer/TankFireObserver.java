package tank.observer;

/**
 * @author wangzhaobin
 * @date 2022/3/10 下午11:06
 */
public interface TankFireObserver {
    void actionOnFire(TankFireEvent event);
}
