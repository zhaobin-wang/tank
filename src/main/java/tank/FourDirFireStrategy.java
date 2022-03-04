package tank;

/**
 * @author wangzhaobin
 * @date 2022/2/23 下午6:00
 */
public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        //子弹位置
        int bx = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        //四个方向
        for(Dir dir : dirs){
            new Bullet(bx, by, dir, t.group, t.gm);
            //new Bullet(bx, by, dir, t.group, t.tf);
        }

    }
}
