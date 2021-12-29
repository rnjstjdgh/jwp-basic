package core.nmvc;

import core.mvc.Controller;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LegacyHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean isSupport(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ((Controller)handler).execute(request, response);
    }
}
