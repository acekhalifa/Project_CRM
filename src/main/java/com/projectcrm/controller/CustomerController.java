package com.projectcrm.controller;

import com.projectcrm.entity.Customer;
import com.projectcrm.service.CustomerService;
import com.projectcrm.util.SortUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/list")
    public String listCustomers(Model model, @RequestParam(required = false) String sort) {
        List<Customer> customers = null;
        if(sort != null){
            int sortKey = Integer.parseInt(sort);
            customers = customerService.getCustomers(sortKey);
        }
        else{
            customers = customerService.getCustomers(SortUtils.LAST_NAME);
        }
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam("customerId") int id, Model model) {
        var customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }
    @GetMapping("/showFormDelete")
    public String showFormDelete(@RequestParam("customerId") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }
}
