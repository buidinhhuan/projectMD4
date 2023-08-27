package project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private  Long id ;
    private int user_id;
    private  double total_price;
    private  String receiver;
    private  String phoneNumber;
    private String email;
    private  String address;
    private boolean status = true ;
    private Date buyDate = new Date();
    private List<CartItem> orderDetail = new ArrayList<>();
    private Pay pay;

    public Order() {
    }

    public Order(Long id, int user_id, double total_price, String receiver, String phoneNumber, String email, String address, boolean status, Date buyDate, List<CartItem> orderDetail, Pay pay) {
        this.id = id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.receiver = receiver;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.status = status;
        this.buyDate = buyDate;
        this.orderDetail = orderDetail;
        this.pay = pay;
    }

    public Order(Long id, String phoneNumber, String address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public List<CartItem> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<CartItem> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }
}
