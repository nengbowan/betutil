package util;

import cn.hutool.core.util.ArrayUtil;

/**
 * 此类依赖于hutool工具类
 */
public class ArrayUtils {

    /**
     * 判断指定数组是否包含指定数组中的任意一个
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
