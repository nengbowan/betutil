package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenHistory {

    private String lotteryNumber;

    private String openNumber;
}
