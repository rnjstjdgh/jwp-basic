package next.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {

    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
