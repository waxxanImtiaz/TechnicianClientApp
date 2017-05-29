package com.example.wassa_000.technician.factory;

import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.beans.Feedback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class BeanFactory {
    private static Customer customer = new Customer();
    private static List<Feedback> feedbacks = new ArrayList<>();

    public static Customer getCustomer() {
        if(customer == null)
            return new Customer();
        return customer;
    }

    public static void setCustomer(Customer customer) {
        BeanFactory.customer = customer;
    }

    public static List<Feedback> getFeedbacks() {

        return feedbacks;
    }

    public static void setFeedbacks(List<Feedback> feedbacks) {
        if (feedbacks == null)
            return;new ArrayList<>();
        BeanFactory.feedbacks = feedbacks;
    }
}
