package com.epam.afshop.dao.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBResourceManager {
    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_POOL_SIZE = "db.poolSize";
    private static final String DB_PROPERTIES = "dao";

    private DBResourceManager() {

    }

    public static DBResourceManager getInstance(){
        return new DBResourceManager();
    }

    private static ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPERTIES);

    public String getValue(String key){
        return bundle.getString(key);
    }
}
