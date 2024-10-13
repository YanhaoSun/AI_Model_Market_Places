package com.yysw.order;

import com.yysw.user.customer.Customer;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.*;
import java.sql.Date;

@Entity
@Table(name = "orders", schema = "yysw-db")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ElementCollection
    private List<OrderedModel> orderedModels = new ArrayList<OrderedModel>();

    private State state;

    private Date orderDate;

    private String paymentId;

    public Order() { };

    public Order(Customer customer, List<OrderedModel> orderedModels,
                 State state, Date orderDate, String paymentId) {
        this.customer = customer;
        this.orderedModels = orderedModels;
        this.state = state;
        this.orderDate = orderDate;
        this.paymentId = paymentId;
    }

    public void updateState(Order order) {
        this.state = order.state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long order_id) {
        this.id = order_id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) { this.state = state; }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedModel> getOrderedModels() {
        return orderedModels;
    }

    public void setOrderedModels(List<OrderedModel> orderedModels) {
        this.orderedModels = orderedModels;
    }

    public Date getOrderDate() { return orderDate; }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int quantityOfOrder() {
        return this.getOrderedModels().size();
    }

    public Double totalPriceOfOrderedModels() {
        Double totalPrice=0.0;
        for(OrderedModel a: this.getOrderedModels())
        {
            totalPrice += a.getPrice();
        }
        DecimalFormat df = new DecimalFormat("####0.00");
        return Double.valueOf(df.format(totalPrice));
//        return totalPrice;
    }

    public String getPaymentId() { return paymentId; }

    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
}