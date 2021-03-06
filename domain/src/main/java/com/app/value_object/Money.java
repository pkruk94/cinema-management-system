package com.app.value_object;

import com.app.exception.MoneyException;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {
    private BigDecimal value;

    public Money() {
        this.value = BigDecimal.ZERO;
    }

    public Money(String value) {
        init(value);
    }

    public Money(Money money) {
        this.value = money.value;
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money add(String value) {
        return new Money(this.value.add(new BigDecimal(value)));
    }

    public Money add(Money money) {
        return new Money(this.value.add(money.value));
    }

    public Money multiply(Money money) {
        return new Money(this.value.multiply(money.value));
    }

    public Money multiply(int value) {
        return new Money(this.value.multiply(BigDecimal.valueOf(value)));
    }

    public Money withDiscount(BigDecimal discount) {
        var reversedDiscount = BigDecimal.ONE.subtract(discount);
        return new Money(value.multiply(reversedDiscount));
    }

    private void init(String value) {
        if (value == null || !value.matches("(\\d+\\.)?\\d+")) {
            throw new MoneyException("Money value is not correct");
        }
        this.value = new BigDecimal(value);
    }

    @Override
    public String toString() {
        return value + "";
    }
}
