package com.ai.baas.ccp.test;

import java.math.BigDecimal;

import com.ai.baas.ccp.topoligy.core.constant.FeeSource;
import com.ai.baas.ccp.topoligy.core.util.Cal;

public class Test {

    public static void main(String[] args) {
        BigDecimal floor = Cal.bigDecimalFromLong(10L,
                FeeSource.FROM_NO_SOURCE);
        System.out.println(floor);
    }

}
