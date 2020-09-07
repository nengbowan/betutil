import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import constant.BetMode;
import dto.OpenHistory;
import dto.req.*;
import dto.resp.CommonDto;
import dto.resp.LotteryNumberCanBetDto;
import dto.resp.UserBalanceDto;
import dto.resp.UserLoginInfoDto;
import util.ArrayUtils;
import util.ClassPathUtil;
import util.SleepUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UserApiStarter {
    public static void main(String[] args) throws IOException {
        UserApi userApi = new UserApi();

        //=======================================================================================
        //==================================测试api开始===============================================
        //用户登录
        CommonDto<UserLoginInfoDto> userLoginInfoDtoCommonDto = userApi.login();
        //用户获取余额
        CommonDto<UserBalanceDto> userBalanceDtoCommonDto = userApi.getBalance(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken());
        System.out.println("用户余额为：" + userBalanceDtoCommonDto.getReturnData().getUser().getUserBalance());
        //获取可下注最新期数
        CommonDto<LotteryNumberCanBetDto> lotteryNumberCanBetDtoCommonDto = userApi.getLotteryNumberCanBet(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken());
        userApi.bet(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken(),
                BetMode.LIANGFEN.getValue(),
                "1",
                BetInfoBuilder.toString(Ge.build(new Integer[]{1}),
                        Shi.build(new Integer[]{1, 2}),
                        Bai.build(new Integer[]{1, 2, 3}),
                        Qian.build(new Integer[]{1, 2, 3, 4}),
                        Wan.build(new Integer[]{1, 2, 3, 4, 5})),
                lotteryNumberCanBetDtoCommonDto.getReturnData().getLotteryGame().getLotteryCycleNow().getNowCycleId() + "");
        //==========================================================================================
        //==================================测试api结束===============================================

        //==========================================================================================
        //==================================业务处理开始===============================================

        //测试用例
        //当导入条数不足30条，轮训等待60秒，再次开始
        //当导入条数满足30条，分析
        // 分析策略是 1.如果当前指定的位置没中的次数大于等于当前设置的次数 开始当前投注 如果不中 依次投 ， 如果中了 ， 从当前中了的期号重新开始 ， 或者将以前的记录删除 ，
        //           2.如果当前指定的位置没中的次数小于当前设置的次数 轮训等待十秒 ， 再次开始
        // 里面还有一些细节没写到 ， 业务可能是错的 ， 需要后期修改
        Properties properties = new Properties();
        properties.load(new ClassPathResource("config.properties").getStream());

        //监控的位置
        String monitorPos = "个";
        //监控的三个指定的数字
        char[] monitorNumbers = new char[]{'1', '2', '3'};
        //遗漏次数
        int notFoundCount = 30;
        //一直运行 从不退出
        while (true) {
            List<OpenHistory> openHistories = null;

            //将arraylist转化为linkedArraylist方便多次移除
            LinkedList<OpenHistory> openHistoryLinkedList = null;
            //一直等到数据清洗之后未中的条数高于30条
            do {
                //一直等到文件历史条数高于30条数
                do {
                    openHistories = ClassPathUtil.loadFileToOpenHistories(properties.getProperty("filePath"));
                    SleepUtil.sleep1m();
                } while (openHistories.size() <= notFoundCount);

                //高于30条 如果当前指定的位置没中的次数大于等于当前设置的次数 开始当前投注 如果不中 依次投 ，如果中了 ， 从当前中了的期号重新开始 ， 或者将以前的记录删除 ，


                //fingo是声音提示成功的意思 在这里是已经中了
                boolean fingo = false;
                openHistoryLinkedList = new LinkedList<OpenHistory>(openHistories);
                //将该值之前得所有数据缓存删除
                int removeIndex = -1;
                //将以上满足条数高于30条的数据，进行清洗，如果清洗之后，数据仍低于30条数，再次等待文件更新
                for (int count = 0; count < openHistoryLinkedList.size(); count++) {
                    OpenHistory currentOpenHistory = openHistoryLinkedList.get(count);
                    char[] chars = currentOpenHistory.getOpenNumber().toCharArray();
                    if (ArrayUtils.containsAny(chars, monitorNumbers)) {
                        fingo = true;
                        removeIndex = count;
                        count = 0;
                    }
                    if (removeIndex != -1 && count < removeIndex) {
                        //日志记录保存
                        OpenHistory removedHistory = openHistoryLinkedList.remove(count);
                        System.out.println("删除的历史记录： " + JSON.toJSONString(removedHistory));
                        //防止越界 再重头开始跑
                        count = 0;
                    }

                }
            } while (openHistoryLinkedList.size() <= notFoundCount);

            //数据清洗之后满足指定的多少场不中 ，可以投注

            userApi.bet(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken(),
                    BetMode.LIANGFEN.getValue(),
                    "1",
                    BetInfoBuilder.toString(Ge.build(new Integer[]{1}),
                            Shi.build(new Integer[]{1, 2}),
                            Bai.build(new Integer[]{1, 2, 3}),
                            Qian.build(new Integer[]{1, 2, 3, 4}),
                            Wan.build(new Integer[]{1, 2, 3, 4, 5})),
                    lotteryNumberCanBetDtoCommonDto.getReturnData().getLotteryGame().getLotteryCycleNow().getNowCycleId() + "");
            //=======================================================================================
            //==================================业务处理结束===============================================
        }
    }
}