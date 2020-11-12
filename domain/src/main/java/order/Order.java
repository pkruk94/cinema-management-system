package order;

import base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticket.Ticket;
import user.User;
import value_object.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;

    @Embedded
    private Money totalPrice;

    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets;

    public Money calculateTotalPrice() {
        return tickets
                .stream()
                .map(Ticket::totalTicketPrice)
                .reduce(new Money(), Money::add);
    }
}
