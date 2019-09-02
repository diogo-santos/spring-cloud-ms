package com.example.cloud.web;

import com.example.cloud.web.client.CustomerService;
import com.example.cloud.web.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Collections.singletonList;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService service;
    private static final String CUSTOMER_PAGE = "customer";

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping({"/","/customer"})
    public String getPeople(Model model, @RequestParam(required = false) Long id) {
        Customer customer;
        if (null != id && null != (customer = service.findById(id))) {
            model.addAttribute(singletonList(customer));
        } else {
            Customer[] people = service.findAll();
            if (null != people) {
                model.addAttribute(people);
            }
        }
        model.addAttribute(new Customer());
        return CUSTOMER_PAGE;
    }

    @PostMapping("/customer")
    public String createCustomer(Model model, @Valid Customer customer, BindingResult result) {
        if (!result.hasErrors()) {
            customer.setContactList();
            Customer customerSaved = service.create(customer);
            logger.debug("Customer saved {}", customerSaved);
            return "redirect:/"+CUSTOMER_PAGE;
        }
        return CUSTOMER_PAGE;
    }
}