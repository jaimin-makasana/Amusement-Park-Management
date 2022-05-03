package com.team5.HAPark.order.model;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {

    IOrder createOrderFromItemQuantities(String userId, List<? extends IOrderItem> orderItems);

    void saveOrder(IOrder order);

    IOrder getOrder(int orderId);

    List<IOrder> getAllOrdersForUser(String email) throws SQLException;
}
