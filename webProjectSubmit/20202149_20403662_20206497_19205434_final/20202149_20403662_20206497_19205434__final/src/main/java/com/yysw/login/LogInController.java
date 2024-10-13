package com.yysw.login;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class LogInController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "login-success.html";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute(new RegisterInformation());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("RegisterInformation") RegisterInformation registerInformation) {
        if (registerInformation.getAdminKey() != null
                && Objects.equals(registerInformation.getAdminKey(), "verycooladminkey")
                && !userRepository.existsUserByUsername(registerInformation.getUsername())) {
            userRepository.save(new Owner(registerInformation.getUsername(), registerInformation.getPasswd()));
        } else if (!userRepository.existsUserByUsername(registerInformation.getUsername())){
            userRepository.save(new Customer(registerInformation.getUsername(), registerInformation.getPasswd()));
        }
        return "login.html";
    }
}
