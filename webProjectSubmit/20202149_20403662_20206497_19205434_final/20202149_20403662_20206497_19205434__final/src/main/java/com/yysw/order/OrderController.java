package com.yysw.order;

import com.yysw.cart.ShoppingCartItem;
import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //DO NOT DELETE THIS METHOD!!!
    @GetMapping("/order-history")
    public String orderHistory(Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        // session user wont be null because order history can only access by user after login
        User user = userRepository.findUserById(sessionUserID);
        List<Order> orders;
        if (user instanceof Customer) {
            orders = orderRepository.findOrderByCustomerOrderByOrderDateDesc((Customer) user);
        } else {
            orders = orderRepository.findAllByOrderByOrderDateDescStateAsc();
        }
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "order-history.html";
    }

    @GetMapping("/view-order/{id}")
    public String viewOrder(ModelMap model, @PathVariable Long id, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        Order order = orderRepository.findOrderById(id);
        double sub = 0.0;

        for(OrderedModel orderedModel: order.getOrderedModels())
        {
            sub += orderedModel.getPrice();
        }

        DecimalFormat df = new DecimalFormat("####0.00");
        // session user wont be null because order history can only access by user after login
        model.addAttribute("price", df.format(sub));
        model.addAttribute("order", orderRepository.findOrderById(id));
        model.addAttribute("user", userRepository.findUserById(sessionUserID));

        return "view-order.html";
    }

    @PostMapping("/view-order/{id}")
    public @ResponseBody void orderChangeState(@ModelAttribute("order") Order order, @PathVariable Long id, HttpServletResponse response) {
        Order orderStateToBeUpdated = orderRepository.findOrderById(id);
        orderStateToBeUpdated.updateState(order);
        orderRepository.save(orderStateToBeUpdated);
        try {
            response.sendRedirect("/order-history");
        } catch(IOException e) {e.printStackTrace();}
    }
}