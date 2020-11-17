package dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import value_object.Money;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderDto {

    private Long userID;
    private Money totalPrice;
    private LocalDateTime orderTime;
    private List<Long> ticketsIDs;

}
