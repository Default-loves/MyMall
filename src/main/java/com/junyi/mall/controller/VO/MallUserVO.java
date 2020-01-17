package com.junyi.mall.controller.VO;

import lombok.Data;

@Data
public class MallUserVO {
    private Long userId;

    private String nickName;

    private String loginName;

    private String introduceSign;

    private String address;

    private int shopCartItemCount;
}
