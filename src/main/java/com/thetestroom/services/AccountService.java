package com.thetestroom.services;

import com.thetestroom.model.Account;
import com.thetestroom.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> list() {
        return accountRepository.findAll();
    }

    public Account get(Long index) {
        return accountRepository.getOne(index);
    }

}
