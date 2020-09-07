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
        //==================================����api��ʼ===============================================
        //�û���¼
        CommonDto<UserLoginInfoDto> userLoginInfoDtoCommonDto = userApi.login();
        //�û���ȡ���
        CommonDto<UserBalanceDto> userBalanceDtoCommonDto = userApi.getBalance(userLoginInfoDtoCommonDto.getReturnData().getInfo().getToken());
        System.out.println("�û����Ϊ��" + userBalanceDtoCommonDto.getReturnData().getUser().getUserBalance());
        //��ȡ����ע��������
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
        //==================================����api����===============================================

        //==========================================================================================
        //==================================ҵ����ʼ===============================================

        //��������
        //��������������30������ѵ�ȴ�60�룬�ٴο�ʼ
        //��������������30��������
        // ���������� 1.�����ǰָ����λ��û�еĴ������ڵ��ڵ�ǰ���õĴ��� ��ʼ��ǰͶע ������� ����Ͷ �� ������� �� �ӵ�ǰ���˵��ں����¿�ʼ �� ���߽���ǰ�ļ�¼ɾ�� ��
        //           2.�����ǰָ����λ��û�еĴ���С�ڵ�ǰ���õĴ��� ��ѵ�ȴ�ʮ�� �� �ٴο�ʼ
        // ���滹��һЩϸ��ûд�� �� ҵ������Ǵ�� �� ��Ҫ�����޸�
        Properties properties = new Properties();
        properties.load(new ClassPathResource("config.properties").getStream());

        //��ص�λ��
        String monitorPos = "��";
        //��ص�����ָ��������
        char[] monitorNumbers = new char[]{'1', '2', '3'};
        //��©����
        int notFoundCount = 30;
        //һֱ���� �Ӳ��˳�
        while (true) {
            List<OpenHistory> openHistories = null;

            //��arraylistת��ΪlinkedArraylist�������Ƴ�
            LinkedList<OpenHistory> openHistoryLinkedList = null;
            //һֱ�ȵ�������ϴ֮��δ�е���������30��
            do {
                //һֱ�ȵ��ļ���ʷ��������30����
                do {
                    openHistories = ClassPathUtil.loadFileToOpenHistories(properties.getProperty("filePath"));
                    SleepUtil.sleep1m();
                } while (openHistories.size() <= notFoundCount);

                //����30�� �����ǰָ����λ��û�еĴ������ڵ��ڵ�ǰ���õĴ��� ��ʼ��ǰͶע ������� ����Ͷ ��������� �� �ӵ�ǰ���˵��ں����¿�ʼ �� ���߽���ǰ�ļ�¼ɾ�� ��


                //fingo��������ʾ�ɹ�����˼ ���������Ѿ�����
                boolean fingo = false;
                openHistoryLinkedList = new LinkedList<OpenHistory>(openHistories);
                //����ֵ֮ǰ���������ݻ���ɾ��
                int removeIndex = -1;
                //������������������30�������ݣ�������ϴ�������ϴ֮�������Ե���30�������ٴεȴ��ļ�����
                for (int count = 0; count < openHistoryLinkedList.size(); count++) {
                    OpenHistory currentOpenHistory = openHistoryLinkedList.get(count);
                    char[] chars = currentOpenHistory.getOpenNumber().toCharArray();
                    if (ArrayUtils.containsAny(chars, monitorNumbers)) {
                        fingo = true;
                        removeIndex = count;
                        count = 0;
                    }
                    if (removeIndex != -1 && count < removeIndex) {
                        //��־��¼����
                        OpenHistory removedHistory = openHistoryLinkedList.remove(count);
                        System.out.println("ɾ������ʷ��¼�� " + JSON.toJSONString(removedHistory));
                        //��ֹԽ�� ����ͷ��ʼ��
                        count = 0;
                    }

                }
            } while (openHistoryLinkedList.size() <= notFoundCount);

            //������ϴ֮������ָ���Ķ��ٳ����� ������Ͷע

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
            //==================================ҵ�������===============================================
        }
    }
}