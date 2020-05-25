package com.zhangwei.javabase.design.responsibilityChain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Request {
    private String type;
    private String content;
}
