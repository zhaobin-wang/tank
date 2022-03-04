package tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhaobin
 * @date 2022/3/4 下午5:20
 */

public class GameModel {

    //V1.1 面向对象的封装
    Tank myTank = new Tank(200, 600, Dir.DOWN, Group.GOOD, this);


    //子弹
    public List<Bullet> bullets = new ArrayList<Bullet>();
    //坦克
    public List<Tank> tanks = new ArrayList<Tank>();


    public List<Explode> explodes = new ArrayList<Explode>();

    public GameModel() {
        String countStr = PropertyMgr.get("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < Integer.parseInt(countStr); i++) {
            this.tanks.add(new Tank(180 + i * 80, 100, Dir.DOWN, Group.BAD, this));
        }
    }


    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
        g.drawString("敌方的数量：" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        //enemyTank.paint(g);

        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
