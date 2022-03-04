package tank;

import abstractFactory.BaseBullet;
import abstractFactory.BaseTank;
import lombok.Data;

import java.awt.*;

/**
 * 子弹类
 *
 * @author wangzhaobin
 * @date 2022/2/14 上午11:56
 */
@Data
public class Bullet {

    //子弹的速度
    private static final int SPEED = 5;
    //大小
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static  int HEIGHT = ResourceMgr.bulletD.getHeight();

    //
    Rectangle rect = new Rectangle();

    private int x, y;

    //方向
    private Dir dir;

    //子弹是否还活着
    private boolean living = true;

    //子弹也是需要分组
    private Group group = Group.BAD;
    //引用TankFrame
    private TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        //todo   创建bullet的时候直接把子弹放到子弹队列里面
        tf.bullets.add(this);
    }


    public void paint(Graphics g) {

        if(!living){
            tf.bullets.remove(this);
        }

//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        //设置成圆
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);


        switch (dir) {
            case LEFT :
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP :
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT :
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN :
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }


        move();
    }

    private void move() {

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

        //超过边界，说明已经没用了（已死）
        if(x < 0 || y <0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    //干掉敌方
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;


        //获取字段的矩形位置
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            //算一下爆炸的位置
            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(new Explode(ex, ey, tf));
        }

    }

    private void die() {
        this.living = false;
    }
}
