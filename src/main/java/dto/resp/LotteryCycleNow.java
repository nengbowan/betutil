
package dto.resp;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class LotteryCycleNow {

    private List<FutureCycleList> futureCycleList;
    private List<String> lastCycleGameResult;
    private String lastCycleValue;
    private long nowCycleCountDown;
    private long nowCycleId;
    private String nowCycleValue;

}
