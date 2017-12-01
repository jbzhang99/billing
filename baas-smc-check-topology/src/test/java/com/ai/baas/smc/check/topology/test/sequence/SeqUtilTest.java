package com.ai.baas.smc.check.topology.test.sequence;

import java.util.HashMap;
import java.util.Map;

import com.ai.baas.storm.sequence.datasource.SeqDataSourceLoader;
import com.ai.baas.storm.sequence.util.SeqUtil;

public class SeqUtilTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("jdbc.driver", "com.mysql.jdbc.Driver");
        map.put("jdbc.url",
                "jdbc:mysql://10.1.235.245:31306/dev_baas_smc1?useUnicode=true&characterEncoding=UTF-8");
        map.put("jdbc.username", "smcusr01");
        map.put("jdbc.password", "smcusr01_123");
        SeqDataSourceLoader.initDefault(map);

        String seqName = "STL_BILL_DATA$BILL_ID$SEQ";
        System.out.println(SeqUtil.getNewId(seqName));
        System.out.println(SeqUtil.getNewId(seqName));
    }
}
