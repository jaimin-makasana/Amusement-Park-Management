package com.team5.HAPark.order.persistence;

import com.team5.HAPark.database.mysql.IMySQLDatabase;
import com.team5.HAPark.food.model.Food;
import com.team5.HAPark.food.model.FoodOrderItem;
import com.team5.HAPark.food.model.IFoodService;
import com.team5.HAPark.order.model.IOrder;
import com.team5.HAPark.order.model.IOrderItem;
import com.team5.HAPark.order.model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLFoodOrderPersistence implements IOrderPersistence {

    private final IMySQLDatabase mySQLDatabase;
    private final IFoodService foodService;

    public MySQLFoodOrderPersistence(IMySQLDatabase mySQLDatabase, IFoodService foodService){
        this.mySQLDatabase = mySQLDatabase;
        this.foodService = foodService;
    }

    @Override
    public void saveOrder(IOrder order) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_food_order(?,?,?,?)} ");

            statement.setString(1,order.getMailId());
            statement.setDate(2, Date.valueOf(order.getOrderDate()));
            statement.setTime(3, Time.valueOf(order.getOrderTime()));
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int orderId = statement.getInt(4);
            order.setOrderId(orderId);

            List<? extends IOrderItem> orderItems = order.getOrderItems();
            for (IOrderItem orderItem: orderItems){

                String itemID = orderItem.getId();
                int quantity = orderItem.getQuantity();

                saveOrderItem(orderId,itemID,quantity);
            }
        } finally {
            close(statement);
        }
    }

    @Override
    public void saveOrderItem(int orderId, String itemId, int quantity) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_food_order_item(?,?,?)} ");

            statement.setInt(1,orderId);
            statement.setString(2, itemId);
            statement.setInt(3, quantity);

            statement.execute();

        } finally {
            close(statement);
        }
    }

    @Override
    public IOrder loadOrder(int orderId) throws SQLException {

        IOrder order;
        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_order(?,?,?,?)} ");

            statement.setInt(1,orderId);
            statement.registerOutParameter(2,Types.DATE);
            statement.registerOutParameter(3,Types.TIME);
            statement.registerOutParameter(4,Types.VARCHAR);

            statement.execute();

            order = new Order();

            order.setOrderId(orderId);
            order.setOrderDate(statement.getDate(2).toLocalDate());
            order.setOrderTime(statement.getTime(3).toLocalTime());
            order.setMailId(statement.getString(4));

            List<IOrderItem> orderItems = loadOrderItems(orderId);
            order.setOrderItems(orderItems);

        } finally {
            close(statement);
        }

        return order;
    }

    public List<IOrderItem> loadOrderItems(int orderId) throws SQLException {

        List<IOrderItem> orderItems = new ArrayList<>();

        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_order_items(?)} ");

            statement.setInt(1,orderId);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int quantity = rs.getInt("quantity");
                String foodId = rs.getString("food_id");
                Food food = foodService.getFood(foodId);

                orderItems.add(new FoodOrderItem(food,quantity));
            }

        } finally {
            close(statement, rs);
        }

        return orderItems;
    }

    @Override
    public List<IOrder> loadAllOrders(String email) throws SQLException {

        List<IOrder> orders = new ArrayList<>();
        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_orders_for_user(?)} ");

            statement.setString(1,email);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int orderId = rs.getInt("food_order_id");
                LocalDate date = rs.getDate("order_date").toLocalDate();
                LocalTime time = rs.getTime("order_time").toLocalTime();
                List<IOrderItem> orderItems = loadOrderItems(orderId);

                IOrder order = new Order();

                order.setOrderId(orderId);
                order.setMailId(email);
                order.setOrderDate(date);
                order.setOrderTime(time);
                order.setOrderItems(orderItems);

                orders.add(order);
            }

        } finally {
            close(statement, rs);
        }

        return orders;
    }

    private void close(CallableStatement statement, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            close(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
