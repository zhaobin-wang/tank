package tank;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wangzhaobin
 * @date 2022/2/22 下午3:42
 */
@Slf4j
public class PropertyMgr {


    private static class PropertyMgrHolder{
        private final static PropertyMgr INSTANCE = new PropertyMgr();
    }

    public static PropertyMgr getInstance(){
        return PropertyMgrHolder.INSTANCE;
    }

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String get(String key){
        if(properties == null) return null;
        return (String)properties.get(key);
    }

    public static void main(String[] args) {
        log.info("count:{}",PropertyMgr.get("initTankCount"));
        PropertyMgr.get("goodFireStrategy");
        try {
            FireStrategy fireStrategy = (FireStrategy)Class.forName("tank.FourDirFireStrategy").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
