package com.adam.config;

/**
 * @author AdamSun
 * @date 2020/8/16 0:24
 */
public enum StatusCode {
    FREE(0), START(1), END(2);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }
}
