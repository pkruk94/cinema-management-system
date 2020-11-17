package com.app.dto.order;

import com.app.value_object.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
