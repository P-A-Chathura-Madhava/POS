package entity;

public class OrderDetails {
    private String id;
    private String date;
    private String time;
    private double cost;
    private String customerId;
    private String customerName;

    public OrderDetails() {
    }

    public OrderDetails(String id, String date, String time, double cost, String customerId, String customerName) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
