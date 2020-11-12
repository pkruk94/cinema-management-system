package validation.order;

import dto.order.CreateOrderDto;
import validation.generic.Validator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateOrderValidator implements Validator<CreateOrderDto> {
    @Override
    public Map<String, String> validate(CreateOrderDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object cannot be null");
            return errors;
        }

        if (!isUserIdValid(item.getUserId())) {
            errors.put("user id", "user id is not valid");
        }

        if (!isOrderTimeValid(item.getOrderTime())) {
            errors.put("order time", "order time is not valid");
        }

        return errors;
    }

    private boolean isOrderTimeValid(LocalDateTime orderTime) {
        // TODO dodać logike?
        return orderTime != null;
    }

    private boolean isUserIdValid(Long userId) {
        return userId != null && userId > 0;
    }
}
