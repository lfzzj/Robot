package com.hamitao.kids.enums;

/**
 * @data on 2018/6/29 14:50
 * @describe:
 */

public enum CustomerHeader {
    CUSTOMER_AUNT("阿姨"),
    CUSTOMER_BROTHER("哥哥"),
    CUSTOMER_FATHER("爸爸"),
    CUSTOMER_GRANDMA("奶奶"),
    CUSTOMER_GRANDMOTHER("外婆"),
    CUSTOMER_GRANDPA2("外公"),
    CUSTOMER_MOTHER("妈妈"),
    CUSTOMER_SISTER("姐姐"),
    CUSTOMER_UNCLE("叔叔"),
    CUSTOMER_GRANDPA("爷爷");

    private final String text;

    CustomerHeader(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
