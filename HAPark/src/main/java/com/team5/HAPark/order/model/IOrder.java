package com.team5.HAPark.order.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IOrder {
    Integer getOrderId();

    void setOrderId(Integer orderId);

    String getMailId();

    void setMailId(String mailId);

    LocalDate getOrderDate();

    void setOrderDate(LocalDate orderDate);

    LocalTime getOrderTime();

    void setOrderTime(LocalTime orderTime);

    List<? extends IOrderItem> getOrderItems();

    void setOrderItems(List<? extends IOrderItem> orderItems);
}
