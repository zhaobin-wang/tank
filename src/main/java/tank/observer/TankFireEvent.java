package tank.observer;

import tank.Tank;

/**
 * @author wangzhaobin
 * @date 2022/3/10 下午11:03
 */
public class TankFireEvent {

    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank){
        this.tank = tank;
    }

}
