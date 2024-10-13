package com.yysw.user.owner;

import com.yysw.aimodels.AIModel;
import com.yysw.aimodels.AIModelRepository;
import com.yysw.user.User;
import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class OwnerController {
    @Autowired
    AIModelRepository aiModelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add-model")
    public String addModel(HttpSession session, Model model) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        User user = userRepository.findUserById(sessionUserID);
        model.addAttribute("user", user);
        model.addAttribute("newModel", new AIModel());
        return("add-model.html");
    }

    @PostMapping("/add-model")
    public String submitModel(@Valid @ModelAttribute("newModel") AIModel aiModel, Model model, HttpSession session) {
        Long sessionUserID = (Long) session.getAttribute("user_id");
        User user = userRepository.findUserById(sessionUserID);
        model.addAttribute("user", user);
        aiModelRepository.save(aiModel);
        model.addAttribute("catalogue", aiModelRepository.findAll());
        return "catalogue.html";
    }

}
