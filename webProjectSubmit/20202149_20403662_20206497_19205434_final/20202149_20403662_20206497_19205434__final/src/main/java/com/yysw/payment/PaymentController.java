package com.yysw.payment;

import com.yysw.cart.ShoppingCartItem;
import com.yysw.cart.ShoppingCartRepository;
import com.yysw.order.Order;
import com.yysw.order.OrderRepository;
import com.yysw.order.OrderedModel;
import com.yysw.order.State;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @GetMapping("/payment")
    public String payment(Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        List<ShoppingCartItem> userCart = customerRepository.findCustomerById(sessionUserID).getCart();
        double sub = 0.0;
        for (ShoppingCartItem item : userCart) {
            sub += item.getPrice();
        }
        DecimalFormat df = new DecimalFormat("####0.00");
        model.addAttribute("currentOrderPrice", df.format(sub));
        model.addAttribute("paymentInformation", new PaymentInformation());
        return "payment.html";
    }

    @Transactional
    @PostMapping("/payment")
    public String submitPayment(
            @Valid @ModelAttribute("paymentInformation") PaymentInformation paymentInformation,
            HttpSession session, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "payment.html";
        } else {
            Long sessionUserID = (Long) session.getAttribute("user_id");
            Customer customer = customerRepository.findCustomerById(sessionUserID);
            List<OrderedModel> orderedModels = new ArrayList<>();

            for (ShoppingCartItem item : customer.getCart()) {
                orderedModels.add(new OrderedModel(item.getItem().getId(), item.getPrice()));
            }

            Date d = Date.valueOf(LocalDate.now());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String id = customer.getId() + "#" + dtf.format(now);

            Order order = new Order(customer, orderedModels, State.NEW, d, id);
            orderRepository.save(order);

            model.addAttribute("items", customer.getCart());
            model.addAttribute("name", customer.getUsername());
            model.addAttribute("order", order);
            customer.setCart(new ArrayList<>());
            shoppingCartRepository.deleteAllByCustomer(customer);

            return "order-receipt.html";
        }
    }
}