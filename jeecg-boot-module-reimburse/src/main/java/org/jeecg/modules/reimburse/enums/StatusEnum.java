package org.jeecg.modules.reimburse.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public enum StatusEnum {

    FINISHED(1,"完成"),
    CANCEL(2,"行政审核不通过");


    @EnumValue
    @JsonValue
    private Integer code;
    private String comment;

    StatusEnum(Integer code, String comment ){
        this.code = code;
        this.comment=comment;
    }


}
