package com.pitchfinder.singleton;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public final class ConPool {
    /** DataSource declaration. */
    private static DataSource datasource = null;
    /** ConPool initialization. */
    private static ConPool instance = new ConPool();

    /**
     * Private constructor.
     */
    private ConPool() {
    }

    /**
     * Return the instance of ConPool.
     * @return ConPool
     */
    public static ConPool getInstance() {
        return instance;
    }

    /**
     *  Return an instance of the DB connection.
     *  @return Connection
     */
    public static Connection getConnection() throws SQLException {
        final int maxActive = 100;
        final int initialSize = 10;
        final int minIdle = 10;
        final int timeout = 60;

        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://den1.mysql3.gear.host:3306/"
                    + "PitchFinder?serverTimezone="
                    + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("pitchfinder");
            p.setPassword("Sq9l!o?E2Ioy");
            p.setMaxActive(maxActive);
            p.setInitialSize(initialSize);
            p.setMinIdle(minIdle);
            p.setRemoveAbandonedTimeout(timeout);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
