package com.ozhegov.laba3.managed_bean;

import org.junit.jupiter.api.Test;
import com.ozhegov.laba3.managed_bean.ResultBean;

import static org.junit.jupiter.api.Assertions.*;

class ResultBeanTest {

    @Test
    void getPoints() {
        ResultBean bean = new ResultBean();
        System.out.println(bean.getPoints());
        System.out.println(Double.parseDouble("-1.42"));
        assertEquals(1,1);
    }


}