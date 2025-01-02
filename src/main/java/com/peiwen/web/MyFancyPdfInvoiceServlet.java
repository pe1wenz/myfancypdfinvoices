package com.peiwen.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peiwen.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.peiwen.model.Invoice;
import com.peiwen.service.InvoiceService;
import com.peiwen.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoiceServlet extends HttpServlet {

    private UserService userService;
    private InvoiceService invoiceService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        context.registerShutdownHook();

        this.userService = context.getBean(UserService.class);
        this.invoiceService = context.getBean(InvoiceService.class);
        this.objectMapper = context.getBean(ObjectMapper.class);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/")) {
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>"
            );
        }
        else if(req.getRequestURI().equalsIgnoreCase("/invoice")) {
            resp.setContentType("application/json; charset=utf-8");
            List<Invoice> invoices = invoiceService.findAll();
            resp.getWriter().print(objectMapper.writeValueAsString(invoices));

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = req.getParameter("user_id");
            Integer amount = Integer.valueOf(req.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            resp.setContentType("application/json; charset=utf-8");
            String json = objectMapper.writeValueAsString(invoice);
            resp.getWriter().print(json);
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
