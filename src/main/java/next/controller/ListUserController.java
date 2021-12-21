package next.controller;

import core.db.DataBase;
import next.utils.UserSessionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!UserSessionUtils.isLogined(req.getSession())) {
//            resp.sendRedirect("/users/loginForm");
            return "redirect:/users/loginForm";
        }

        req.setAttribute("users", DataBase.findAll());

//        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
//        rd.forward(req, resp);
        return "/user/list.jsp";
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "";
    }
}
