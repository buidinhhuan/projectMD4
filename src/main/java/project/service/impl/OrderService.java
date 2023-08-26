package project.service.impl;

import org.springframework.stereotype.Service;
import project.model.Order;
import project.model.Product;
import project.service.IGenericService;
import project.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService  implements IGenericService<Order ,Integer> {
    private final String FINDALL = "SELECT * FROM ORDERS";

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall(FINDALL);
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setUser_id(rs.getInt(" id_user"));
                order.setReceiver(rs.getString("receiver "));
                order.setPhoneNumber(rs.getString(" phoneNumber"));
                order.setEmail(rs.getString("email"));
                order.setAddress(rs.getString(" address"));
                order.setTotal_price(rs.getInt("totalPrice"));
                order.setStatus(rs.getBoolean("status"));
                order.setBuyDate(rs.getDate("dateBuy"));
                // Add the product to the list
                list.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }

        return list;
    }


    @Override
    public Order findById(Integer id) {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void delete(Integer id) {

    }
}
