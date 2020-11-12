package validation.ticket;

import dto.ticket.CreateTicketDto;
import validation.generic.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateTicketValidator implements Validator<CreateTicketDto> {
    @Override
    public Map<String, String> validate(CreateTicketDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object cannot be null");
            return errors;
        }

        if (!isDiscountIdValid(item.getDiscountId())) {
            errors.put("discount id", "discount id is not valid");
        }

        if (!isMovieShowingIdValid(item.getMovieShowingId())) {
            errors.put("movie showing id", "movie showing id is not valid");
        }

        if (!isSeatNumberValid(item.getSeatNumber())) {
            errors.put("seat number", "seat number is not valud");
        }

        return errors;
    }

    private boolean isSeatNumberValid(String seatNumber) {
        return seatNumber != null && seatNumber.matches("[A-Z]\\d+");
    }

    private boolean isMovieShowingIdValid(Long movieShowingId) {
        return movieShowingId != null && movieShowingId > 0;
    }

    private boolean isDiscountIdValid(Long discountId) {
        return discountId != null && discountId > 0;
    }
}
