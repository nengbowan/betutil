package dto.req;

import cn.hutool.core.util.ArrayUtil;

/**
 * 下注属性中的一个属性 bet_info
 */
public class BetInfoBuilder {

    /**
     * 对外提供builder构造方法,提供好用的api  有顺序的万 十 百 千 个
     * @param ge
     * @param shi
     * @param bai
     * @param qian
     * @param wan
     * @return
     */
    public static String toString(Ge ge , Shi shi , Bai bai , Qian qian , Wan wan) {
        Integer[][] result = new Integer[][]{ wan.getWan() ,  qian.getQian() , bai.getBai() ,  shi.getShi() , ge.getGe()};
        return ArrayUtil.toString(result);
    }





}

