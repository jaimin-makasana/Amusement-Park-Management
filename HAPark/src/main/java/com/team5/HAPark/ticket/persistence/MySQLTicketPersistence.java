package com.team5.HAPark.ticket.persistence;

import com.team5.HAPark.database.mysql.IMySQLDatabase;
import com.team5.HAPark.ticket.model.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLTicketPersistence implements ITicketPersistence{

    private final IMySQLDatabase mySQLDatabase;

    public MySQLTicketPersistence(IMySQLDatabase database) {
        this.mySQLDatabase = database;
    }

    public Ticket loadTicket(String type) throws SQLException {

        Ticket ticket = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = mySQLDatabase.getConnection();

        try {

            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM ticket WHERE ticket_type = '"+type+"';");

            while(resultSet.next()){
                String ticketType = resultSet.getString("ticket_type");
                double ticketPrice = resultSet.getDouble("ticket_price");
                ticket = new Ticket(ticketType,ticketPrice);
            }

        } finally {

            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> Tickets= new ArrayList<Ticket>();
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM ticket;");
        while (rs.next()){
            Ticket t = new Ticket();
            t.setTicketType(rs.getString("ticket_type"));
            t.setTicketPrice(rs.getDouble("ticket_price"));
            Tickets.add(t);
        }
        return Tickets;
    }

}
