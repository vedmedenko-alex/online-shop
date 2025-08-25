package com.solvd.online_shop.connection;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private String URL;
    private String USER;
    private String PASSWORD;
    private int POOL_SIZE;

    private final List<Connection> pool = new ArrayList<>();
    private static ConnectionPool instance;

    private ConnectionPool() {
        Properties p = new Properties();
        try (InputStream in = ConnectionPool.class
                .getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (in == null) {
                throw new IllegalStateException("database.properties not found");
            }
            p.load(in);

            URL = p.getProperty("db.url");
            USER = p.getProperty("db.user");
            PASSWORD = p.getProperty("db.password");
            POOL_SIZE = Integer.parseInt(p.getProperty("db.pool.size", "5"));

            for (int i = 0; i < POOL_SIZE; i++) {
                pool.add(createProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD)));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (pool.isEmpty()) {
            return createProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD));
        }
        return pool.remove(pool.size() - 1);
    }

    private Connection createProxyConnection(Connection realConn) {
        return (Connection) Proxy.newProxyInstance(
                ConnectionPool.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("close")) {
                        releaseConnection(realConn);
                        return null;
                    }
                    return method.invoke(realConn, args);
                });
    }

    private synchronized void releaseConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                pool.add(createProxyConnection(c));
            }
        } catch (SQLException ignored) {
        }
    }

    public synchronized void shutdown() {
        for (Connection c : pool) {
            try {
                if (c != null && !c.isClosed()) {
                    c.close();
                }
            } catch (SQLException ignored) {
            }
        }
        pool.clear();
    }
}
