package com.example.demo.serviceImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.service.PaymentInterf;

@Primary
@Service
public class UPIPaymentService implements PaymentInterf {
}
