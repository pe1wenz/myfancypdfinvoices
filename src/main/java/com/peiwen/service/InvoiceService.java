package com.peiwen.service;

import com.peiwen.model.Invoice;
import com.peiwen.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final UserService userService;

    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll(){
        return invoices;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }
        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);

        return invoice;
    }
}
