package com.yysw.login;

import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@WebServlet("/submit-login")
public class PersistentLogIn extends HttpServlet {
    @Autowired
    private UserRepository userRepository;
    private static final long serialVersionUID = 1L;
    public PersistentLogIn() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.setMaxInactiveInterval(-1);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("passwd");

        PrintWriter out = response.getWriter();

        //说明还没有用户登录
        if (session.getAttribute("user_id") == null) {
            if (userRepository.existsUserByUsername(username)) {
                if (userRepository.existsUserByUsernameAndPasswd(username, password)) {
                    Cookie cookie = new Cookie("sessionId", sessionId);
                    cookie.setMaxAge(-1);//let's say the cookie is valid in two minutes
                    response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in
                    session.setAttribute("user_id", userRepository.findByUsernameAndPasswd(username, password).getId()); //add user id into session
                    response.sendRedirect("/login-success");
                } else {
                    out.write("<html"
                            + "<head><script type='text/javascript'> alert('Incorrect password.');location='login';</script></head>"
                            + "<body></body></html>");
                }
            } else {
                out.write("<html>"
                        + "<head><script type='text/javascript'> alert('Username does not exist.');location='login';</script></head>"
                        + "<body></body></html>");
            }
        } else {
            response.sendRedirect("/");
        }
    }
}