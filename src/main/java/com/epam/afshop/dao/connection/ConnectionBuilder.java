package com.epam.afshop.dao.connection;

import java.sql.Connection;

public interface ConnectionBuilder {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}