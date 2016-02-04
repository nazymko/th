package org.nazymko.thehomeland.parser.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DaoFactory {
    private DaoFactory() {
    }

    public static MysqlDataSource getMysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPassword("0000");
        dataSource.setUser("root");
        dataSource.setDatabaseName("thehomeland");
        return dataSource;
    }
}