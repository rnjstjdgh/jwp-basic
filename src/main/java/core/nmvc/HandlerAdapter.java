package core.nmvc;

import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {

    /***
     * HandlerAdapter 인스턴스가 지원하는 핸들러라면 true
     * @param handler
     * @return
     */
    boolean isSupport(Object handler);

    /***
     * handler 인스턴스의 작업 수행
     * @param handler
     * @param request
     * @param response
     * @return
     */
    ModelAndView handle(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
