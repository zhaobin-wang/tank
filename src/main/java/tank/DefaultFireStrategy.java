package tank;

/**
 * @author wangzhaobin
 * @date 2022/2/23 下午6:00
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        //子弹位置
        int bx = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        //todo new bullet 又把自己加了一遍
//        GameModel.getInstance().add(
//                new RectDecorator(
//                        new TailDecorator(
//                        new Bullet(bx, by, t.dir, t.group))));
        new Bullet(bx, by, t.dir, t.group);
    }
}
