package com.example.creditservice;

public class CreditCheckResponse {

    private final boolean approved;
    private String message;

    public CreditCheckResponse(boolean approved, DataService.Customer customer, DataService.Vehicle vehicle) {
        this.approved = approved;

        if (this.approved) {
            this.message = String.format("Customer '%s' (credit score = %s)" +
                            " is approved to purchase vehicle '%s' (minimum credit score = %s)",
                    customer.getCustomerId(),
                    customer.getCreditScore(),
                    vehicle.getVehicleId(),
                    vehicle.getMinCreditScore());
        } else {
            this.message = String.format("Customer '%s' (credit score = %s)" +
                            " is NOT approved to purchase vehicle '%s' (minimum credit score = %s)",
                    customer.getCustomerId(),
                    customer.getCreditScore(),
                    vehicle.getVehicleId(),
                    vehicle.getMinCreditScore());
        }
    }

    public boolean getApproved() {
        return this.approved;
    }

    public String getMessage() {
        return this.message;
    }

}
