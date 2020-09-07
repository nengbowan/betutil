
package dto.resp;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class AddLotteryOrders {

    private String _Typename;
    private String message;
    private List<String> orderIds;

}
