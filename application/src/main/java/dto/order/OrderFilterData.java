package dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import value_object.Money;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFilterData {

    private Money minValue;
    private Money maxValue;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

}
