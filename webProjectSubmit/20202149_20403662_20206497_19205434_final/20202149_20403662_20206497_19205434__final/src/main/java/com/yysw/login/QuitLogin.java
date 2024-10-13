package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@WebServlet("/quitLogin")
public class QuitLogin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        boolean alreadyLogIn = false;
        if(session != null) {
            PrintWriter out = response.getWriter();
            //we set cookie of username to 0，which means delete it straightaway
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sessionId")) { // this means current username already logged in
                        alreadyLogIn = true;
                        cookie.setMaxAge(0);
                        session.invalidate();
                        response.addCookie(cookie);
                    }
                }
            }
            if(alreadyLogIn){
                response.sendRedirect(request.getContextPath() + "/");
            }else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
