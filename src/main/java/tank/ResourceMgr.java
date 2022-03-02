package tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author wangzhaobin
 * @date 2022/2/16 上午12:33
 */
public class ResourceMgr {

    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        //load到内存    当ResourceMgr初始化的时候，静态语句块会执行
        try {


            goodTankU = ImageIO.read(new File("/Users/wangzhaobin/Downloads/tank-master/tank/src/images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(new File("/Users/wangzhaobin/Downloads/tank-master/tank/src/images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);


            //bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));


            bulletU = ImageIO.read(new File("/Users/wangzhaobin/Downloads/tank-master/tank/src/images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            //爆炸的图片全部放到内存
            for (int i = 0; i < 16; i++)
                explodes[i] = ImageIO.read(new File("/Users/wangzhaobin/Downloads/tank-master/tank/src/images/e" + (i + 1) + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
