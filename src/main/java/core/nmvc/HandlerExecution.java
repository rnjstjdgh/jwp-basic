package core.nmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ModelAndView;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class HandlerExecution {

    private Object declaredObject;
    private Method method;

    public HandlerExecution(Object declaredController, Method method) {
        this.declaredObject = declaredController;
        this.method = method;
    }

    public HandlerExecution(){};

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return (ModelAndView) method.invoke(declaredObject,request,response);
    }
}
