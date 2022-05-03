package com.team5.HAPark.food.persistence;

import com.team5.HAPark.food.model.Food;
import com.team5.HAPark.food.model.Menu;
import com.team5.HAPark.database.mysql.IMySQLDatabase;

import java.sql.*;

public class MySQLFoodPersistence implements IFoodPersistence{

    private final IMySQLDatabase database;

    public MySQLFoodPersistence(IMySQLDatabase database){
        this.database = database;
    }

    public Food loadFood(String id) throws SQLException {

        Food food = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = database.getConnection();

        try {
            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM food WHERE food_id = "+id+";");

            while(resultSet.next()){
                 String name = resultSet.getString("food_name");
                 double price = resultSet.getDouble("food_price");
                 food = new Food(name,id,price);
            }
        } finally {
            close(statement, resultSet);
        }

        return food;
    }

    public Menu loadMenu() throws SQLException {

        Menu menu = new Menu();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = database.getConnection();

        try {
            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM food;");

            while(resultSet.next()){
                String id = resultSet.getString("food_id");
                String name = resultSet.getString("food_name");
                double price = resultSet.getDouble("food_price");
                Food food = new Food(name,id,price);
                menu.addFoodToMenu(food);
            }
        } finally {
            close(statement, resultSet);
        }
        return menu;
    }

    private void close(Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
