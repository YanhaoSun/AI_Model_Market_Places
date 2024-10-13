package com.yysw.aimodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "aimodel", schema = "yysw-db")
public class AIModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;

    private double trainedPrice;

    private double untrainedPrice;

    private String description;

    private boolean available;

    public void updateModel(AIModel model) {
        this.trainedPrice = model.trainedPrice;
        this.untrainedPrice = model.untrainedPrice;
        this.description = model.description;
        this.available = model.available;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getModelName() { return modelName; }

    public void setModelName(String modelName) { this.modelName = modelName; }

    public double getTrainedPrice() { return trainedPrice; }

    public void setTrainedPrice(double trainedPrice) { this.trainedPrice = trainedPrice; }

    public double getUntrainedPrice() { return untrainedPrice; }

    public void setUntrainedPrice(double untrainedPrice) { this.untrainedPrice = untrainedPrice; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "AIModel{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", trainedPrice=" + trainedPrice +
                ", untrainedPrice=" + untrainedPrice +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
