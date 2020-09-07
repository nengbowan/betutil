
package dto.req;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Input {

    private String betInfo;
    private String betMode;
    private long betMultiple;
    private long betPercent;
    private String betPercentType;
    private Object followCommissionPercent;
    private long gameCycleId;
    private long gameId;
    private long gameTypeId;
    private Boolean isFollow;

}
