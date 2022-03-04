package common;

/**
 * @author wangzhaobin
 * @date 2022/3/4 下午3:26
 */
public enum TankTypeEnum {

    BLUE_TYPE(1, "蓝色方"),
    RED_TYPE(2, "红色方");


    private final Integer type;
    private final String desc;

    TankTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
