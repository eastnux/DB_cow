package io.mobile.customer;

public class Customer {
    private String customer_id;
    private String name;
    private String address;

    public Customer(String name, String customer_id, String address) {
        this.name = name;
        this.customer_id = customer_id;
        this.address = address;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
