package com.mercury.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/11 20:21
 **/
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    FROZEN("0", "冻结"),
    NORMAL("1", "正常");


    private final String code;
    private final String desc;

    public static UserStatusEnum getStatus(String code) {
        for (UserStatusEnum status : UserStatusEnum.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        return null;
    }
}
