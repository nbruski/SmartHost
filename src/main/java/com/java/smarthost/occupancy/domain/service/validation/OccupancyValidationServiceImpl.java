package com.java.smarthost.occupancy.domain.service.validation;

import com.java.smarthost.common.exception.ValidationException;
import com.java.smarthost.common.validation.error.ValidationError;
import com.java.smarthost.common.validation.error.ValidationErrorsEnum;
import com.java.smarthost.occupancy.domain.model.Occupancy;
import com.java.smarthost.occupancy.domain.service.rules.OccupancyRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OccupancyValidationServiceImpl implements OccupancyValidationService {

    private final OccupancyRulesService occupancyRulesService;

    @Override
    public void validateOccupancy(final Occupancy occupancy) {
        List<ValidationError> validationErrors = new ArrayList<>();

        validateCustomerWillingPrice(occupancy.getCustomerWillingPrice(), validationErrors);
        validateRoom(occupancy.getNoOfEconomyRooms(), validationErrors);
        validateRoom(occupancy.getNoOfPremiumRooms(), validationErrors);

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    private void validateCustomerWillingPrice(List<Double> prices, List<ValidationError> validationErrors) {
        if (!this.occupancyRulesService.applyRulesForPrices(prices)) {
            validationErrors.add(new ValidationError(ValidationErrorsEnum.INVALID_INPUT_NUMBER, "Wrong price provided"));
        }
    }

    private void validateRoom(int number, List<ValidationError> validationErrors) {
        if (!this.occupancyRulesService.applyRulesForRooms(number)) {
            validationErrors.add(new ValidationError(ValidationErrorsEnum.INVALID_NUMBER_OF_ROOMS, "Wrong number of rooms provided"));
        }
    }
}
