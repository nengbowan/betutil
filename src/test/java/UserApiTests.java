import constant.BetMode;
import dto.req.*;
import dto.resp.CommonDto;
import dto.resp.LotteryNumberCanBetDto;
import dto.resp.UserBalanceDto;
import dto.resp.UserLoginInfoDto;
import org.junit.jupiter.api.Test;

public class UserApiTests {
    @Test
    public void test(){
        UserApi userApi = new UserApi();
        CommonDto<UserLoginInfoDto> userLoginInfoDtoCommonDto = userApi.login();
        CommonDto<UserBalanceDto> userBalanceDtoCommonDto = userApi.getBalance(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken());
        System.out.println("用户余额为："+userBalanceDtoCommonDto.getReturnData().getUser().getUserBalance());
        CommonDto<LotteryNumberCanBetDto> lotteryNumberCanBetDtoCommonDto = userApi.getLotteryNumberCanBet(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken());
        userApi.bet(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken(),
                BetMode.LIANGFEN.getValue(),
                "1",
                BetInfoBuilder.toString(Ge.build(new Integer[]{1}),
                        Shi.build(new Integer[]{1, 2}),
                        Bai.build(new Integer[]{1, 2, 3}),
                        Qian.build(new Integer[]{1, 2, 3,4}),
                        Wan.build(new Integer[]{1, 2, 3,4,5})),
                lotteryNumberCanBetDtoCommonDto.getReturnData().getLotteryGame().getLotteryCycleNow().getNowCycleId()+"");


        //测试用例
        //当导入条数不足30条，轮训等待60秒，再次开始
        //当导入条数满足30条，分析
        // 分析策略是 1.如果当前指定的位置没中的次数大于等于当前设置的次数 开始当前投注 如果不中 依次投 ， 如果中了 ， 从当前中了的期号重新开始 ， 或者将以前的记录删除 ，
        //           2.如果当前指定的位置没中的次数小于当前设置的次数 轮训等待十秒 ， 再次开始
    }
}
