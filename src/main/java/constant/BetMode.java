package constant;

/**
 * 下注金额单位
 */
public enum BetMode {

    LIANGYUAN("TwoYuan"),
    LIANGJIAO("TwoJiao"),
    LIANGFEN("TwoFen"),
    LIANGLI("TwoLi"),

    YIYUAN("OneYuan"),
    YIJIAO("OneJiao"),
    YIFEN("OneFen"),
    YILI("OneLi");

    private String value;
    BetMode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
