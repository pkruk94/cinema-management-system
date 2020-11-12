package discount;

import base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ticket.Ticket;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "discounts")
public class Discount extends BaseEntity {

    // TODO walidajca
    private BigDecimal value;
    // TODO unique
    private DiscountType discountType;

    @OneToMany(mappedBy = "discount")
    private List<Ticket> tickets;
}
