import cn.hutool.core.text.UnicodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import dto.resp.*;
import exception.BetFailedException;
import exception.GetBalanceException;
import exception.GetLotteryNumberFailedException;
import exception.LoginFailedException;
import okhttp3.*;
import java.io.IOException;

/**
 * office url ： https://yk3-c.lhk198679.com
 */
public class UserApi {
    /**
     * 登录以获取token
     * @return
     */
    public CommonDto<UserLoginInfoDto> login()throws LoginFailedException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"operationName\": null,\n    \"variables\": {\n        \"app_key\": \"EEtDkyQpKRtn\",\n        \"account\": \"hjq5288\",\n        \"password\": \"aa1234567\",\n        \"captcha_id\": \"a2bfdf4627c480c7608f09a305eea9490bd725d3\",\n        \"captcha_code\": \"\",\n        \"google_code\": \"\",\n        \"bank_card_real_name\": null,\n        \"two_step_token\": null\n    },\n    \"query\": \"mutation ($app_key: String!, $account: String!, $password: String!, $captcha_id: String, $captcha_code: String, $google_code: String, $bank_card_real_name: String, $two_step_token: String) {\\n  info: Login(app_key: $app_key, account: $account, password: $password, captcha_id: $captcha_id, captcha_code: $captcha_code, google_code: $google_code, bank_card_real_name: $bank_card_real_name, two_step_token: $two_step_token) {\\n    user_id\\n    token\\n    user_info {\\n      id\\n      user_account\\n      user_email\\n      user_name\\n      user_avatar\\n      user_nickname\\n      is_beta\\n      mobile_number\\n      is_bind_google_code\\n      has_bind_bank_card\\n      has_set_balance_password\\n      is_proxy\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"\n}");
        Request request = new Request.Builder()
                .url("https://yk3-c.bofuzm.com/APIV2/GraphQL?l=zh-cn&pf=web")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            resp = UnicodeUtil.toString(resp);
            if(!resp.contains("token")){
                throw new LoginFailedException(resp);
            }
            return JSONObject.parseObject(resp , new TypeReference<CommonDto<UserLoginInfoDto>>(CommonDto.class){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new LoginFailedException(e.getMessage());
        }
    }

    /**
     * 获取用户余额
     * @param token
     * @return
     */

    public CommonDto<UserBalanceDto> getBalance(String token){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"operationName\": \"getUserBalance\",\n    \"variables\": {\n        \"product_id\": \"Enum1\"\n    },\n    \"query\": \"query getUserBalance($product_id: ProductEnum) {\\n  User {\\n    id\\n    user_balance(product_id: $product_id)\\n  }\\n}\\n\"\n}");
        Request request = new Request.Builder()
                .url("https://yk3-c.bofuzm.com/APIV2/GraphQL?l=zh-cn&pf=web")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            resp = UnicodeUtil.toString(resp);
            if(!resp.contains("user_balance")){
                throw new GetBalanceException(resp);
            }
            return JSONObject.parseObject(resp , new TypeReference<CommonDto<UserBalanceDto>>(CommonDto.class){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new GetBalanceException(e.getMessage());
        }
    }

    /**
     * 获取可下注最新期数
     */
    public CommonDto<LotteryNumberCanBetDto> getLotteryNumberCanBet(String token)throws GetLotteryNumberFailedException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"operationName\": \"GetLotteryCycle\",\n    \"variables\": {\n        \"game_id\": 190,\n        \"row_count\": 30\n    },\n    \"query\": \"query GetLotteryCycle($game_id: Int!, $row_count: Int) {\\n  LotteryGame(game_id: $game_id) {\\n    game_id\\n    game_value\\n    base_game\\n    lottery_cycle_now {\\n      now_cycle_id\\n      now_cycle_value\\n      now_cycle_count_down\\n      last_cycle_value\\n      last_cycle_game_result\\n      future_cycle_list {\\n        cycle_id\\n        cycle_value\\n      }\\n    }\\n    lottery_result_history(row_count: $row_count) {\\n      cycle_value\\n      game_result\\n      open_time\\n    }\\n  }\\n}\\n\"\n}");
        Request request = new Request.Builder()
                .url("https://yk3-c.bofuzm.com/APIV2/GraphQL?l=zh-cn&pf=web")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            resp = UnicodeUtil.toString(resp);
            if(!resp.contains("腾讯分分彩")){
                throw new GetLotteryNumberFailedException(resp);
            }
            return JSONObject.parseObject(resp , new TypeReference<CommonDto<LotteryNumberCanBetDto>>(CommonDto.class){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new GetLotteryNumberFailedException(e.getMessage());
        }
    }

    /**
     * 下注api
     * @param token
     * @return
     * @throws BetFailedException
     */
    public CommonDto<BetSuccessDto> bet(String token ,String betMode,
            String betMultiple,
            String betInfoStr,
            String gameNumber)throws BetFailedException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"operationName\": \"AddLotteryOrders\",\n    \"variables\": {\n        \"input\": [\n            {\n                \"game_id\": 190,\n                \"game_type_id\": 65,\n                \"game_cycle_id\": "+gameNumber+",\n                \"bet_info\": \""+betInfoStr+"\",\n                \"bet_mode\": \""+betMode+"\",\n                \"bet_multiple\": "+betMultiple+",\n                \"bet_percent_type\": \"AdjustPercentType\",\n                \"bet_percent\": 0,\n                \"is_follow\": false,\n                \"follow_commission_percent\": null\n            }\n        ]\n    },\n    \"query\": \"mutation AddLotteryOrders($input: [AddLotteryOrderInputObj]!) {\\n  AddLotteryOrders(orders: $input) {\\n    message\\n    order_ids\\n    __typename\\n  }\\n}\\n\"\n}");
        Request request = new Request.Builder()
                .url("https://yk3-c.bofuzm.com/APIV2/GraphQL?l=zh-cn&pf=web")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            resp = UnicodeUtil.toString(resp);
            if(!resp.contains("投注成功")){
                throw new BetFailedException(resp);
            }
            return JSONObject.parseObject(resp , new TypeReference<CommonDto<BetSuccessDto>>(CommonDto.class){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new BetFailedException(e.getMessage());
        }
    }





}
