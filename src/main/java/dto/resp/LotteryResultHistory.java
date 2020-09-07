
package dto.resp;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class LotteryResultHistory {

    private String cycleValue;
    private List<String> gameResult;
    private String openTime;

}
