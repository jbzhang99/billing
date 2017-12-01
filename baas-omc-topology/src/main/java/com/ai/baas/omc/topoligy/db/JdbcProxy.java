package com.ai.baas.omc.topoligy.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.omc.topoligy.core.util.db.JdbcParam;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class JdbcProxy extends AbstractDbProxy {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcProxy.class);

    private HikariDataSource datasource;

    private static JdbcProxy instance;

    private JdbcProxy() {
    }

    public static JdbcProxy getInstance() {
        if (instance == null) {
            synchronized (JdbcProxy.class) {
                if (instance == null) {
                    instance = new JdbcProxy();
                }
            }
        }
        return instance;
    }

    public void loadResource(Map<String, String> conf) {
        HikariConfig config = new HikariConfig();
        JdbcParam jdbcParam = new JdbcParam(conf);
        if (StringUtil.isBlank(jdbcParam.getJdbcDriver())) {
            LOGGER.info("未配置mysql连接信息，跳过加载mysql数据源");
            return;
        }
        config.setDriverClassName(jdbcParam.getJdbcDriver());
        config.setJdbcUrl(jdbcParam.getDbUrl());
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 500);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);
        // 池中最小空闲链接数量
        config.setMinimumIdle(jdbcParam.getInitialConnections());
        // 池中最大链接数量
        config.setMaximumPoolSize(jdbcParam.getMaxConnections());

        HikariDataSource ds = new HikariDataSource(config);
        // 设置数据源
        setdatabase(ds);
    }

    public Connection getConnection() {
            try {
                return datasource.getConnection();
            } catch (SQLException e) {
                throw new SystemException("获取jdbc连接失败");
            }
    }

    private void setdatabase(HikariDataSource datasource) {
        this.datasource = datasource;
    }

}


