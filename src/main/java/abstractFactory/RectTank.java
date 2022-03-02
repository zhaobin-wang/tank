package abstractFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tank.*;

import java.awt.*;
import java.util.Random;

/**
 * 方形坦克
 * @author wangzhaobin
 * @date 2022/2/14 上午11:18
 */
@Data
@Slf4j
public class RectTank extends BaseTank {

    //位置
    public int x, y;
    //默认方向
    public Dir dir = Dir.DOWN;

    private static final int SPEED = 1;

    private boolean moving = true;
    private boolean living = true;
    //分组


    //持有tankFrame的引用，把子弹传递给Tank
    public TankFrame tf = null;

    public FireStrategy fireStrategy;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();



    private Random random = new Random();

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;


        //extensibility  拓展性
        if (this.group == Group.GOOD) {
            String goodFireStrategyName = PropertyMgr.get("goodFireStrategy");
            log.info("goodFireStrategyName:{}", goodFireStrategyName);
            try {
                fireStrategy = (FireStrategy) Class.forName("tank."+ goodFireStrategyName).newInstance();
                log.info("fireStrategy:{}", fireStrategy);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {

            //只有一个抽象方法的接口 可以这样写
//            fireStrategy = (t) -> {
//                new DefaultFireStrategy();
//            };

            String badFireStrategyName = PropertyMgr.get("badFireStrategy");
            try {
                fireStrategy = (FireStrategy) Class.forName("tank."+ badFireStrategyName).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g) {
        //Color c = g.getColor();
//        g.setColor(Color.BLUE);
//        g.fillRect(x, y, 20, 20);

        if(!living) tf.tanks.remove(this);


        //画图，把图片画到这个位置
//        switch (dir) {
//            case LEFT :
//                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
//                break;
//            case UP :
//                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
//                break;
//            case RIGHT :
//                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
//                break;
//            case DOWN :
//                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
//                break;
//        }

        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x,y, 40, 40);
        g.setColor(c);

        //g.setColor(c);
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        //随机打子弹
        if(this.group == Group.BAD && random.nextInt(1000) > 990) this.fire();

        //只有敌方坦克才会随机移动
        if(this.group == Group.BAD && random.nextInt(100) >95)
            randomDir();

        //边界检测
        boundsCheck();

    }

    private void boundsCheck() {

        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH) x = TankFrame.GAME_WIDTH - RectTank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2;

    }

    private void randomDir() {

        //拿到数组的下标
        this.dir = Dir.values()[random.nextInt(4)];

    }

    /**
     * 字段开火策略，
     * 1.可以做成传参
     *  1.1 做成传参，然后把defaultFireStrategy做成单例
     *  1.2 传参，如果传入的没有做成单例，每次都要new一个对象。所以最好做成单例
     * 2.作为坦克的一个成员变量，不需要做成单例
     */
    public void fire() {

        //fireStrategy.fire(this);

        //子弹位置
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tf.bullets.add(tf.gf.createBullet(bx, by, this.dir, this.group, this.tf));
    }

    public void die() {
        this.living = false;
    }
}
