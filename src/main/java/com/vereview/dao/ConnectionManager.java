package com.vereview.dao;

import com.vereview.context.ApplicationContext;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by mjmangan on 9/16/17.
 */
public class ConnectionManager {
    private static final ConnectionManager instance = new ConnectionManager();

    private HikariDataSource dataSource;

    private ConnectionManager(){

    }

    public static ConnectionManager getInstance(){
        return instance;
    }

    public void init(ApplicationContext context){
        HikariConfig cfg = new HikariConfig();
        cfg.setConnectionTestQuery("select 1");
        cfg.setJdbcUrl(getConnectionUrl(context));
        cfg.setUsername(context.getDbUserName());
        cfg.setPassword(context.getDbPassword());
        cfg.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
        cfg.setMaximumPoolSize(100);
        cfg.setInitializationFailFast(true);
        dataSource = new HikariDataSource(cfg);
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public String getConnectionUrl(ApplicationContext context){
        return "jdbc:jtds:sqlserver://" + context.getDbServer() + ":1433/" + context.getDbName() + ";instance=" + context.getDbInstance();
    }
}
