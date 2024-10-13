package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@WebServlet("/stayLogIn")
public class CheckLogIn extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        boolean alreadyLogIn = false;
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if(session != null) {
            // 判断cookie是否有username，如果有代表登陆过
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sessionId")) { // 表明已经登陆过了，就直接跳转了
                        alreadyLogIn = true;
                    }
                }
            }

            if (alreadyLogIn) {
                response.sendRedirect(request.getContextPath() + "/successLogIn");
            } else {
                response.sendRedirect(request.getContextPath() + "/logInAgain");
                out.write("<html>"
                        + "<head><script type='text/javascript'> alert('Never Login, please login!');location='login.html';</script></head>"
                        + "<body></body></html>");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/logInAgain");
        }
    }
}
