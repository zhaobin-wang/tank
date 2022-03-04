package tank;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class main {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tf = new TankFrame();

        Audio.play("/Users/wangzhaobin/Downloads/tankProject/src/audio/war1.wav");

        //自动刷新
        while (true) {
            Thread.sleep(20);
            tf.repaint();
        }


    }
}



