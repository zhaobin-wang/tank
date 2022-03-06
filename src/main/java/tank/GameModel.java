package tank;

import tank.cor.BulletTankCollider;
import tank.cor.Collider;
import tank.cor.ColliderChain;
import tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 两个身份
 *  1.Facade 对外来说，她是门面
 *  2.内部来看，它是Mediator  调停者
 * @author wangzhaobin
 * @date 2022/3/4 下午5:20
 */

public class GameModel {

    //单例模式
    private static final GameModel INSTANCE = new GameModel();

    //V1.1 面向对象的封装
    Tank myTank = myTank = new Tank(200, 600, Dir.DOWN, Group.GOOD);



    //子弹
//    public List<Bullet> bullets = new ArrayList<Bullet>();
//    //坦克
//    public List<Tank> tanks = new ArrayList<Tank>();
//    public List<Explode> explodes = new ArrayList<Explode>();

    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();
    //Collider怎么做到动态配置
    ColliderChain chain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    //需要使用GameModel的时候，必须使用
    public static GameModel getInstance(){
        return INSTANCE;
    }

    private GameModel() {


        String countStr = PropertyMgr.get("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < Integer.parseInt(countStr); i++) {
            add(new Tank(180 + i * 80, 100, Dir.DOWN, Group.BAD));
        }

        //初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }



    public void add(GameObject go){
        this.objects.add(go);
    }
    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.BLACK);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 60);
//        g.drawString("敌方的数量：" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        //enemyTank.paint(g);

        //子弹
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }


        //相互碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                //第一种写法，for 每一个都碰撞
                //第二种写法，责任链里面自己碰撞
                chain.collide(o1, o2);
            }
        }

        //碰撞检测
//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++)
//                bullets.get(i).collideWith(tanks.get(j));
//        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
