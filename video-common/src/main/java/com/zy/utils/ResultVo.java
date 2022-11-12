package com.zy.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
@ApiModel(value = "统一返回体")
public class ResultVo<T> {
    // 返回前端的信息提示
    @ApiModelProperty(value = "信息",example = "success")
    private String msg;
    // 返回前端的状态码
    @ApiModelProperty(value = "状态码",example = "200")
    private int code;
    // 返回的数据
    @ApiModelProperty(value = "数据主体",example = "data")
    private T data;
}
