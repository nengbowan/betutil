
package dto.resp;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class LotteryGame {

    private String baseGame;
    private long gameId;
    private String gameValue;
    private LotteryCycleNow lotteryCycleNow;
    private List<LotteryResultHistory> lotteryResultHistory;

}
