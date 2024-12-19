package io.mobile.customer;

import java.util.List;

public class CustomerMain {
    public static void main(String[] args) {

        System.out.println("\n 새로운 고객 추가\n-----------------------");

        // Insert a new customer
        String newCustomerId = "김1";
        String newName = "김길동";
        String newAddress = "대전시유성구";
        int insertResult = CustomerService.insert(newCustomerId, newName, newAddress);
        if (insertResult > 0) {
            System.out.println("Customer added successfully: " + newCustomerId);
        } else {
            System.out.println("Failed to add customer: " + newCustomerId);
        }

        System.out.println("\n 모든 고객 목록 보기\n-----------------------");

        // Select all customers
        List<Customer> allCustomers = CustomerService.selectAll();
        if (allCustomers != null) {
            allCustomers.forEach(System.out::println);
        } else {
            System.out.println("No customers found.");
        }

        System.out.println("\n 특정 고객 정보 보기\n-----------------------");

        // Select a customer by ID
        Customer selectedCustomer = CustomerService.selectById(newCustomerId);
        if (selectedCustomer != null) {
            System.out.println("Selected Customer: " + selectedCustomer);
        } else {
            System.out.println("Customer not found: " + newCustomerId);
        }

        System.out.println("\n 고객 정보 업데이트\n-----------------------");

        // Update a customer
//        String updatedName = "Jane Doe";
//        String updatedAddress = "456 Elm St, Anytown, USA";
//        int updateResult = CustomerService.update(newCustomerId, updatedName, updatedAddress);
//        if (updateResult > 0) {
//            System.out.println("Customer updated successfully: " + newCustomerId);
//        } else {
//            System.out.println("Failed to update customer: " + newCustomerId);
//        }

        System.out.println("\n 고객 정보 삭제\n-----------------------");

        // Delete a customer
        int deleteResult = CustomerService.delete(newCustomerId);
        if (deleteResult > 0) {
            System.out.println("Customer deleted successfully: " + newCustomerId);
        } else {
            System.out.println("Failed to delete customer: " + newCustomerId);
        }
    }
}
