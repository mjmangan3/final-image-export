package com.vereview.context;

/**
 * Created by mjmangan on 9/16/17.
 */
public enum PropertyAttributes {
    DB_USERNAME("db_username"),
    DB_PASSWORD("db_password"),
    DB_INSTANCE("db_instance"),
    DB_SERVER("db_server"),
    OPT_MAX_RECORDS("opt_max_records"),
    THREAD_COUNT("thread_count");

    private String attribute;

    PropertyAttributes(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
