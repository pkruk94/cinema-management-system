package com.app.ticket;

import com.app.base.BaseEntity;
import com.app.discount.Discount;
import com.app.movie_showing.MovieShowing;
import com.app.order.Order;
import com.app.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    // TICKET com.app.service
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private MovieShowing movieShowing;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Order order;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Discount discount;

    private String seatNumber;

    public Money totalTicketPrice() {
        return movieShowing.getMovie().getPrice().withDiscount(discount.getValue());
    }
}
