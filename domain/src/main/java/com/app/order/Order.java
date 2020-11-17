package com.app.order;

import com.app.base.BaseEntity;
import com.app.ticket.Ticket;
import com.app.user.User;
import com.app.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    // TODO com.app.order com.app.service
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
