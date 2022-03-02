import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author wangzhaobin
 * @date 2022/2/15 下午4:47
 */
@Slf4j
public class ImageTest {


    @Test
    public void test() {

        try {
            ///Users/wangzhaobin/Downloads/tank-master/tank/src/images   /Users/wangzhaobin/Downloads/tankProject/src/images/bulletD.gif

            //src/images/bulletD.gif
            BufferedImage iamge0 = ImageIO.read(new File("/Users/wangzhaobin/Downloads/tank-master/tank/src/images/bulletD.gif"));
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("../../../image/bulletD.gif"));
            //log.info("image0:{}", image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("sdfghjk");


    }

}
