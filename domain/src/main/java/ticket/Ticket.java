package ticket;

import base.BaseEntity;
import discount.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movie_showing.MovieShowing;
import order.Order;
import value_object.Money;

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

    // TICKET service
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
