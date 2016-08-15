package com.supercell.service;

/**
 * Created by WUJO2 on 8/9/2016.
 */
public interface JMSProducerService {
    void sendCustomerComplaintToAdmin(final String message);

    void sendMerchantRatingToAdmin(final String message);

    void sendMessageToAdmin(final String message);
}
