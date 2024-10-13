package com.yysw;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class SiteController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        if (sessionUserID == null) {
            return "index.html";
        } else {
            User repoUser = userRepository.findUserById(sessionUserID);
            model.addAttribute("user", repoUser);
        }
        return "index.html";
    }
}