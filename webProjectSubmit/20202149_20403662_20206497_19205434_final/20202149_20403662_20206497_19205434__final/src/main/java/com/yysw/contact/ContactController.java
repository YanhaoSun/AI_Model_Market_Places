package com.yysw.contact;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ContactController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/contactUs")
    public String contact(Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        User sessionUser = userRepository.findUserById(sessionUserID);
        model.addAttribute("user", sessionUser);
        model.addAttribute("contactInformation", new ContactInformation());
        return "contact-us.html";
    }

    @PostMapping("/contactUs")
    public String submitContact(@Valid @ModelAttribute("contactInformation") ContactInformation contactInformation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact-us.html";
        } else {
            return "contact-success.html";
        }
    }
}
