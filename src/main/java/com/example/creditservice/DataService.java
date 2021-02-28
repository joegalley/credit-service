package com.example.creditservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    public DataService() {

    }

    public static class Vehicle {

        private String vehicleId;
        private String description;
        private String price;
        private int minCreditScore;

        public String getVehicleId() {
            return this.vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getMinCreditScore() {
            return this.minCreditScore;
        }

        public void setMinCreditScore(int minCreditScore) {
            this.minCreditScore = minCreditScore;
        }
    }

    public static class Customer {
        private String customerId;
        private int creditScore;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(int creditScore) {
            this.creditScore = creditScore;
        }
    }

    public List<Vehicle> getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleId("vehicle-1");
        vehicle1.setDescription("Toyota Camry");
        vehicle1.setPrice("$22,000");
        vehicle1.setMinCreditScore(500);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleId("vehicle-2");
        vehicle2.setDescription("Honda Accord");
        vehicle2.setPrice("$25,000");
        vehicle2.setMinCreditScore(550);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setVehicleId("vehicle-3");
        vehicle3.setDescription("Nissan Rogue");
        vehicle3.setPrice("$27,000");
        vehicle3.setMinCreditScore(700);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setVehicleId("vehicle-4");
        vehicle4.setDescription("Tesla Model S");
        vehicle4.setPrice("$70,000");
        vehicle4.setMinCreditScore(775);

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);

        return vehicles;
    }

    public List<Customer> getCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustomerId("customer-1");
        customer1.setCreditScore(400);

        Customer customer2 = new Customer();
        customer2.setCustomerId("customer-2");
        customer2.setCreditScore(813);

        Customer customer3 = new Customer();
        customer3.setCustomerId("customer-3");
        customer3.setCreditScore(672);

        Customer customer4 = new Customer();
        customer4.setCustomerId("customer-5");
        customer4.setCreditScore(796);

        Customer customer5 = new Customer();
        customer5.setCustomerId("customer-5");
        customer5.setCreditScore(561);

        Customer customer6 = new Customer();
        customer6.setCustomerId("customer-6");
        customer6.setCreditScore(840);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);

        return customers;
    }

    public Customer getCustomerById(String customerId) {
        for (Customer customer : getCustomers()) {
            if (customer.getCustomerId().trim().equalsIgnoreCase(customerId.trim())) {
                return customer;
            }
        }

        throw new CustomerNotFoundException(customerId);
    }

    public Vehicle getVehicleById(String vehicleId) {
        for (Vehicle vehicle : getVehicles()) {
            if (vehicle.getVehicleId().trim().equalsIgnoreCase(vehicleId.trim())) {
                return vehicle;
            }
        }

        throw new VehicleNotFoundException(vehicleId);
    }

    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException(String customerId) {
            super("No customer found with id '" + customerId + "'");
        }
    }

    public static class VehicleNotFoundException extends RuntimeException {
        public VehicleNotFoundException(String vehicleId) {
            super("No vehicle found with id '" + vehicleId + "'");
        }
    }

}
