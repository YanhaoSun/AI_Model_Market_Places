package com.yysw.payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PaymentInformation implements Serializable {
    @NotBlank
    private String name;

    @Size(min = 16, max = 16)
    @NotBlank
    private String cardNum;

    @NotBlank
    private String expiry;

    private Integer cvv;

    public String getName() {
        return name;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getExpiry() {
        return expiry;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "PaymentInformation{" +
                "name='" + name + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", expiry=" + expiry +
                ", cvv=" + cvv +
                '}';
    }
}
