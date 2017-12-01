package com.ai.runner.center.bmc.resdeposit.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class MyHbaseUtil {
    private static Connection connection;
    
    static {
        Configuration configuration = HBaseConfiguration.create();
        // configuration.set("hbase.zookeeper.property.clientPort", "2181");
        // configuration.set("hbase.zookeeper.quorum", "node01,node02,node03");
        // configuration.set("hbase.master", "node01");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * 获取值（family名字与qualifier名字相同）
     */
    public static String getCellValue(Result r, String name) {
        return getCellValue(r, name, name);
    }

    /**
     * 获取值（family名字与qualifier名字不同）
     */
    public static String getCellValue(Result r, String family, String qualifier) {
        return Bytes.toString(CellUtil.cloneValue(
                r.getColumnLatestCell(Bytes.toBytes(family), Bytes.toBytes(qualifier))));
    }

}
