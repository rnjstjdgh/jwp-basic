package core.web;

import next.controller.Controller;
import next.controller.UpdateUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = RequestMapping.getController(req.getRequestURI());
        String viewName = controller.doGet(req,resp);
        move(viewName,req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = RequestMapping.getController(req.getRequestURI());
        String viewName = controller.doPost(req,resp);
        move(viewName,req,resp);
    }

    private void move(String viewName,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(viewName.startsWith("redirect"))
        {
            resp.sendRedirect(viewName.substring(9));
        }
        else {
            forward(viewName,req,resp);
        }
    }

    private void forward(String forwardUrl, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
        rd.forward(req, resp);
    }
}
