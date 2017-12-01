package com.ai.baas.smc.test.util;

import java.io.IOException;

import com.ai.baas.smc.util.AntZipUtil;

public class AntZipUtilTest {

    public static void main(String[] args) throws IOException {
        AntZipUtil.unzip("e:/test/1.zip", "e:/test/tmp");
//        AntZipUtil.unZip("e:/test/1.zip");
        System.out.println("success");
    }

}
