package dto.req;

import cn.hutool.core.util.ArrayUtil;

/**
 * ��ע�����е�һ������ bet_info
 */
public class BetInfoBuilder {

    /**
     * �����ṩbuilder���췽��,�ṩ���õ�api  ��˳����� ʮ �� ǧ ��
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

