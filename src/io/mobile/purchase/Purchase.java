package io.mobile.purchase;

public class Purchase {
    private int order_nub;
    private String order_date;
    private int order_quantity;
    private String shipping_address;

    public Purchase(int order_nub, String order_date, int order_quantity, String shipping_address) {
        this.order_nub = order_nub;
        this.order_date = order_date;
        this.order_quantity = order_quantity;
        this.shipping_address = shipping_address;
    }

    public int getOrder_nub() {
        return order_nub;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "order_nub=" + order_nub +
                ", order_date='" + order_date + '\'' +
                ", order_quantity=" + order_quantity +
                ", shipping_address='" + shipping_address + '\'' +
                '}';
    }
}
