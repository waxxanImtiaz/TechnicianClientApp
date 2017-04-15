package com.example.wassa_000.technician.factory;

import com.example.wassa_000.technician.beans.Customer;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class BeanFactory {
    private static Customer customer = new Customer();

    public static Customer getCustomer() {
        if(customer == null)
            return new Customer();
        return customer;
    }

    public static void setCustomer(Customer customer) {
        BeanFactory.customer = customer;
    }
}
