package validation;

import dto.cinema.CreateCinemaDto;
import validation.generic.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateCinemaValidator implements Validator<CreateCinemaDto> {
    @Override
    public Map<String, String> validate(CreateCinemaDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isCityValid(item.getCity())) {
            errors.put("city", "city is not valid");
        }

        return errors;
    }

    private boolean isCityValid(String city) {
        return city != null && city.matches("[A-z]+");
    }
}
