package com.peiwen.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peiwen.service.InvoiceService;
import com.peiwen.service.UserService;

public class Application {
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
