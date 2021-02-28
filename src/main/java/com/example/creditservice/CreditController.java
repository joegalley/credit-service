package com.example.creditservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CreditController {

    private final Logger logger = LogManager.getLogger(CreditController.class);

    private static final String LOOKUP_STR = "Evaluating ";
    private static final String RETURNING_STR = "";

    private final DataService dataService;

    @Autowired
    public CreditController(DataService dataService) {
        this.dataService = dataService;
    }


    @PostMapping("/check-credit")
    public ResponseEntity<Object> getVehicles(@RequestBody CreditCheckRequest creditCheckRequest) {
        logger.info(String.format("Checking to see if customer '%s' has a high enough " +
                        "credit score to purchase vehicle '%s'",
                creditCheckRequest.getCustomerId(),
                creditCheckRequest.getVehicleId())
        );

        DataService.Customer customer = dataService.getCustomerById(creditCheckRequest.getCustomerId());

        DataService.Vehicle vehicle = dataService.getVehicleById(creditCheckRequest.getVehicleId());

        if (vehicle.getMinCreditScore() <= customer.getCreditScore()) {
            logger.info(String.format("Customer '%s' is approved to purchase vehicle '%s'",
                    creditCheckRequest.getCustomerId(),
                    creditCheckRequest.getVehicleId())
            );
            return new ResponseEntity<>(new CreditCheckResponse(true, customer, vehicle), HttpStatus.OK);
        } else {
            logger.info(String.format("Customer '%s' is NOT approved to purchase vehicle '%s'",
                    creditCheckRequest.getCustomerId(),
                    creditCheckRequest.getVehicleId())
            );
            return new ResponseEntity<>(new CreditCheckResponse(false, customer, vehicle), HttpStatus.OK);
        }
    }

}
