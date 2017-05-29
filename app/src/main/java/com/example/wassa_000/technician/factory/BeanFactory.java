package com.example.wassa_000.technician.factory;

import com.example.wassa_000.technician.beans.Complain;
import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.beans.Feedback;
import com.example.wassa_000.technician.beans.PaymentHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class BeanFactory {
    private static Customer customer = new Customer();
    private static List<Feedback> feedbacks = new ArrayList<>();
    private static List<Complain> complain = new ArrayList<>();
    private static List<PaymentHistory> paymentHistories = new ArrayList<>();

    public static Customer getCustomer() {
        if(customer == null)
            return new Customer();
        return customer;
    }

    public static void setCustomer(Customer customer) {
        BeanFactory.customer = customer;
    }

    public static List<Feedback> getFeedbacks() {
        if (feedbacks == null)
            return new ArrayList<>();
        return feedbacks;
    }

    public static void setFeedbacks(List<Feedback> feedbacks) {
        BeanFactory.feedbacks = feedbacks;
    }

    public static List<Complain> getComplain() {
        if (complain == null)
            return new ArrayList<>();
        return complain;
    }

    public static void setComplain(List<Complain> complain) {
        BeanFactory.complain = complain;
    }

    public static List<PaymentHistory> getPaymentHistories() {
        if (paymentHistories == null)
            return new ArrayList<>();
        return paymentHistories;
    }

    public static void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        BeanFactory.paymentHistories = paymentHistories;
    }
}
