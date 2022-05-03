package com.team5.HAPark.user.persistence;

import com.team5.HAPark.database.mysql.IMySQLDatabase;
import com.team5.HAPark.user.model.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class MySQLUserPersistence implements IUserPersistence {

    private final IMySQLDatabase mySQLDatabase;

    public MySQLUserPersistence(IMySQLDatabase mySQLDatabase){
        this.mySQLDatabase = mySQLDatabase;
    }

    @Override
    public boolean doesUserExist(String email) throws SQLException {

        CallableStatement statement = null;
        boolean userExists;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call does_user_exist(?,?)}");
            statement.setString(1,email);
            statement.registerOutParameter(2, Types.BOOLEAN);
            statement.execute();
            userExists = statement.getBoolean(2);

        } finally {
            close(statement);
        }

        return userExists;
    }

    @Override
    public String getPassword(String email) throws SQLException {

        CallableStatement statement = null;
        String password;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call get_password_for_user(?,?)}");
            statement.setString(1,email);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            password = statement.getString(2);

        } finally {
            close(statement);
        }

        return password;
    }


    @Override
    public void saveUser(String email, String fname, String lname, String password) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_user(?,?,?,?)} ");

            statement.setString(1,email);
            statement.setString(2,fname);
            statement.setString(3,lname);
            statement.setString(4,password);

            statement.execute();

        } finally {
            close(statement);
        }
    }

    @Override
    public void userUpdatedPassword(String new_password, String email) throws SQLException {
        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call update_user_password(?,?)} ");

            statement.setString(1,new_password);
            statement.setString(2,email);

            statement.execute();

        } finally {
            close(statement);
        }

    }

    @Override
    public User loadUser(String email) throws SQLException {

        CallableStatement statement = null;
        User loadedUser;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_user(?,?,?,?)}");

            statement.setString(1,email);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.registerOutParameter(3, Types.VARCHAR);
            statement.registerOutParameter(4, Types.VARCHAR);

            statement.execute();

            String fname = statement.getString(2);
            String lname = statement.getString(3);
            String password = statement.getString(4);

            loadedUser = new User(fname,lname,email,password);

        } finally {
            close(statement);
        }
        return loadedUser;
    }

    private void close(CallableStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
