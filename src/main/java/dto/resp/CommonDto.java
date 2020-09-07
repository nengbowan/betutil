package dto.resp;


import com.alibaba.fastjson.annotation.JSONField;

@lombok.Data
@SuppressWarnings("unused")
public class CommonDto<T> {
    @JSONField(name="data")
    private T returnData;
}
