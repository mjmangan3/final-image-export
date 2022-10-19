package com.vereview.context;

import com.vereview.MainApp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by mjmangan on 9/16/17.
 */
public class ApplicationContext {
    private static final ApplicationContext instance = new ApplicationContext();

    private String dbUserName;
    private String dbPassword;
    private String dbInstance;
    private String dbServer;
    private String dbName;
    private Integer optMaxRecords;
    private Integer threadCount;

    private ApplicationContext(){

    }

    public static ApplicationContext getInstance(){
        return instance;
    }

    public void init(String dbName, MainApp.Environment env){
        try (InputStream in = Files.newInputStream(Paths.get(env.getPropertiesFileName()))){
            Properties prop = new Properties();
            prop.load(in);
            this.dbInstance = prop.getProperty(PropertyAttributes.DB_INSTANCE.getAttribute());
            this.dbPassword = prop.getProperty(PropertyAttributes.DB_PASSWORD.getAttribute());
            this.dbServer = prop.getProperty(PropertyAttributes.DB_SERVER.getAttribute());
            this.dbUserName = prop.getProperty(PropertyAttributes.DB_USERNAME.getAttribute());
            this.optMaxRecords = Integer.valueOf(prop.getProperty(PropertyAttributes.OPT_MAX_RECORDS.getAttribute()));
            this.threadCount = Integer.valueOf(prop.getProperty(PropertyAttributes.THREAD_COUNT.getAttribute()));
            this.dbName = dbName;
        }catch (IOException ioe){
            throw new RuntimeException("failed to initialize application context", ioe);
        }
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbInstance() {
        return dbInstance;
    }

    public void setDbInstance(String dbInstance) {
        this.dbInstance = dbInstance;
    }

    public String getDbServer() {
        return dbServer;
    }

    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Integer getOptMaxRecords() {
        return optMaxRecords;
    }

    public void setOptMaxRecords(Integer optMaxRecords) {
        this.optMaxRecords = optMaxRecords;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }
}
