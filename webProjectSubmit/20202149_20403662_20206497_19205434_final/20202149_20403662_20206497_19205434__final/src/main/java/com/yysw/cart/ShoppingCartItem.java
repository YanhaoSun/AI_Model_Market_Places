package com.yysw.cart;

import com.yysw.aimodels.AIModel;
import com.yysw.user.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shopping_cart_item", schema = "yysw-db")
public class ShoppingCartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private AIModel item;

    private boolean trainedModelOrNot;

    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Customer customer;

    public ShoppingCartItem() {}

    public ShoppingCartItem(AIModel aiModel, boolean trainedModelOrNot, Double price, Customer customer) {
        item = aiModel;
        this.trainedModelOrNot = trainedModelOrNot;
        this.price = price;
        this.customer = customer;

    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }
    public AIModel getItem() { return item; }

    public void setItem(AIModel item) { this.item = item; }

    public boolean isTrainedModelOrNot() { return trainedModelOrNot; }

    public void setTrainedModelOrNot(boolean trainedModelOrNot) {
        this.trainedModelOrNot = trainedModelOrNot;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id=" + id +
                ", item=" + item +
                ", trainedModel=" + trainedModelOrNot +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
