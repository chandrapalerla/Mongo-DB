package com.ticketbooking.repository;

import com.ticketbooking.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserId(Long userId);
}
