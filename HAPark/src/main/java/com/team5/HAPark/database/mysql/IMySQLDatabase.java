package com.team5.HAPark.database.mysql;

import com.team5.HAPark.database.IDataBase;

import java.sql.Connection;
import java.sql.SQLException;

public interface IMySQLDatabase extends IDataBase {
    Connection getConnection() throws SQLException;
}
