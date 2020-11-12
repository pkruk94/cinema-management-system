package validation.cinema;

import dto.cinema.UpdateCinemaDto;
import validation.generic.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UpdateCinemaValidator implements Validator<UpdateCinemaDto> {

    @Override
    public Map<String, String> validate(UpdateCinemaDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isIdValid(item.getId())) {
            errors.put("id", "id is not valid");
        }

        if (!isNameValid(item.getName())) {
            errors.put("name", "name is not valid");
        }

        if (!isAddressValid(item.getName())) {
            errors.put("address", "address is not valid");
        }

        if (!areRoomNumbersValid(item.getRoomNumbers())) {
            errors.put("room numbers", "room numbers are not valid");
        }

        if (!isCityValid(item.getCity())) {
            errors.put("city", "city is not valid");
        }

        return errors;
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
    }

    private boolean areRoomNumbersValid(List<Integer> roomNumbers) {
        return roomNumbers != null;
    }

    private boolean isAddressValid(String address) {
        return address != null && address.matches("[A-z0-9 ]+");
    }

    private boolean isNameValid(String name) {
        return name != null && name.matches("[A-z]+");
    }

    private boolean isCityValid(String city) {
        return city != null && city.matches("[A-z]+");
    }

}
