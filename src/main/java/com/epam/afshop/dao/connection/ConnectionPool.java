package com.epam.afshop.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements ConnectionBuilder {
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static ConnectionPool instance;

    private BlockingQueue<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        this.driverName = dbResourceManager.getValue(DBResourceManager.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBResourceManager.DB_URL);
        this.user = dbResourceManager.getValue(DBResourceManager.DB_LOGIN);
        this.password = dbResourceManager.getValue(DBResourceManager.DB_PASSWORD);
        this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBResourceManager.DB_POOL_SIZE));
        connectionPool = new ArrayBlockingQueue<>(poolSize);

        try {
            Class.forName(driverName);
            for (int i = 0; i < poolSize; i++) {
                connectionPool.add(createConnection(url, user, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}