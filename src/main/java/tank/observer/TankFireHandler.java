package tank.observer;

import tank.Audio;
import tank.Tank;

/**
 * @author wangzhaobin
 * @date 2022/3/10 下午11:07
 */
public class TankFireHandler implements TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank t = event.getSource();
        t.fire();
        Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/tank_fire.wav");
    }
}
