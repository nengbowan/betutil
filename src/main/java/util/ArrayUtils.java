package util;

import cn.hutool.core.util.ArrayUtil;

/**
 * ����������hutool������
 */
public class ArrayUtils {

    /**
     * �ж�ָ�������Ƿ����ָ�������е�����һ��
     * @param chars
     * @param monitorNumbers
     * @return
     */
    public static boolean containsAny(char [] chars , char [] monitorNumbers ){
        for(char monitorNumber : monitorNumbers){
            if(ArrayUtil.contains(chars , monitorNumber)){
                return true;
            }
        }
        return false;
    }
}
