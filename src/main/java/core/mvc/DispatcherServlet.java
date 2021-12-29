package core.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import core.nmvc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private LegacyHandlerMapping legacyHandlerMapping;
    private AnnotationHandlerMapping annotationHandlerMapping;

    private List<HandlerMapping> mappings = new ArrayList<>();
    private List<HandlerAdapter> adapters = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        legacyHandlerMapping = new LegacyHandlerMapping();
        legacyHandlerMapping.initMapping();

        annotationHandlerMapping = new AnnotationHandlerMapping("next");
        try {
            annotationHandlerMapping.initialize();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mappings.add(legacyHandlerMapping);
        mappings.add(annotationHandlerMapping);

        adapters.add(new AnnotationHandlerAdapter());
        adapters.add(new LegacyHandlerAdapter());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

        Object handler = getHandler(req);
        try {
            ModelAndView mav = execution(handler,req,resp);
            View view = mav.getView();
            view.render(mav.getModel(), req, resp);
        } catch (Throwable e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }

    private ModelAndView execution(Object handler, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        for(HandlerAdapter handlerAdapter: adapters){
            if(handlerAdapter.isSupport(handler))
                return handlerAdapter.handle(handler,req,resp);
        }
        return null;
    }

    private Object getHandler(HttpServletRequest req){
        Object handler = null;
        for(HandlerMapping handlerMapping: mappings){
            handler = handlerMapping.getHandler(req);
            if(handler != null)
                break;
        }
        return handler;
    }
}
