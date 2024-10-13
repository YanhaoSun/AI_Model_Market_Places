package com.yysw.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedModel {
    private Long model_id;

    private Double price;

    public OrderedModel() { }

    public OrderedModel(Long model_id, Double price) {
        this.model_id = model_id;
        this.price = price;
    }

    public Long getModel_id() { return model_id; }

    public void setModel_id(Long id) { this.model_id = id; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}