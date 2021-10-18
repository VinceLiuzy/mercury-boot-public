package com.mercury.system.enums;

import com.mercury.crud.exception.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常列表
 * <p>
 * 代码以 8000000 开始
 * 中间三位数代表异常分类
 * 后三位自定义业务异常详细CODE
 *
 * @author liuzhengyu
 * @version 1.0
 * @date 2019/9/17 16:40
 **/
@Getter
@AllArgsConstructor
public enum MercuryExceptionEnum implements IExceptionEnum {
    // 系统认证[8001xxx]
    REGISTER_FAIL(8001001, "注册失败！"),

    // 系统用户[8002xxx]
    USER_NOT_EXIST(8002001, "用户不存在！"),

    // 系统角色[8003xxx]
    DUPLICATE_ROLECODE(8003001, "重复的角色编码！");

    private final int code;
    private final String message;
}
