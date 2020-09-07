
package dto.req;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class BetDto {

    private String operationName;
    private String query;
    private Variables variables;

}
