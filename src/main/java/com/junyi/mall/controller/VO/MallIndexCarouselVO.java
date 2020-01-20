package com.junyi.mall.controller.VO;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MallIndexCarouselVO implements Serializable {
    private String carouselUrl;

    private String redirectUrl;
}
