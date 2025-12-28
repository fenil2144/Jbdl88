package com.gfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transact")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public String transact(@RequestParam("receiver") String receiver,
                           @RequestParam("amount") double amount,
                           @RequestParam("reason") String reason){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return transactionService.transact(user.getUsername(), receiver, amount, reason);

    }
}
