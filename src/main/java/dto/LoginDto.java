
package dto;

import lombok.Builder;
import lombok.Data;

@lombok.Data
@SuppressWarnings("unused")
@Builder
public class LoginDto {

    @Builder.Default
    private long lEduId;
    @Builder.Default
    private long lSchoolId;
    private String strLoginId;
    private String strPassword;

}
