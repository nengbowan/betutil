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
        System.out.println("�û����Ϊ��"+userBalanceDtoCommonDto.getReturnData().getUser().getUserBalance());
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


        //��������
        //��������������30������ѵ�ȴ�60�룬�ٴο�ʼ
        //��������������30��������
        // ���������� 1.�����ǰָ����λ��û�еĴ������ڵ��ڵ�ǰ���õĴ��� ��ʼ��ǰͶע ������� ����Ͷ �� ������� �� �ӵ�ǰ���˵��ں����¿�ʼ �� ���߽���ǰ�ļ�¼ɾ�� ��
        //           2.�����ǰָ����λ��û�еĴ���С�ڵ�ǰ���õĴ��� ��ѵ�ȴ�ʮ�� �� �ٴο�ʼ
    }
}
