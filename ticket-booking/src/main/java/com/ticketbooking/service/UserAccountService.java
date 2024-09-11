package com.ticketbooking.service;

import com.ticketbooking.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {

    UserAccount refillAccount(long userId, BigDecimal money);
}
