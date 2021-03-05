package com.example.creditservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class CreditController {

    private final Logger logger = LogManager.getLogger(CreditController.class);

    private static final String LOOKUP_STR = "Looking up credit info";
    private static final String RETURNING_STR = "Returning credit info to caller";

    @Autowired
    public CreditController() {

    }

    @GetMapping("/credit-score/{customer_id}")
    public ResponseEntity<Object> getCreditScore(@PathVariable("customer_id") String customer_id) {
        logger.info(LOOKUP_STR + " for customer ID " + customer_id);

        int creditScore;

        switch (customer_id) {
            case "customer-1":
                creditScore = 500;
                break;
            case "customer-2":
                creditScore = 489;
                break;
            case "customer-3":
                creditScore = 843;
                break;
            case "customer-4":
                creditScore = 755;
                break;
            case "customer-5":
                creditScore = 687;
                break;
            case "customer-6":
                creditScore = 565;
                break;
            case "customer-7":
                creditScore = 682;
                break;
            default:
                logger.info("No customer found for ID " + customer_id);
                return new ResponseEntity("No customer found with ID " + customer_id, HttpStatus.NOT_FOUND);
        }

        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setCreditScore(creditScore);

        logger.info("Returning credit score for customer " + customer_id);

        return new ResponseEntity<>(creditResponse, HttpStatus.OK);
    }

}
