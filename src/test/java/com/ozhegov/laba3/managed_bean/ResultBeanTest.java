package com.ozhegov.laba3.managed_bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultBeanTest {

    @Test
    void getPoints() {
        ResultBean bean = new ResultBean();
        System.out.println(bean.getPoints());
        assertEquals(1,1);
    }


}