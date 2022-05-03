package com.team5.HAPark.order.model;

import com.team5.HAPark.order.persistence.IOrderPersistence;
import com.team5.HAPark.order.model.*;
import com.team5.HAPark.ticket.model.Ticket;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class OrderServiceTest {

    private static IOrderService orderService;
    private static IOrderPersistence orderPersistenceMock;
    private static List<IOrderItem> orderItems;
    private static TicketOrderItemAdapter childTicketOrder;
    private static TicketOrderItemAdapter adultTicketOrder;
    private static IOrder order;

    @BeforeAll
    public static void init(){

        orderPersistenceMock = Mockito.mock(IOrderPersistence.class);
        orderService = new OrderService(orderPersistenceMock);
        orderItems = new ArrayList<>();

        Ticket child = new Ticket("child", 15);
        Ticket adult = new Ticket("adult", 20);

        childTicketOrder = new TicketOrderItemAdapter(new TicketOrderItem(child,3));
        adultTicketOrder = new TicketOrderItemAdapter(new TicketOrderItem(adult,2));

        orderItems.add(childTicketOrder);
        orderItems.add(adultTicketOrder);

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        order = new Order();
        order.setOrderDate(date);
        order.setOrderTime(time);
        order.setMailId("email");
    }

    @AfterEach
    public void reset(){
        Mockito.clearInvocations(orderPersistenceMock);
    }

    @Test
    public void createOrderFromItemQuantitiesHasItems() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email", orderItems);
        List<? extends IOrderItem> orderItems = newOrder.getOrderItems();

        assertTrue(orderItems.contains(childTicketOrder));
        assertTrue(orderItems.contains(adultTicketOrder));
    }

    @Test
    public void createOrderFromItemQuantitiesHasCorrectEmail() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email", orderItems);

        assertEquals("email",newOrder.getMailId());
    }

    @Test
    public void createOrderFromItemQuantitiesHasDateTime() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email", orderItems);

        assertNotNull(newOrder.getOrderDate());
        assertNotNull(newOrder.getOrderDate());
    }

    @Test
    public void createOrderFromItemQuantitiesDefaultHasNoOrderId() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email", orderItems);

        assertNull(newOrder.getOrderId());
    }

    @Test
    public void createOrderFromItemQuantitiesEmptyItems() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email", new ArrayList<>());

        assertNull(newOrder);
    }

    @Test
    public void createOrderFromItemQuantitiesNullItems() {

        IOrder newOrder = orderService.createOrderFromItemQuantities("email",null);

        assertNull(newOrder);
    }

    @Test
    public void saveOrder() throws SQLException {

        orderService.saveOrder(order);
        Mockito.verify(orderPersistenceMock,times(1)).saveOrder(order);
    }

    @Test
    public void getOrder() throws SQLException {
        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(new Order());
        assertNotNull(orderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);
    }

    @Test
    public void getOrderDoesntExist() throws SQLException {
        Mockito.when(orderPersistenceMock.loadOrder(10)).thenReturn(null);
        assertNull(orderService.getOrder(10));
        Mockito.verify(orderPersistenceMock,times(1)).loadOrder(10);
    }

    @Test
    public void getAllOrdersForUser() throws SQLException {

        ArrayList<IOrder> orders = new ArrayList<>();
        orders.add(order);

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(orders);

        assertEquals(orders, orderService.getAllOrdersForUser("email"));
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");
    }

    @Test
    public void getAllOrdersForUserButUserHasNoOrders() throws SQLException {

        Mockito.when(orderPersistenceMock.loadAllOrders("email")).thenReturn(null);

        assertTrue(orderService.getAllOrdersForUser("email").isEmpty());
        Mockito.verify(orderPersistenceMock,times(1)).loadAllOrders("email");
    }
}