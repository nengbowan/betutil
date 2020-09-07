
package dto.resp;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Info {

    private String _Typename;
    private String token;
    private long userId;
    private UserInfo userInfo;

}
