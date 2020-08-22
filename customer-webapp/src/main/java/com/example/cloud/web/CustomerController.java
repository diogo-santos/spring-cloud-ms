package com.example.cloud.web;

import com.example.cloud.web.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Collections.singletonList;
import static java.util.Objects.nonNull;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService service;
    private static final String CUSTOMER_PAGE = "customer";

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping({"/","/customer"})
    public String getCustomer(Model model, @RequestParam(required = false) Long id) {
        if (nonNull(id)) {
            Customer customer = service.getCustomer(id);
            model.addAttribute(singletonList(customer));
        } else {
            Customer[] customers = service.getCustomers();
            model.addAttribute("customerList", customers);
        }
        model.addAttribute(new Customer());
        return CUSTOMER_PAGE;
    }

    @PostMapping("/customer")
    public String createCustomer(Model model, @Valid Customer customer, BindingResult result) {
        if (!result.hasErrors()) {
            Customer customerResponse = service.create(customer);
            logger.debug("Customer saved {}", customerResponse);
            return "redirect:/" + CUSTOMER_PAGE;
        }
        return CUSTOMER_PAGE;
    }
}