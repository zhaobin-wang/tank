package tank;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();

        String countStr = PropertyMgr.get("initTankCount");

        //初始化敌方坦克
        for (int i = 0; i < Integer.parseInt(countStr); i++) {
            tf.tanks.add(tf.gf.createTank(180 + i * 80, 100, Dir.DOWN, Group.BAD, tf));
        }

        Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/war1.wav");

        //自动刷新
        while (true) {
            Thread.sleep(20);
            tf.repaint();
        }


    }
}



