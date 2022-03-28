package tank;

import java.io.Serializable;

/**
 * @author wangzhaobin
 * @date 2022/2/23 下午5:58
 */
public interface FireStrategy extends Serializable {

    void fire(Tank t);

}
