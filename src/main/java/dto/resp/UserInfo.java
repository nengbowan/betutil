
package dto.resp;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class UserInfo {

    private String _Typename;
    private Boolean hasBindBankCard;
    private Boolean hasSetBalancePassword;
    private long id;
    private Boolean isBeta;
    private Boolean isBindGoogleCode;
    private Boolean isProxy;
    private String mobileNumber;
    private String userAccount;
    private String userAvatar;
    private String userEmail;
    private Object userName;
    private String userNickname;

}
